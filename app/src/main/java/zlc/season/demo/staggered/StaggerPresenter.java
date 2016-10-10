package zlc.season.demo.staggered;

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
class StaggerPresenter {
    private static int count = -1;
    private CompositeSubscription mSubscriptions;
    private StaggerView mView;
    private Context mContext;

    StaggerPresenter(Context context) {
        mContext = context;
        mSubscriptions = new CompositeSubscription();
    }

    void setDataLoadCallBack(StaggerView staggerView) {
        mView = staggerView;
    }

    void unsubscribeAll() {
        mSubscriptions.clear();
    }

    void loadData(final boolean isRefresh) {
        Subscription subscription = createObservable()
                .subscribeOn(Schedulers.io())
                .delay(2, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<StaggerBean>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.w("SingleItemPresenter", e);
                        mView.onDataLoadFailed(isRefresh);
                    }

                    @Override
                    public void onNext(List<StaggerBean> list) {
                        mView.onDataLoadSuccess(list, isRefresh);
                    }
                });
        mSubscriptions.add(subscription);
    }

    private Observable<List<StaggerBean>> createObservable() {
        count++;
        count %= 6;
        return Observable.create(new Observable.OnSubscribe<List<StaggerBean>>() {
            @Override
            public void call(Subscriber<? super List<StaggerBean>> subscriber) {
                if (count == 3) {
                    subscriber.onError(new Throwable("on error"));
                    return;
                }
                if (count == 5) {
                    subscriber.onNext(new ArrayList<StaggerBean>());
                    return;
                }
                List<StaggerBean> mData = new ArrayList<>();
                for (int i = count * 5; i < count * 5 + 2; i++) {
                    StaggerBean bean = new StaggerBean(i + "");
                    mData.add(bean);
                }
                subscriber.onNext(mData);
                subscriber.onCompleted();

            }
        });
    }
}
