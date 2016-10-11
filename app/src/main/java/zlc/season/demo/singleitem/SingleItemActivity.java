package zlc.season.demo.singleitem;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.Menu;
import android.view.MenuItem;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import zlc.season.demo.Header;
import zlc.season.demo.R;
import zlc.season.practicalrecyclerview.PracticalRecyclerView;

public class SingleItemActivity extends AppCompatActivity {

    @BindView(R.id.recycler)
    PracticalRecyclerView mRecycler;

    private boolean isNoMoreEnabled = false;
    private boolean isLoadMoreEnabled = false;
    private boolean isLoadMoreFailedEnabled = false;

    private boolean isAutoLoadEnabled = true;

    private SingleItemAdapter mAdapter;
    private SingleItemPresenter mPresenter;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem noMore = menu.findItem(R.id.action_no_more_enabled);
        MenuItem loadMore = menu.findItem(R.id.action_load_more_enabled);
        MenuItem loadMoreFailed = menu.findItem(R.id.action_load_more_failed_enabled);
        MenuItem autoLoad = menu.findItem(R.id.action_auto_load_enabled);
        noMore.setChecked(isNoMoreEnabled);
        loadMore.setChecked(isLoadMoreEnabled);
        loadMoreFailed.setChecked(isLoadMoreFailedEnabled);
        autoLoad.setChecked(isAutoLoadEnabled);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id) {
            case R.id.action_no_more_enabled:
                isNoMoreEnabled = !item.isChecked();
                item.setChecked(isNoMoreEnabled);
                mRecycler.setNoMoreViewEnabled(!isNoMoreEnabled);
                return true;
            case R.id.action_load_more_enabled:
                isLoadMoreEnabled = !item.isChecked();
                item.setChecked(isLoadMoreEnabled);
                mRecycler.setLoadMoreViewEnabled(!isLoadMoreEnabled);
                return true;
            case R.id.action_load_more_failed_enabled:
                isLoadMoreFailedEnabled = !item.isChecked();
                item.setChecked(isLoadMoreFailedEnabled);
                mRecycler.setLoadMoreFailedViewEnabled(!isLoadMoreFailedEnabled);
                return true;
            case R.id.action_auto_load_enabled:
                isAutoLoadEnabled = !item.isChecked();
                item.setChecked(isAutoLoadEnabled);
                mRecycler.setAutoLoadEnable(isAutoLoadEnabled);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

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
}
