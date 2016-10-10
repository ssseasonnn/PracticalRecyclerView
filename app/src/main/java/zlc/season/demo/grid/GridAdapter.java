package zlc.season.demo.grid;

import android.view.ViewGroup;

import zlc.season.practicalrecyclerview.AbstractAdapter;

/**
 * Author: Season(ssseasonnn@gmail.com)
 * Date: 2016/10/10
 * Time: 10:25
 * FIXME
 */
public class GridAdapter extends AbstractAdapter<GridBean, GridViewHolder> {
    @Override
    protected GridViewHolder onNewCreateViewHolder(ViewGroup parent, int viewType) {
        return new GridViewHolder(parent);
    }

    @Override
    protected void onNewBindViewHolder(GridViewHolder holder, int position) {
        holder.setData(get(position));
    }
}
