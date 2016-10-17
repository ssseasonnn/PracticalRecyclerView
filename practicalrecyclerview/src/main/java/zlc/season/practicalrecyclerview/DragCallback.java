package zlc.season.practicalrecyclerview;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

/**
 * Author: Season(ssseasonnn@gmail.com)
 * Date: 2016/10/12
 * Time: 10:28
 * FIXME
 */
class DragCallback extends ItemTouchHelper.Callback {

    private DragListener mDragListener;

    DragCallback(DragListener dragListener) {
        mDragListener = dragListener;
    }

    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        int currentPosition = viewHolder.getAdapterPosition();
        if (!mDragListener.canDrag(currentPosition)) {
            return 0;
        }
        int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
        int swipeFlags = ItemTouchHelper.START | ItemTouchHelper.END;
        return makeMovementFlags(dragFlags, swipeFlags);
    }


    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder,
                          RecyclerView.ViewHolder target) {
        mDragListener.onItemMove(viewHolder.getAdapterPosition(), target.getAdapterPosition());
        return true;
    }

    @Override
    public boolean isLongPressDragEnabled() {
        return mDragListener.isLongPressDragEnabled();
    }

    @Override
    public boolean isItemViewSwipeEnabled() {
        return mDragListener.isItemViewSwipeEnabled();
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        mDragListener.onItemDismiss(viewHolder.getAdapterPosition());
    }

    @Override
    public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
        super.onSelectedChanged(viewHolder, actionState);
        final boolean enabled = !(actionState == ItemTouchHelper.ACTION_STATE_DRAG ||
                actionState == ItemTouchHelper.ACTION_STATE_SWIPE);
        mDragListener.resolveSwipeConflicts(enabled);
    }

    interface DragListener {

        boolean canDrag(int position);

        void onItemMove(int fromPosition, int toPosition);

        void onItemDismiss(int position);

        void resolveSwipeConflicts(boolean enabled);

        boolean isLongPressDragEnabled();

        boolean isItemViewSwipeEnabled();
    }
}
