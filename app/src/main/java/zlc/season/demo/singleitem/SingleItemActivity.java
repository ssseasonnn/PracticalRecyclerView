package zlc.season.demo.singleitem;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import zlc.season.demo.R;
import zlc.season.practicalrecyclerview.ConfigureAdapter;
import zlc.season.practicalrecyclerview.PracticalRecyclerView;
import zlc.season.practicalrecyclerview.SectionItemImpl;

public class SingleItemActivity extends AppCompatActivity {

    @BindView(R.id.recycler)
    PracticalRecyclerView mRecycler;
    @BindView(R.id.activity_single_item)
    RelativeLayout mActivitySingleItem;

    private SingleItemAdapter mAdapter;
    private SingleItemPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_item);
        ButterKnife.bind(this);

        configurePresenter();
        configureRecyclerView();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.unsubscribeAll();
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
                    mAdapter.addAll(list);
                } else {
                    mAdapter.addAll(list);
                }
            }

            @Override
            public void onDataLoadFailed(boolean isRefresh) {
                if (isRefresh) {
                    mAdapter.showError();
                } else {
                    mAdapter.loadMoreFailed();
                }
            }
        });
    }

    private void configureRecyclerView() {
        mRecycler.setLayoutManager(new LinearLayoutManager(this));
        mRecycler.setAdapterWithLoading(mAdapter);
        mRecycler.configureView(new ConfigureAdapter() {
            @Override
            public void configureErrorView(View errorView) {
                super.configureErrorView(errorView);
                errorView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mPresenter.loadData(true);
                    }
                });
            }

            @Override
            public void configureLoadMoreErrorView(View loadMoreErrorView) {
                super.configureLoadMoreErrorView(loadMoreErrorView);
                loadMoreErrorView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mAdapter.resumeLoadMore();
                        mPresenter.loadData(false);
                    }
                });
            }
        });

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
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.loadData(true);
    }

    class Header extends SectionItemImpl {
        @BindView(R.id.like)
        Button mLike;
        @BindView(R.id.boring)
        Button mBoring;

        @Override
        public View createView(ViewGroup parent) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.header_item, parent, false);
            ButterKnife.bind(this, view);
            return view;
        }

        @OnClick({R.id.like, R.id.boring})
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.like:
                    Toast.makeText(SingleItemActivity.this, "like", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.boring:
                    break;
            }
        }
    }
}
