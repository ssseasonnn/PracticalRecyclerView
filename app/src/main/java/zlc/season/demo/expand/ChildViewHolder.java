package zlc.season.demo.expand;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import zlc.season.demo.R;
import zlc.season.practicalrecyclerview.AbstractViewHolder;

/**
 * Author: Season(ssseasonnn@gmail.com)
 * Date: 2016/10/17
 * Time: 15:31
 * FIXME
 */
class ChildViewHolder extends AbstractViewHolder<ChildBean> {
    @BindView(R.id.text)
    TextView mText;

    private Context mContext;

    ChildViewHolder(ViewGroup parent) {
        super(parent, R.layout.child_item);
        ButterKnife.bind(this, itemView);
        mContext = parent.getContext();
    }

    @Override
    public void setData(ChildBean data) {
        mText.setText(String.valueOf(data.text));
    }

    @OnClick(R.id.text)
    public void onClick() {
        Toast.makeText(mContext, "i am child view", Toast.LENGTH_SHORT).show();
    }
}
