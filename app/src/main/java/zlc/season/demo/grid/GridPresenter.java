package zlc.season.demo.grid;

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

/**
 * Author: Season(ssseasonnn@gmail.com)
 * Date: 2016/10/10
 * Time: 11:42
 * FIXME
 */
class GridPresenter {
    private static int count = -1;
    private CompositeSubscription mSubscriptions;
    private GridView mView;
    private Context mContext;

    GridPresenter(Context context) {
        mContext = context;
        mSubscriptions = new CompositeSubscription();
    }

    void setDataLoadCallBack(GridView gridView) {
        mView = gridView;
    }

    void unsubscribeAll() {
        mSubscriptions.clear();
    }

    void loadData(final boolean isRefresh) {
        Subscription subscription = createObservable()
                .subscribeOn(Schedulers.io())
                .delay(2, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<GridBean>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.w("SingleItemPresenter", e);
                        mView.onDataLoadFailed(isRefresh);
                    }

                    @Override
                    public void onNext(List<GridBean> gridBeen) {
                        mView.onDataLoadSuccess(gridBeen, isRefresh);
                    }
                });
        mSubscriptions.add(subscription);
    }

    private Observable<List<GridBean>> createObservable() {
        count++;
        count %= 6;
        return Observable.create(new Observable.OnSubscribe<List<GridBean>>() {
            @Override
            public void call(Subscriber<? super List<GridBean>> subscriber) {
                if (count == 3) {
                    subscriber.onError(new Throwable("on error"));
                    return;
                }
                if (count == 5) {
                    subscriber.onNext(new ArrayList<GridBean>());
                    return;
                }
                List<GridBean> mData = new ArrayList<>();
                for (int i = count * 5; i < count * 5 + 2; i++) {
                    GridBean bean = new GridBean(i + "");
                    mData.add(bean);
                }
                subscriber.onNext(mData);
                subscriber.onCompleted();

            }
        });
    }
}
