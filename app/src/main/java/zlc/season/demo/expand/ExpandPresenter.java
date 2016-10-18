package zlc.season.demo.expand;

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;

import com.google.gson.Gson;

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
 * Date: 2016/10/10
 * Time: 11:42
 * FIXME
 */
class ExpandPresenter {
    private CompositeSubscription mSubscriptions;
    private ExpandView mView;
    private Context mContext;

    ExpandPresenter(Context context) {
        mContext = context;
        mSubscriptions = new CompositeSubscription();
    }

    void setDataLoadCallBack(ExpandView gridView) {
        mView = gridView;
    }

    void unsubscribeAll() {
        mSubscriptions.clear();
    }

    void loadData() {
        Subscription subscription = createObservable()
                .subscribeOn(Schedulers.io())
                .delay(2, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MockResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.w("SingleItemPresenter", e);
                        mView.onDataLoadFailed();
                    }

                    @Override
                    public void onNext(MockResponse response) {
                        List<ParentBean> parent = response.mData;
                        mView.onDataLoadSuccess(parent);
                    }
                });
        mSubscriptions.add(subscription);
    }

    private Observable<MockResponse> createObservable() {
        return Observable.create(new Observable.OnSubscribe<MockResponse>() {
            @Override
            public void call(Subscriber<? super MockResponse> subscriber) {
                Resources res = mContext.getResources();
                final String mockResponse = res.getString(R.string.mock_response);
                MockResponse response = new Gson().fromJson(mockResponse, MockResponse.class);
                subscriber.onNext(response);
                subscriber.onCompleted();
            }
        });
    }
}
