package zlc.season.demo.expand;

import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import zlc.season.demo.R;
import zlc.season.practicalrecyclerview.AbstractAdapter;
import zlc.season.practicalrecyclerview.AbstractViewHolder;

/**
 * Author: Season(ssseasonnn@gmail.com)
 * Date: 2016/10/17
 * Time: 15:30
 * FIXME
 */
public class ParentViewHolder extends AbstractViewHolder<ParentBean> {

    @BindView(R.id.text)
    TextView mText;

    private boolean isExpand = false;
    private List<ChildBean> child;

    private ExpandAdapter mAdapter;

    public ParentViewHolder(AbstractAdapter adapter, ViewGroup parent) {
        super(adapter, parent, R.layout.parent_item);
        ButterKnife.bind(this, itemView);
        mAdapter = (ExpandAdapter) adapter;
    }

    @Override
    public void setData(ParentBean data) {
        mText.setText(String.valueOf(data.text));
        isExpand = false;
        child = data.mChild;
    }

    @OnClick(R.id.text)
    public void onClick() {
        if (isExpand) {
            mAdapter.removeDataBack(getAdapterPosition(), child.size());
            mAdapter.notifyDataSetChanged();
            isExpand = false;
        } else {
            mAdapter.insertAllDataBack(getAdapterPosition(), child);
            mAdapter.notifyDataSetChanged();
            isExpand = true;
        }
    }
}
