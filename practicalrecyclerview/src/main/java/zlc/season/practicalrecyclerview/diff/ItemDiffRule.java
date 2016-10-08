package zlc.season.practicalrecyclerview.diff;

import android.support.v7.util.DiffUtil;

import java.util.List;

/**
 * Author: Season(ssseasonnn@gmail.com)
 * Date: 2016/9/29
 * Time: 10:15
 * FIXME
 */
public class ItemDiffRule<E> extends DiffUtil.Callback {

    private List<? extends E> mOldData;
    private List<? extends E> mNewData;

    public ItemDiffRule(List<? extends E> newData, List<? extends E> oldData) {
        mNewData = newData;
        mOldData = oldData;
    }

    @Override
    public int getOldListSize() {
        return mOldData.size();
    }

    @Override
    public int getNewListSize() {
        return mNewData.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return mOldData.get(oldItemPosition).equals(mNewData.get(newItemPosition));
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return true;
    }
}
