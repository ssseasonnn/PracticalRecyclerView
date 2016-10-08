package zlc.season.demo.singleitem;

import android.view.ViewGroup;

import zlc.season.practicalrecyclerview.AbstractAdapter;

/**
 * Author: Season(ssseasonnn@gmail.com)
 * Date: 2016/9/22
 * Time: 09:47
 * FIXME
 */
class SingleItemAdapter extends AbstractAdapter<NormalBean, NormalViewHolder> {

    @Override
    protected NormalViewHolder onNewCreateViewHolder(ViewGroup parent, int viewType) {
        return new NormalViewHolder(parent);
    }

    @Override
    protected void onNewBindViewHolder(NormalViewHolder holder, int dataPosition) {
        holder.setData(get(dataPosition));
    }
}
