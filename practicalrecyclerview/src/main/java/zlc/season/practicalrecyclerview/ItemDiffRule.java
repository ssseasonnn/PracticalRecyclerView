package zlc.season.practicalrecyclerview;

import android.support.annotation.Nullable;
import android.support.v7.util.DiffUtil;

/**
 * Author: Season(ssseasonnn@gmail.com)
 * Date: 2016/9/29
 * Time: 10:15
 * FIXME
 */
public class ItemDiffRule<E> extends DiffUtil.Callback {

    protected DataSetObservable<E> mOldDataSet;
    protected DataSetObservable<E> mNewDataSet;


    public ItemDiffRule(DataSetObservable<E> oldData, DataSetObservable<E> newData) {
        mOldDataSet = oldData;
        mNewDataSet = newData;
    }

    @Override
    public int getOldListSize() {
        return mOldDataSet.totalSize();
    }

    @Override
    public int getNewListSize() {
        return mNewDataSet.totalSize();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        if (mOldDataSet.header.is(oldItemPosition) && mNewDataSet.header.is(newItemPosition)) {
            return mOldDataSet.header.get(oldItemPosition).equals(mNewDataSet.header.get(newItemPosition));
        } else if (mOldDataSet.data.is(oldItemPosition) && mNewDataSet.data.is(newItemPosition)) {
            return mOldDataSet.data.get(oldItemPosition).equals(mNewDataSet.data.get(newItemPosition));
        } else if (mOldDataSet.footer.is(oldItemPosition) && mNewDataSet.footer.is(newItemPosition)) {
            return mOldDataSet.footer.get(oldItemPosition).equals(mNewDataSet.footer.get(newItemPosition));
        } else if (mOldDataSet.extra.is(oldItemPosition) && mNewDataSet.extra.is(newItemPosition)) {
            return mOldDataSet.extra.get(oldItemPosition).equals(mNewDataSet.extra.get(newItemPosition));
        }
        return false;
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return true;
    }

    @Nullable
    @Override
    public Object getChangePayload(int oldItemPosition, int newItemPosition) {
        return null;
    }
}
