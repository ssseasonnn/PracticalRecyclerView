package zlc.season.demo.main;

import android.view.ViewGroup;

import zlc.season.practicalrecyclerview.AbstractAdapter;

/**
 * Author: Season(ssseasonnn@gmail.com)
 * Date: 2016/10/8
 * Time: 14:19
 * FIXME
 */
class MainAdapter extends AbstractAdapter<MenuBean, MenuViewHolder> {
    @Override
    protected MenuViewHolder onNewCreateViewHolder(ViewGroup parent, int viewType) {
        return new MenuViewHolder(parent);
    }

    @Override
    protected void onNewBindViewHolder(MenuViewHolder holder, int position) {
        holder.setData(get(position));
    }
}
