package zlc.season.demo.lineardrag;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RelativeLayout;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import zlc.season.demo.Header;
import zlc.season.demo.R;
import zlc.season.practicalrecyclerview.ItemDiffRule;
import zlc.season.practicalrecyclerview.PracticalRecyclerView;

public class LinearDragActivity extends AppCompatActivity {

    @BindView(R.id.recycler)
    PracticalRecyclerView mRecycler;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.content_item_drag)
    RelativeLayout mContentItemDrag;
    @BindView(R.id.fab)
    FloatingActionButton mFab;

    private boolean dragEnabled = false;

    private LinearDragAdapter mAdapter;
    private LinearDragPresenter mPresenter;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.drag_menu, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem menuItem = menu.findItem(R.id.action_edit_or_save);
        menuItem.setTitle("编辑");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id) {
            case R.id.action_edit_or_save:
                dragEnabled = !dragEnabled;
                if (dragEnabled) {
                    item.setTitle("保存");
                } else {
                    item.setTitle("编辑");
                }
                //开启或关闭拖拽
                mRecycler.enableDragItem(dragEnabled);

                List<? extends LinearDragBean> oldData = mAdapter.getAllDataCopy();
                List<? extends LinearDragBean> newData = mAdapter.getAllData();
                for (LinearDragBean each : newData) {
                    each.status = !each.status;
                }
                mAdapter.diffData(new LinearDragItemDiffRule(oldData, newData));
                //                mAdapter.notifyDataSetChanged();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linear_drag);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);

        mAdapter = new LinearDragAdapter();
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

        mPresenter = new LinearDragPresenter(this);
        mPresenter.setDataLoadCallBack(new LinearDragView() {
            @Override
            public void onDataLoadSuccess(List<LinearDragBean> list, boolean isRefresh) {
                if (isRefresh) {
                    mAdapter.clear();
                    mAdapter.addHeader(new Header());
                    //                    mAdapter.addFooter(new Header());
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

    class LinearDragItemDiffRule extends ItemDiffRule<LinearDragBean> {

        LinearDragItemDiffRule(List<? extends LinearDragBean> newData, List<? extends LinearDragBean> oldData) {
            super(newData, oldData);
        }

        @Override
        public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
            return mOldData.get(oldItemPosition).status == mNewData.get(newItemPosition).status;
        }

        @Nullable
        @Override
        public Object getChangePayload(int oldItemPosition, int newItemPosition) {
            LinearDragBean oldItem = mOldData.get(oldItemPosition);
            LinearDragBean newItem = mNewData.get(newItemPosition);

            Bundle diffBundle = new Bundle();
            if (newItem.status != oldItem.status) {
                diffBundle.putBoolean("status", newItem.status);
            }
            if (diffBundle.size() == 0) {
                return null;
            } else {
                return diffBundle;
            }
        }
    }
}
