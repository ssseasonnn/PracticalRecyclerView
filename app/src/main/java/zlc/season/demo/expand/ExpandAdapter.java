package zlc.season.demo.expand;

import android.view.ViewGroup;

import zlc.season.demo.RecyclerItemType;
import zlc.season.practicalrecyclerview.AbstractAdapter;
import zlc.season.practicalrecyclerview.AbstractViewHolder;
import zlc.season.practicalrecyclerview.ItemType;

/**
 * Author: Season(ssseasonnn@gmail.com)
 * Date: 2016/10/17
 * Time: 15:31
 * FIXME
 */
class ExpandAdapter extends AbstractAdapter<ItemType, AbstractViewHolder> {
    @Override
    protected AbstractViewHolder onNewCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == RecyclerItemType.PARENT.getValue()) {
            return new ParentViewHolder(this, parent);
        } else if (viewType == RecyclerItemType.CHILD.getValue()) {
            return new ChildViewHolder(parent);
        }
        return null;
    }

    @Override
    protected void onNewBindViewHolder(AbstractViewHolder holder, int position) {
        if (holder instanceof ParentViewHolder) {
            ((ParentViewHolder) holder).setData((ParentBean) get(position));
        } else if (holder instanceof ChildViewHolder) {
            ((ChildViewHolder) holder).setData((ChildBean) get(position));
        }
    }
}
