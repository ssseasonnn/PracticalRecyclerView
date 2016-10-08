package zlc.season.demo.main;

import android.content.Context;
import android.content.res.Resources;

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
 * Date: 2016/10/8
 * Time: 14:34
 * FIXME
 */
class MainPresenter {

    private CompositeSubscription mSubscriptions;
    private Context mContext;
    private MainView mMainView;

    MainPresenter(Context context) {
        mContext = context;
        mSubscriptions = new CompositeSubscription();
    }

    void setDataLoadCallBack(MainView mainView) {
        mMainView = mainView;
    }

    void unsubscribeAll() {
        mSubscriptions.clear();
    }

    void loadData() {
        Subscription subscription = createObservable()
                .subscribeOn(Schedulers.io())
                .delay(2, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<MenuBean>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        mMainView.onLoadFailed();
                    }

                    @Override
                    public void onNext(List<MenuBean> menu) {
                        mMainView.onLoadSuccess(menu);
                    }
                });
        mSubscriptions.add(subscription);
    }

    private Observable<List<MenuBean>> createObservable() {
        Resources res = mContext.getResources();
        final String[] resource = res.getStringArray(R.array.mennu);
        return Observable.create(new Observable.OnSubscribe<List<MenuBean>>() {
            @Override
            public void call(Subscriber<? super List<MenuBean>> subscriber) {
                List<MenuBean> mData = new ArrayList<>();
                for (int i = 0; i < resource.length; i++) {
                    String aResource = resource[i];
                    MenuBean bean = new MenuBean();
                    bean.menu = aResource;
                    bean.type = i;
                    mData.add(bean);
                }
                subscriber.onNext(mData);
                subscriber.onCompleted();
            }
        });
    }
}
