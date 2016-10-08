package zlc.season.practicalrecyclerview;

import android.os.Handler;
import android.os.Looper;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.Observer;

import static android.support.v7.widget.RecyclerView.SCROLL_STATE_IDLE;

/**
 * Author: Season(ssseasonnn@gmail.com)
 * Date: 2016/9/21
 * Time: 14:34
 * FIXME
 */
public abstract class AbstractAdapter<T extends ItemType, VH extends AbstractViewHolder> extends
        RecyclerView.Adapter<VH> {
    public DataSetObservable<T> dataSet;

    private RecyclerView mRecyclerView;

    public AbstractAdapter() {
        dataSet = new DataSetObservable<>();
    }


    public T get(int position) {
        return dataSet.data.get(position);
    }

    public void registerObserver(Observer observer) {
        dataSet.addObserver(observer);
    }


    public void addHeader(SectionItem header) {
        dataSet.header.add(header);
    }

    public void clear() {
        dataSet.clear();
    }

    /**
     * 添加0个数据时自动停止加载更多
     *
     * @param data list of data
     */
    public void addAll(List<? extends T> data) {
        dataSet.data.addAll(data);
        notifyDataSetChanged();

        if (dataSet.totalSize() == 0) {
            dataSet.notifyEmpty();
        } else {
            if (data.size() == 0) {
                dataSet.notifyNoMore();
            } else {
                dataSet.notifyContent();
            }
        }
    }

    public void showError() {
        dataSet.clear();
        notifyDataSetChanged();
        dataSet.notifyError();
    }

    public void loadMoreFailed() {
        dataSet.notifyLoadMoreFailed();
    }

    public void resumeLoadMore() {
        dataSet.notifyResumeLoadMore();
    }

    public void diffData(List<? extends T> oldData, List<? extends T> newData) {
        DiffUtil.DiffResult result = DiffUtil.calculateDiff(new ItemDiffRule<>(oldData, newData));
        result.dispatchUpdatesTo(this);
        dataSet.notifyContent();
    }

    public void diffExtra(List<SectionItem> oldData, List<SectionItem> newData) {
        DiffUtil.DiffResult result = DiffUtil.calculateDiff(new ItemDiffRule<>(oldData, newData));
        result.dispatchUpdatesTo(this);
    }

    public int getExtraAdapterPosition() {
        return dataSet.extra.position();
    }

    public void insteadExtra(int adapterPosition, SectionItem newItem) {
        dataSet.extra.set(adapterPosition, newItem);
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        VH viewHolder = createHeaderFooterViewHolder(parent, viewType);
        if (viewHolder != null) return viewHolder;
        return onNewCreateViewHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(VH holder, int position) {
        if (dataSet.header.is(position)) {
            dataSet.header.get(position).onBind();
        } else if (dataSet.data.isData(position)) {
            onNewBindViewHolder(holder, position - dataSet.header.size());
        } else if (dataSet.footer.is(position)) {
            dataSet.footer.get(position).onBind();
        } else {
            dataSet.extra.get(position).onBind();
        }
    }


    @Override
    public final int getItemViewType(int position) {
        //用header和footer的HashCode表示它们的ItemType,
        //普通类型的数据由该数据类型的ItemType决定
        if (dataSet.header.is(position)) {
            return dataSet.header.get(position).hashCode();
        } else if (dataSet.data.isData(position)) {
            return dataSet.data.getDataByAdapterPosition(position).itemType();
        } else if (dataSet.footer.is(position)) {
            return dataSet.footer.get(position).hashCode();
        } else {
            return dataSet.extra.get(position).hashCode();
        }
    }

    @Override
    public final int getItemCount() {
        return dataSet.totalSize();
    }

    @Override
    public void onViewAttachedToWindow(VH holder) {
        super.onViewAttachedToWindow(holder);
        if (mRecyclerView.getScrollState() != SCROLL_STATE_IDLE) return;
        int position = holder.getAdapterPosition();
        if (dataSet.extra.size() == 0) {
            if (position == dataSet.totalSize() - 1) {
                loadMore();
            }
        } else {
            if (position == dataSet.totalSize() - 1 - dataSet.extra.size()) {
                loadMore();
            }
        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        mRecyclerView = recyclerView;
    }

    private void loadMore() {
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                dataSet.notifyAutoLoadMore();
            }
        });
    }

    @SuppressWarnings("unchecked")
    private VH createHeaderFooterViewHolder(ViewGroup parent, int viewType) {
        List<SectionItem> tempContainer = new ArrayList<>();
        tempContainer.addAll(dataSet.header.getAll());
        tempContainer.addAll(dataSet.footer.getAll());
        tempContainer.addAll(dataSet.extra.getAll());

        for (SectionItem each : tempContainer) {
            if (each.hashCode() == viewType) {
                View view = each.createView(parent);
                return (VH) new SectionItemViewHolder(view);
            }
        }
        return null;
    }

    void show(View view) {
        if (dataSet.extra.size() == 0) {
            dataSet.extra.add(new SectionItemImpl(view));
            notifyItemInserted(dataSet.extra.position());
        } else {
            if (!dataSet.extra.get(dataSet.extra.position()).createView(null).equals(view)) {
                dataSet.extra.set(dataSet.extra.position(), new SectionItemImpl(view));
                notifyItemChanged(dataSet.extra.position());
            }
        }
    }


    protected abstract VH onNewCreateViewHolder(ViewGroup parent, int viewType);

    protected abstract void onNewBindViewHolder(VH holder, int dataPosition);


    private class SectionItemViewHolder extends AbstractViewHolder {

        SectionItemViewHolder(View itemView) {
            super(itemView);
        }

    }

}
