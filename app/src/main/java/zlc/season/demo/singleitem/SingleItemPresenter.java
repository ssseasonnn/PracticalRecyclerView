package zlc.season.demo.singleitem;

import android.content.Context;
import android.content.res.Resources;
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
import zlc.season.demo.R;

/**
 * Author: Season(ssseasonnn@gmail.com)
 * Date: 2016/9/23
 * Time: 15:16
 * FIXME
 */
class SingleItemPresenter {
    private static int count = -1;
    private CompositeSubscription mSubscriptions;
    private SingleItemView mSingleItemView;
    private Context mContext;

    SingleItemPresenter(Context context) {
        mContext = context;
        mSubscriptions = new CompositeSubscription();
    }

    void setDataLoadCallBack(SingleItemView singleItemView) {
        mSingleItemView = singleItemView;
    }

    void unsubscribeAll() {
        mSubscriptions.clear();
    }

    void loadData(final boolean isRefresh) {
        Subscription subscription = createObservable()
                .subscribeOn(Schedulers.io())
                .delay(2, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<NormalBean>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.w("SingleItemPresenter", e);
                        mSingleItemView.onDataLoadFailed(isRefresh);
                    }

                    @Override
                    public void onNext(List<NormalBean> normalBeen) {
                        mSingleItemView.onDataLoadSuccess(normalBeen, isRefresh);
                    }
                });
        mSubscriptions.add(subscription);
    }

    private Observable<List<NormalBean>> createObservable() {
        count++;
        count %= 6;
        Resources res = mContext.getResources();
        final String[] images = res.getStringArray(R.array.image);
        final String[] titles = res.getStringArray(R.array.title);
        final String[] contents = res.getStringArray(R.array.content);
        return Observable.create(new Observable.OnSubscribe<List<NormalBean>>() {
            @Override
            public void call(Subscriber<? super List<NormalBean>> subscriber) {
                if (count == 3) {
                    subscriber.onError(new Throwable("on error"));
                    return;
                }
                if (count == 5) {
                    subscriber.onNext(new ArrayList<NormalBean>());
                    return;
                }
                List<NormalBean> mData = new ArrayList<>();
                for (int i = count * 5; i < count * 5 + 2; i++) {
                    NormalBean bean = new NormalBean(images[i], titles[i], contents[i]);
                    mData.add(bean);
                }
                subscriber.onNext(mData);
                subscriber.onCompleted();

            }
        });
    }

}
