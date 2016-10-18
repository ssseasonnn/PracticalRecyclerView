package zlc.season.demo.expand;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

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
class ParentViewHolder extends AbstractViewHolder<ParentBean> {

    @BindView(R.id.text)
    TextView mText;
    @BindView(R.id.image)
    ImageView mImageView;

    private ParentBean parent;
    private List<ChildBean> child;

    private Context mContext;
    private ExpandAdapter mAdapter;

    ParentViewHolder(AbstractAdapter adapter, ViewGroup parent) {
        super(parent, R.layout.parent_item);
        ButterKnife.bind(this, itemView);
        mContext = parent.getContext();
        mAdapter = (ExpandAdapter) adapter;
    }

    @Override
    public void setData(ParentBean data) {
        mText.setText(String.valueOf(data.text));
        child = data.mChild;
        parent = data;
    }

    @OnClick(R.id.text)
    public void onClick() {
        if (parent.isExpand) {
            mAdapter.removeBack(getAdapterPosition(), child.size());
            parent.isExpand = false;
            Picasso.with(mContext).load(R.drawable.ic_keyboard_arrow_down).into(mImageView);
        } else {
            mAdapter.insertAllBack(getAdapterPosition(), child);
            parent.isExpand = true;
            Picasso.with(mContext).load(R.drawable.ic_keyboard_arrow_up).into(mImageView);
        }
    }
}
