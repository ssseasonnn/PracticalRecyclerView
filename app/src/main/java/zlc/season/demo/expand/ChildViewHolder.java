package zlc.season.demo.expand;

import android.view.ViewGroup;
import android.widget.TextView;

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
public class ChildViewHolder extends AbstractViewHolder<ChildBean> {
    @BindView(R.id.text)
    TextView mText;

    public ChildViewHolder(ViewGroup parent) {
        super(parent, R.layout.child_item);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void setData(ChildBean data) {
        mText.setText(data.text + "");
    }

    @OnClick(R.id.text)
    public void onClick() {
    }
}
