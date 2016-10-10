package zlc.season.demo.staggered;

import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import zlc.season.demo.R;
import zlc.season.practicalrecyclerview.AbstractViewHolder;

/**
 * Author: Season(ssseasonnn@gmail.com)
 * Date: 2016/10/10
 * Time: 13:38
 * FIXME
 */
public class StaggerViewHolder extends AbstractViewHolder<StaggerBean> {
    @BindView(R.id.text)
    TextView mText;

    public StaggerViewHolder(ViewGroup parent) {
        super(parent, R.layout.stagger_item);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void setData(StaggerBean data) {
        mText.setText(data.text);

        int height = (int) (100 + Math.random() * 300);
        ViewGroup.LayoutParams lp = itemView.getLayoutParams();
        lp.height = height;

        itemView.setLayoutParams(lp);
    }

    @OnClick(R.id.text)
    public void onClick() {
    }
}
