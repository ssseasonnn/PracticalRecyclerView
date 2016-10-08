package zlc.season.practicalrecyclerview;

import android.support.annotation.LayoutRes;
import android.view.View;
import android.view.ViewGroup;

/**
 * Author: Season(ssseasonnn@gmail.com)
 * Date: 2016/9/27
 * Time: 10:09
 * FIXME
 */
public abstract class BaseViewHolder<T extends ItemType> extends AbstractViewHolder {

    public BaseViewHolder(View itemView) {
        super(itemView);
    }

    public BaseViewHolder(ViewGroup parent, @LayoutRes int res) {
        super(parent, res);
    }

    public abstract void setData(T data);
}
