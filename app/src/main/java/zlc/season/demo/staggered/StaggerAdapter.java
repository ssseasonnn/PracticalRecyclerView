package zlc.season.demo.staggered;

import android.view.ViewGroup;

import zlc.season.practicalrecyclerview.AbstractAdapter;

/**
 * Author: Season(ssseasonnn@gmail.com)
 * Date: 2016/10/10
 * Time: 13:47
 * FIXME
 */
public class StaggerAdapter extends AbstractAdapter<StaggerBean, StaggerViewHolder> {
    @Override
    protected StaggerViewHolder onNewCreateViewHolder(ViewGroup parent, int viewType) {
        return new StaggerViewHolder(parent);
    }

    @Override
    protected void onNewBindViewHolder(StaggerViewHolder holder, int position) {
        holder.setData(get(position));
    }
}
