package zlc.season.demo.main;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import zlc.season.demo.R;
import zlc.season.practicalrecyclerview.PracticalRecyclerView;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.fab)
    FloatingActionButton mFab;
    @BindView(R.id.recycler)
    PracticalRecyclerView mRecycler;

    private MainAdapter mAdapter;
    private MainPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);

        mAdapter = new MainAdapter();
        mPresenter = new MainPresenter(this);

        mRecycler.setLayoutManager(new LinearLayoutManager(this));
        mRecycler.setAdapterWithLoading(mAdapter);

        mPresenter.setDataLoadCallBack(new MainView() {
            @Override
            public void onLoadSuccess(List<MenuBean> menu) {
                mAdapter.clear();
                mAdapter.addAll(menu);
            }

            @Override
            public void onLoadFailed() {
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
