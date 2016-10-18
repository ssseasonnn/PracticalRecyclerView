package zlc.season.practicalrecyclerview;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

/**
 * Author: Season(ssseasonnn@gmail.com)
 * Date: 2016/10/12
 * Time: 10:28
 * FIXME
 */
public class SimpleItemTouchHelperCallback extends ItemTouchHelper.Callback {

    private boolean isLongPressDragEnabled;
    private boolean isSwipeEnabled;
    private AbstractAdapter mAdapter;

    /**
     * SimpleItemTouchHelperCallback
     *
     * @param isLongPressDragEnabled 是否启用默认的长按拖动
     * @param isSwipeEnabled         是否启用默认的滑动删除
     */
    public SimpleItemTouchHelperCallback(boolean isLongPressDragEnabled, boolean isSwipeEnabled) {
        this.isLongPressDragEnabled = isLongPressDragEnabled;
        this.isSwipeEnabled = isSwipeEnabled;
    }

    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        if (!(recyclerView.getAdapter() instanceof AbstractAdapter)) {
            return 0;
        }

        mAdapter = (AbstractAdapter) recyclerView.getAdapter();
        int currentPosition = viewHolder.getAdapterPosition();
        if (!mAdapter.canDrag(currentPosition)) {
            return 0;
        }

        int dragFlags;
        int swipeFlags;
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN | ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
            swipeFlags = 0;
        } else if (layoutManager instanceof LinearLayoutManager) {
            dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
            swipeFlags = ItemTouchHelper.START | ItemTouchHelper.END;
        } else {
            dragFlags = 0;
            swipeFlags = 0;
        }

        return makeMovementFlags(dragFlags, swipeFlags);
    }


    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder,
                          RecyclerView.ViewHolder target) {
        mAdapter.moveItem(viewHolder.getAdapterPosition(), target.getAdapterPosition());
        return true;
    }

    @Override
    public boolean isLongPressDragEnabled() {
        return isLongPressDragEnabled;
    }

    @Override
    public boolean isItemViewSwipeEnabled() {
        return isSwipeEnabled;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        mAdapter.removeItem(viewHolder.getAdapterPosition());
    }

    @Override
    public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
        super.onSelectedChanged(viewHolder, actionState);
        final boolean enabled = !(actionState == ItemTouchHelper.ACTION_STATE_DRAG ||
                actionState == ItemTouchHelper.ACTION_STATE_SWIPE);
        mAdapter.resolveSwipeConflicts(enabled);
    }
}
