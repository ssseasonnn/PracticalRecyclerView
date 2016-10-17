package zlc.season.practicalrecyclerview;


import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

/**
 * Author: Season(ssseasonnn@gmail.com)
 * Date: 2016/9/22
 * Time: 14:24
 * FIXME
 */
class DataSetObservable<E> extends Observable {
    Segment<SectionItem> header = new HeaderSegment();
    Segment<E> data = new DataSegment();
    Segment<SectionItem> footer = new FooterSegment();
    Segment<SectionItem> extra = new ExtraSegment();

    private List<SectionItem> mHeader = new ArrayList<>();
    private List<E> mData = new ArrayList<>();
    private List<SectionItem> mFooter = new ArrayList<>();
    private List<SectionItem> mExtra = new ArrayList<>();


    //------------------------------
    //|position    size     item   |
    //|----------------------------|
    //|    0               header0 |
    //|    1        2      header1 |
    //|----------------------------|
    //|    2               data0   |
    //|    3               data1   |
    //|    4               data2   |
    //|    5        4      data3   |
    //|----------------------------|
    //|    6               footer0 |
    //|    7        2      footer1 |
    //|----------------------------|
    //|    8               extra0  |
    //|    9        2      extra1  |
    //------------------------------

    int totalSize() {
        return mHeader.size() + mData.size() + mFooter.size() + mExtra.size();
    }

    void clear() {
        mHeader.clear();
        mData.clear();
        mFooter.clear();
        mExtra.clear();
    }

    void notifyLoading() {
        super.setChanged();
        super.notifyObservers(new Bridge.Loading());
    }

    void notifyContent() {
        super.setChanged();
        super.notifyObservers(new Bridge.Content());
    }

    void notifyError() {
        super.setChanged();
        super.notifyObservers(new Bridge.Error());
    }

    void notifyEmpty() {
        super.setChanged();
        super.notifyObservers(new Bridge.Empty());
    }

    void notifyLoadMoreFailed() {
        super.setChanged();
        super.notifyObservers(new Bridge.LoadMoreFailed());
    }

    void notifyResumeLoadMore() {
        super.setChanged();
        super.notifyObservers(new Bridge.ResumeLoadMore());
    }

    void notifyNoMore() {
        super.setChanged();
        super.notifyObservers(new Bridge.NoMore());
    }

    void notifyAutoLoadMore() {
        super.setChanged();
        super.notifyObservers(new Bridge.AutoLoadMore());
    }

    void notifyManualLoadMore() {
        super.setChanged();
        super.notifyObservers(new Bridge.ManualLoadMore());
    }

    abstract class Segment<T> {

        abstract int size();

        abstract void clear();

        abstract void add(T item);

        abstract void addAll(List<? extends T> items);

        abstract List<T> getAll();

        final T get(int adapterPosition) {
            if (is(adapterPosition)) {
                return getImpl(adapterPosition - positionImpl());
            } else {
                throw new NullPointerException();
            }
        }

        final int position() {
            if (size() == 0) return -1;
            return positionImpl();
        }

        final boolean is(int adapterPosition) {
            return adapterPosition >= 0 && size() > 0 && adapterPosition - positionImpl() < size()
                    && adapterPosition - positionImpl() >= 0;
        }

        final void set(int adapterPosition, T newItem) {
            if (is(adapterPosition)) {
                setImpl(adapterPosition - positionImpl(), newItem);
            }
        }

        final void remove(int adapterPosition) {
            if (is(adapterPosition)) {
                removeImpl(adapterPosition - positionImpl());
            }
        }

        final void remove(T needRemove) {
            if (size() != 0) {
                removeImpl(needRemove);
            }
        }

        @SuppressWarnings({"rawtypes", "unchecked"})
        void swap(int fromAdapterPosition, int toAdapterPosition) {
            final List l = getAll();
            fromAdapterPosition = fromAdapterPosition - positionImpl();
            toAdapterPosition = toAdapterPosition - positionImpl();
            l.set(fromAdapterPosition, l.set(toAdapterPosition, l.get(fromAdapterPosition)));
        }

        abstract int positionImpl();

        abstract T getImpl(int position);

        abstract void setImpl(int position, T newItem);

        abstract void removeImpl(int position);

        abstract void removeImpl(T needRemove);

    }

    private class HeaderSegment extends Segment<SectionItem> {

        @Override
        int size() {
            return mHeader.size();
        }

        @Override
        void clear() {
            mHeader.clear();
        }

        @Override
        List<SectionItem> getAll() {
            return mHeader;
        }

        @Override
        void add(SectionItem item) {
            mHeader.add(item);
        }

        @Override
        void addAll(List<? extends SectionItem> items) {
            mHeader.addAll(items);
        }

        @Override
        int positionImpl() {
            return 0;
        }

        @Override
        SectionItem getImpl(int position) {
            return mHeader.get(position);
        }

        @Override
        void setImpl(int position, SectionItem newItem) {
            mHeader.set(position, newItem);
        }

        @Override
        void removeImpl(int position) {
            mHeader.remove(position);
        }

        @Override
        void removeImpl(SectionItem needRemove) {
            mHeader.remove(needRemove);
        }
    }

    private class DataSegment extends Segment<E> {

        @Override
        int size() {
            return mData.size();
        }

        @Override
        void clear() {
            mData.clear();
        }

        @Override
        void add(E item) {
            mData.add(item);
        }

        @Override
        void addAll(List<? extends E> items) {
            mData.addAll(items);
        }

        @Override
        List<E> getAll() {
            return mData;
        }

        @Override
        int positionImpl() {
            return header.size();
        }

        @Override
        E getImpl(int position) {
            return mData.get(position);
        }

        @Override
        void setImpl(int position, E newItem) {
            mData.set(position, newItem);
        }

        @Override
        void removeImpl(int position) {
            mData.remove(position);
        }

        @Override
        void removeImpl(E needRemove) {
            mData.remove(needRemove);
        }
    }

    private class FooterSegment extends Segment<SectionItem> {

        @Override
        int size() {
            return mFooter.size();
        }

        @Override
        void clear() {
            mFooter.clear();
        }

        @Override
        List<SectionItem> getAll() {
            return mFooter;
        }

        @Override
        void add(SectionItem item) {
            mFooter.add(item);
        }

        @Override
        void addAll(List<? extends SectionItem> items) {
            mFooter.addAll(items);
        }

        @Override
        int positionImpl() {
            return header.size() + data.size();
        }

        @Override
        SectionItem getImpl(int position) {
            return mFooter.get(position);
        }

        @Override
        void setImpl(int position, SectionItem newItem) {
            mFooter.set(position, newItem);
        }

        @Override
        void removeImpl(int position) {
            mFooter.remove(position);
        }

        @Override
        void removeImpl(SectionItem needRemove) {
            mFooter.remove(needRemove);
        }
    }

    private class ExtraSegment extends Segment<SectionItem> {

        @Override
        int size() {
            return mExtra.size();
        }

        @Override
        void clear() {
            mExtra.clear();
        }

        @Override
        List<SectionItem> getAll() {
            return mExtra;
        }

        @Override
        void add(SectionItem item) {
            mExtra.add(item);
        }

        @Override
        void addAll(List<? extends SectionItem> items) {
            mExtra.addAll(items);
        }

        @Override
        int positionImpl() {
            return header.size() + data.size() + footer.size();
        }

        @Override
        SectionItem getImpl(int position) {
            return mExtra.get(position);
        }

        @Override
        void setImpl(int position, SectionItem newItem) {
            mExtra.set(position, newItem);
        }

        @Override
        void removeImpl(int position) {
            mExtra.remove(position);
        }

        @Override
        void removeImpl(SectionItem needRemove) {
            mExtra.remove(needRemove);
        }
    }

}
