package zlc.season.demo.expand;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import zlc.season.demo.R;
import zlc.season.practicalrecyclerview.PracticalRecyclerView;

public class ExpandActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.recycler)
    PracticalRecyclerView mRecycler;

    private ExpandAdapter mAdapter;
    private ExpandPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expand);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);
        mAdapter = new ExpandAdapter();
        mRecycler.setLayoutManager(new LinearLayoutManager(this));
        mRecycler.setAdapterWithLoading(mAdapter);

        mRecycler.setRefreshListener(new PracticalRecyclerView.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.loadData();
            }
        });

        mPresenter = new ExpandPresenter(this);
        mPresenter.setDataLoadCallBack(new ExpandView() {
            @Override
            public void onDataLoadSuccess(List<ParentBean> list) {
                mAdapter.clear();
//                mAdapter.addHeader(new Header());
                //                    mAdapter.addFooter(new Header());
                mAdapter.addAll(list);
            }

            @Override
            public void onDataLoadFailed() {
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
