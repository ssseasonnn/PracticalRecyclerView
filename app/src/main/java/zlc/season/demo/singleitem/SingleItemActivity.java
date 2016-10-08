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

import zlc.season.demo.data.NormalBean;
import zlc.season.practicalrecyclerview.ConfigureViewAdapter;
import zlc.season.practicalrecyclerview.SectionItem;
import zlc.season.practicalrecyclerview.SuperRecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SingleItemActivity extends AppCompatActivity {

    @BindView(zlc.season.demo.R.id.super_recycler)
    SuperRecyclerView mSuperRecycler;
    @BindView(zlc.season.demo.R.id.activity_single_item)
    RelativeLayout mActivitySingleItem;

    private SingleItemAdapter mSingleItemAdapter;
    private SingleItemPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(zlc.season.demo.R.layout.activity_single_item);
        ButterKnife.bind(this);

        mSingleItemAdapter = new SingleItemAdapter();
        mSuperRecycler.setLayoutManager(new LinearLayoutManager(this));
        mSuperRecycler.setAdapterWithLoading(mSingleItemAdapter);

        mSuperRecycler.setConfigureView(new ConfigureViewAdapter() {
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
        });

        mPresenter = new SingleItemPresenter(this, new SingleItemView() {
            @Override
            public void onDataLoadSuccess(List<NormalBean> list, boolean isRefresh) {
                if (isRefresh) {
                    mSingleItemAdapter.clear();
                    mSingleItemAdapter.addHeader(new Header());
                    mSingleItemAdapter.addAll(list);
                } else {
                    mSingleItemAdapter.addAll(list);
                }
            }

            @Override
            public void onDataLoadFailed(boolean isRefresh) {
                if (isRefresh) {
                    mSingleItemAdapter.showError();
                } else {
                    mSingleItemAdapter.loadMoreFailed();
                }
            }
        });


        mSuperRecycler.setRefreshListener(new SuperRecyclerView.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.loadData(true);
            }
        });

        mSuperRecycler.setLoadMoreListener(new SuperRecyclerView.OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                mPresenter.loadData(false);
            }
        });
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

    class Header implements SectionItem {
        @BindView(zlc.season.demo.R.id.like)
        Button mLike;
        @BindView(zlc.season.demo.R.id.boring)
        Button mBoring;

        @Override
        public View createView(ViewGroup parent) {
            View view = LayoutInflater.from(parent.getContext()).inflate(zlc.season.demo.R.layout.header_item, parent, false);
            ButterKnife.bind(this, view);
            return view;
        }

        @Override
        public void onBind() {

        }

        @OnClick({zlc.season.demo.R.id.like, zlc.season.demo.R.id.boring})
        public void onClick(View view) {
            switch (view.getId()) {
                case zlc.season.demo.R.id.like:
                    Toast.makeText(SingleItemActivity.this, "like", Toast.LENGTH_SHORT).show();
                    break;
                case zlc.season.demo.R.id.boring:
                    break;
            }
        }
    }
}
