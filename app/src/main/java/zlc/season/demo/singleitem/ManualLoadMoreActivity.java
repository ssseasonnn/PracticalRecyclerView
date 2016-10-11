package zlc.season.demo.singleitem;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import zlc.season.demo.Header;
import zlc.season.demo.R;
import zlc.season.practicalrecyclerview.ConfigureAdapter;
import zlc.season.practicalrecyclerview.PracticalRecyclerView;

/**
 * Author: Season(ssseasonnn@gmail.com)
 * Date: 2016/10/11
 * Time: 15:24
 * FIXME
 */
public class ManualLoadMoreActivity extends AppCompatActivity {

    @BindView(R.id.recycler)
    PracticalRecyclerView mRecycler;

    private SingleItemAdapter mAdapter;
    private SingleItemPresenter mPresenter;

    private Button mNextPage;
    private LinearLayout mProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manual_load);
        ButterKnife.bind(this);

        configurePresenter();
        configureRecyclerView();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.unsubscribeAll();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.loadData(true);
    }

    private void configurePresenter() {
        mAdapter = new SingleItemAdapter();
        mPresenter = new SingleItemPresenter(this);
        mPresenter.setDataLoadCallBack(new SingleItemView() {
            @Override
            public void onDataLoadSuccess(List<NormalBean> list, boolean isRefresh) {
                if (isRefresh) {
                    mAdapter.clear();
                    mAdapter.addHeader(new Header());
                    // mAdapter.addFooter(new Header());
                    mAdapter.addAll(list);
                } else {
                    mAdapter.addAll(list);
                }
                setVisibility(View.VISIBLE, View.GONE);
            }

            @Override
            public void onDataLoadFailed(boolean isRefresh) {
                if (isRefresh) {
                    mAdapter.showError();
                } else {
                    mAdapter.loadMoreFailed();
                }
                setVisibility(View.VISIBLE, View.GONE);
            }
        });
    }

    private void configureRecyclerView() {
        //关闭自动加载更多, 改为手动触发
        mRecycler.setAutoLoadEnable(false);
        mRecycler.setLayoutManager(new LinearLayoutManager(this));
        mRecycler.setAdapterWithLoading(mAdapter);

        mRecycler.setRefreshListener(new PracticalRecyclerView.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.loadData(true);
            }
        });
        mRecycler.setLoadMoreListener(new PracticalRecyclerView.OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                mPresenter.loadData(false);
            }
        });

        mRecycler.configureView(new ConfigureAdapter() {
            @Override
            public void configureLoadMoreView(View loadMoreView) {
                super.configureLoadMoreView(loadMoreView);
                mNextPage = (Button) loadMoreView.findViewById(R.id.next_page);
                mProgress = (LinearLayout) loadMoreView.findViewById(R.id.loading);
                mNextPage.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        setVisibility(View.GONE, View.VISIBLE);
                        //手动触发加载更多
                        mAdapter.manualLoadMore();
                    }
                });
            }
        });
    }

    private void setVisibility(int visible1, int visible2) {
        mNextPage.setVisibility(visible1);
        mProgress.setVisibility(visible2);
    }
}