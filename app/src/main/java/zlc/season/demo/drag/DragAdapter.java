package zlc.season.demo.drag;

import android.view.ViewGroup;

import zlc.season.practicalrecyclerview.AbstractAdapter;

/**
 * Author: Season(ssseasonnn@gmail.com)
 * Date: 2016/10/12
 * Time: 11:45
 * FIXME
 */
class DragAdapter extends AbstractAdapter<DragBean, DragViewHolder> {
    @Override
    protected DragViewHolder onNewCreateViewHolder(ViewGroup parent, int viewType) {
        return new DragViewHolder(parent);
    }

    @Override
    protected void onNewBindViewHolder(DragViewHolder holder, int position) {
        holder.setData(get(position));
    }

}
