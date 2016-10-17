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
    private PracticalRecyclerView mHost;

    /**
     * SimpleItemTouchHelperCallback
     *
     * @param host                   PracticalRecyclerView
     * @param isLongPressDragEnabled 是否启用默认的长按拖动
     * @param isSwipeEnabled         是否启用默认的滑动删除
     */
    public SimpleItemTouchHelperCallback(PracticalRecyclerView host, boolean isLongPressDragEnabled, boolean
            isSwipeEnabled) {
        mHost = host;
        this.isLongPressDragEnabled = isLongPressDragEnabled;
        this.isSwipeEnabled = isSwipeEnabled;
    }

    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        int currentPosition = viewHolder.getAdapterPosition();
        if (!mHost.canDrag(currentPosition)) {
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
        mHost.onItemMove(viewHolder.getAdapterPosition(), target.getAdapterPosition());
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
        mHost.onItemDismiss(viewHolder.getAdapterPosition());
    }

    @Override
    public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
        super.onSelectedChanged(viewHolder, actionState);
        final boolean enabled = !(actionState == ItemTouchHelper.ACTION_STATE_DRAG ||
                actionState == ItemTouchHelper.ACTION_STATE_SWIPE);
        mHost.resolveSwipeConflicts(enabled);
    }
}
