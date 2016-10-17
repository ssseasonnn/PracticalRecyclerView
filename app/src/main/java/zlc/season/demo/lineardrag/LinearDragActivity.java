package zlc.season.demo.lineardrag;

import android.os.Bundle;
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
import zlc.season.demo.R;
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

    private boolean defaultLongPressEnabled = false;
    private boolean customTouchEnabled = false;
    private boolean swipeEnabled = false;

    private LinearDragAdapter mAdapter;
    private LinearDragPresenter mPresenter;

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem longPress = menu.findItem(R.id.long_press);
        MenuItem touch = menu.findItem(R.id.touch);
        MenuItem swipe = menu.findItem(R.id.swipe);
        longPress.setChecked(defaultLongPressEnabled);
        touch.setChecked(customTouchEnabled);
        swipe.setChecked(swipeEnabled);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id) {
            case R.id.long_press:
                defaultLongPressEnabled = !item.isChecked();
                item.setChecked(defaultLongPressEnabled);
                mRecycler.enableDragOrSwipe(true, defaultLongPressEnabled, swipeEnabled);
                return true;
            case R.id.touch:
                customTouchEnabled = !item.isChecked();
                item.setChecked(customTouchEnabled);
                mRecycler.enableDragOrSwipe(true, customTouchEnabled, swipeEnabled);
                return true;
            case R.id.swipe:
                swipeEnabled = !item.isChecked();
                item.setChecked(swipeEnabled);
                mRecycler.setLoadMoreFailedViewEnabled(!swipeEnabled);
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

        mPresenter = new LinearDragPresenter(this);
        mPresenter.setDataLoadCallBack(new LinearDragView() {
            @Override
            public void onDataLoadSuccess(List<LinearDragBean> list) {
                mAdapter.addAll(list);
            }

            @Override
            public void onDataLoadFailed() {
                mAdapter.showError();
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
        mPresenter.loadData();
    }
}
