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
public class DataSetObservable<E> extends Observable {
    public HeaderSegment header = new HeaderSegment();
    public DataSegment data = new DataSegment();
    public FooterSegment footer = new FooterSegment();
    public ExtraSegment extra = new ExtraSegment();

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

    void notifyContent() {
        super.setChanged();
        super.notifyObservers(new EventType.Content());
    }

    void notifyError() {
        super.setChanged();
        super.notifyObservers(new EventType.Error());
    }

    void notifyEmpty() {
        super.setChanged();
        super.notifyObservers(new EventType.Empty());
    }

    void notifyLoadMoreFailed() {
        super.setChanged();
        super.notifyObservers(new EventType.LoadMoreFailed());
    }

    void notifyResumeLoadMore() {
        super.setChanged();
        super.notifyObservers(new EventType.ResumeLoadMore());
    }

    void notifyNoMore() {
        super.setChanged();
        super.notifyObservers(new EventType.NoMore());
    }

    void notifyAutoLoadMore() {
        super.setChanged();
        super.notifyObservers(new EventType.AutoLoadMore());
    }

    abstract class Segment {

        abstract int size();

        abstract void clear();

        abstract void add(SectionItem item);

        abstract List<SectionItem> getAll();

        final SectionItem get(int adapterPosition) {
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
            return adapterPosition >= 0 && size() >= 0 && adapterPosition - positionImpl() < size();
        }

        final void set(int adapterPosition, SectionItem newItem) {
            if (is(adapterPosition)) {
                setImpl(adapterPosition - positionImpl(), newItem);
            }
        }

        final void remove(int adapterPosition) {
            if (is(adapterPosition)) {
                removeImpl(adapterPosition - positionImpl());
            }
        }

        final void remove(SectionItem needRemove) {
            if (size() != 0) {
                removeImpl(needRemove);
            }
        }

        protected abstract int positionImpl();

        protected abstract SectionItem getImpl(int position);

        protected abstract void setImpl(int position, SectionItem newItem);

        protected abstract void removeImpl(int position);

        protected abstract void removeImpl(SectionItem needRemove);

    }

    class HeaderSegment extends Segment {

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
        protected int positionImpl() {
            return 0;
        }

        @Override
        protected SectionItem getImpl(int position) {
            return mHeader.get(position);
        }

        @Override
        protected void setImpl(int position, SectionItem newItem) {
            mHeader.set(position, newItem);
        }

        @Override
        protected void removeImpl(int position) {
            mHeader.remove(position);
        }

        @Override
        protected void removeImpl(SectionItem needRemove) {
            mHeader.remove(needRemove);
        }
    }

    public class DataSegment {

        public int dataSize() {
            return mData.size();
        }

        public boolean isData(int adapterPosition) {
            return adapterPosition >= header.size() && adapterPosition < header.size() +
                    dataSize();
        }

        public int position() {
            if (dataSize() == 0) return -1;
            return header.size();
        }

        public List<E> getAll() {
            return mData;
        }

        public List<E> getDataSetCopy() {
            return new ArrayList<>(mData);
        }

        public E get(int dataPosition) {
            return mData.get(dataPosition);
        }

        public E getDataByAdapterPosition(int adapterPosition) {
            return mData.get(adapterPosition - header.size());
        }

        public void add(E data) {
            mData.add(data);
        }

        public void addAll(List<? extends E> data) {
            mData.addAll(data);
        }

        public E remove(int dataPosition) {
            return mData.remove(dataPosition);
        }

        public boolean remove(E data) {
            return mData.remove(data);
        }


        public void clear() {
            mData.clear();
        }
    }

    class FooterSegment extends Segment {

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
        protected int positionImpl() {
            return header.size() + data.dataSize();
        }

        @Override
        protected SectionItem getImpl(int position) {
            return mFooter.get(position);
        }

        @Override
        protected void setImpl(int position, SectionItem newItem) {
            mFooter.set(position, newItem);
        }

        @Override
        protected void removeImpl(int position) {
            mFooter.remove(position);
        }

        @Override
        protected void removeImpl(SectionItem needRemove) {
            mFooter.remove(needRemove);
        }
    }

    class ExtraSegment extends Segment {

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
        protected int positionImpl() {
            return header.size() + data.dataSize() + footer.size();
        }

        @Override
        protected SectionItem getImpl(int position) {
            return mExtra.get(position);
        }

        @Override
        protected void setImpl(int position, SectionItem newItem) {
            mExtra.set(position, newItem);
        }

        @Override
        protected void removeImpl(int position) {
            mExtra.remove(position);
        }

        @Override
        protected void removeImpl(SectionItem needRemove) {
            mExtra.remove(needRemove);
        }
    }

}
