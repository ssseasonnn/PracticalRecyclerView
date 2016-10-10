package zlc.season.demo.multipleitem;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;
import zlc.season.practicalrecyclerview.ItemType;

/**
 * Author: Season(ssseasonnn@gmail.com)
 * Date: 2016/10/10
 * Time: 12:07
 * FIXME
 */
public class MultiItemPresenter {
    private static int count = -1;
    private CompositeSubscription mSubscriptions;
    private MultiItemView mView;
    private Context mContext;

    MultiItemPresenter(Context context) {
        mContext = context;
        mSubscriptions = new CompositeSubscription();
    }

    void setDataLoadCallBack(MultiItemView itemView) {
        mView = itemView;
    }

    void unsubscribeAll() {
        mSubscriptions.clear();
    }

    void loadData(final boolean isRefresh) {
        Subscription subscription = createObservable()
                .subscribeOn(Schedulers.io())
                .delay(2, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<ItemType>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.w("SingleItemPresenter", e);
                        mView.onDataLoadFailed(isRefresh);
                    }

                    @Override
                    public void onNext(List<ItemType> list) {
                        mView.onDataLoadSuccess(list, isRefresh);
                    }
                });
        mSubscriptions.add(subscription);
    }

    private Observable<List<ItemType>> createObservable() {
        count++;
        count %= 6;
        return Observable.create(new Observable.OnSubscribe<List<ItemType>>() {
            @Override
            public void call(Subscriber<? super List<ItemType>> subscriber) {
                if (count == 3) {
                    subscriber.onError(new Throwable("on error"));
                    return;
                }
                if (count == 5) {
                    subscriber.onNext(new ArrayList<ItemType>());
                    return;
                }
                List<ItemType> mData = new ArrayList<>();
                for (int i = count * 5; i < count * 5 + 2; i++) {
                    if (i % 2 == 0) {
                        ItemType bean = new TypeOneBean("type one");
                        mData.add(bean);
                    } else {
                        ItemType bean = new TypeTwoBean("type two");
                        mData.add(bean);
                    }
                }
                subscriber.onNext(mData);
                subscriber.onCompleted();
            }
        });
    }
}
