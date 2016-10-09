package zlc.season.demo.multipleitem;

import android.view.ViewGroup;

import zlc.season.demo.RecyclerItemType;
import zlc.season.practicalrecyclerview.AbstractAdapter;
import zlc.season.practicalrecyclerview.ItemType;
import zlc.season.practicalrecyclerview.AbstractViewHolder;

/**
 * Author: Season(ssseasonnn@gmail.com)
 * Date: 2016/9/21
 * Time: 15:08
 * FIXME
 */
public class MultiItemAdapter extends AbstractAdapter<ItemType, AbstractViewHolder> {


    @Override
    protected AbstractViewHolder onNewCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == RecyclerItemType.TEST1.getValue()) {
            return new TestViewHolder(parent);
        }
        return null;
    }

    @Override
    protected void onNewBindViewHolder(AbstractViewHolder holder, int position) {
        if (holder instanceof TestViewHolder) {
            ((TestViewHolder) holder).setData((TestBean) get(position));
        }
    }


}
