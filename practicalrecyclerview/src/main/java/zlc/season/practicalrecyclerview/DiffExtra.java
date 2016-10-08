package zlc.season.practicalrecyclerview;

import android.support.v7.util.DiffUtil;

import java.util.List;

/**
 * Author: Season(ssseasonnn@gmail.com)
 * Date: 2016/9/29
 * Time: 10:26
 * FIXME
 */
public class DiffExtra extends DiffUtil.Callback {

    private List<SectionItem> mOldData;
    private List<SectionItem> mNewData;

    public DiffExtra(List<SectionItem> newData, List<SectionItem> oldData) {
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
