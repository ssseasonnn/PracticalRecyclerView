package zlc.season.demo.drag;

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
 * Date: 2016/10/12
 * Time: 11:46
 * FIXME
 */
public class DragPresenter {

    private CompositeSubscription mSubscriptions;
    private DragView mView;
    private Context mContext;

    DragPresenter(Context context) {
        mContext = context;
        mSubscriptions = new CompositeSubscription();
    }

    void setDataLoadCallBack(DragView view) {
        mView = view;
    }

    void unsubscribeAll() {
        mSubscriptions.clear();
    }

    void loadData() {
        Subscription subscription = createObservable()
                .subscribeOn(Schedulers.io())
                .delay(2, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<DragBean>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.w("SingleItemPresenter", e);
                        mView.onDataLoadFailed();
                    }

                    @Override
                    public void onNext(List<DragBean> list) {
                        mView.onDataLoadSuccess(list);
                    }
                });
        mSubscriptions.add(subscription);
    }

    private Observable<List<DragBean>> createObservable() {
        return Observable.create(new Observable.OnSubscribe<List<DragBean>>() {
            @Override
            public void call(Subscriber<? super List<DragBean>> subscriber) {
                List<DragBean> mData = new ArrayList<>();
                for (int i = 0; i < 5; i++) {
                    DragBean bean = new DragBean(i + "");
                    mData.add(bean);
                }
                subscriber.onNext(mData);
                subscriber.onCompleted();
            }
        });
    }
}
