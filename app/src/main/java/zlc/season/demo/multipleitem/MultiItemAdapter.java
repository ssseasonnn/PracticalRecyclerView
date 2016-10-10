package zlc.season.demo.multipleitem;

import android.view.ViewGroup;

import zlc.season.demo.RecyclerItemType;
import zlc.season.practicalrecyclerview.AbstractAdapter;
import zlc.season.practicalrecyclerview.AbstractViewHolder;
import zlc.season.practicalrecyclerview.ItemType;

/**
 * Author: Season(ssseasonnn@gmail.com)
 * Date: 2016/9/21
 * Time: 15:08
 * FIXME
 */
public class MultiItemAdapter extends AbstractAdapter<ItemType, AbstractViewHolder> {
    
    @Override
    protected AbstractViewHolder onNewCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == RecyclerItemType.TYPE1.getValue()) {
            return new TypeOneViewHolder(parent);
        } else if (viewType == RecyclerItemType.TYPE2.getValue()) {
            return new TypeTwoViewHolder(parent);
        }
        return null;
    }

    @Override
    protected void onNewBindViewHolder(AbstractViewHolder holder, int position) {
        if (holder instanceof TypeOneViewHolder) {
            ((TypeOneViewHolder) holder).setData((TypeOneBean) get(position));
        } else if (holder instanceof TypeTwoViewHolder) {
            ((TypeTwoViewHolder) holder).setData((TypeTwoBean) get(position));
        }
    }
}
