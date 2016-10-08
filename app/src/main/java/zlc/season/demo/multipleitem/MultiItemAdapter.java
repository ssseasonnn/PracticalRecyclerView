package zlc.season.demo.multipleitem;

import android.view.ViewGroup;

import zlc.season.demo.RecyclerItemType;
import zlc.season.demo.data.NormalBean;
import zlc.season.demo.data.TestBean;
import zlc.season.demo.viewholder.NormalViewHolder;
import zlc.season.demo.viewholder.TestViewHolder;
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
        if (viewType == RecyclerItemType.NORMAL.getValue()) {
            return new NormalViewHolder(parent);
        } else if (viewType == RecyclerItemType.TEST1.getValue()) {
            return new TestViewHolder(parent);
        }
        return null;
    }

    @Override
    protected void onNewBindViewHolder(AbstractViewHolder holder, int position) {
        if (holder instanceof NormalViewHolder) {
            ((NormalViewHolder) holder).setData((NormalBean) get(position));
        } else if (holder instanceof TestViewHolder) {
            ((TestViewHolder) holder).setData((TestBean) get(position));
        }
    }


}
