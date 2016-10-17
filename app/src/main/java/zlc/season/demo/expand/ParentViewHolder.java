package zlc.season.demo.expand;

import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import zlc.season.demo.R;
import zlc.season.practicalrecyclerview.AbstractAdapter;
import zlc.season.practicalrecyclerview.AbstractViewHolder;

/**
 * Author: Season(ssseasonnn@gmail.com)
 * Date: 2016/10/17
 * Time: 15:30
 * FIXME
 */
public class ParentViewHolder extends AbstractViewHolder<ParentBean> {

    List<ChildBean> child;
    @BindView(R.id.text)
    TextView mText;

    private ExpandAdapter mAdapter;

    public ParentViewHolder(AbstractAdapter adapter, ViewGroup parent) {
        super(adapter, parent, R.layout.parent_item);
        ButterKnife.bind(this, itemView);
        mAdapter = (ExpandAdapter) adapter;
    }

    @Override
    public void setData(ParentBean data) {
        mText.setText(data.text + "");
        child = data.mChild;
    }

    @OnClick(R.id.text)
    public void onClick() {
        mAdapter.insertAllData(getAdapterPosition()+1, child);
        mAdapter.notifyDataSetChanged();
    }
}
