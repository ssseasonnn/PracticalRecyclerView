package zlc.season.practicalrecyclerview;

import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Author: Season(ssseasonnn@gmail.com)
 * Date: 2016/9/21
 * Time: 14:35
 * FIXME
 */
public abstract class AbstractViewHolder<T extends ItemType> extends RecyclerView.ViewHolder  {


    public AbstractViewHolder(View itemView) {
        super(itemView);
    }

    public AbstractViewHolder(ViewGroup parent, @LayoutRes int res) {
        super(LayoutInflater.from(parent.getContext()).inflate(res, parent, false));
    }

    public abstract void setData(T data);
}
