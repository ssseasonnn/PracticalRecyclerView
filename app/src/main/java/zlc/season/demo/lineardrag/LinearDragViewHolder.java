package zlc.season.demo.lineardrag;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import zlc.season.demo.R;
import zlc.season.practicalrecyclerview.AbstractViewHolder;

/**
 * Author: Season(ssseasonnn@gmail.com)
 * Date: 2016/10/12
 * Time: 11:43
 * FIXME
 */
public class LinearDragViewHolder extends AbstractViewHolder<LinearDragBean> {
    @BindView(R.id.text)
    TextView mText;
    @BindView(R.id.reorder)
    ImageView mReorder;
    private Context mContext;

    public LinearDragViewHolder(ViewGroup parent) {
        super(parent, R.layout.linear_drag_item);
        ButterKnife.bind(this, itemView);
        mContext = parent.getContext();
    }

    @Override
    public void setData(LinearDragBean data) {
        mText.setText(data.text);
        if (data.status) {
            mReorder.setVisibility(View.VISIBLE);
        } else {
            mReorder.setVisibility(View.GONE);
        }
    }

    public void updateStatus(boolean status) {
        if (status) {
            mReorder.setVisibility(View.VISIBLE);
        } else {
            mReorder.setVisibility(View.GONE);
        }
    }

    @OnClick(R.id.text)
    public void onClick() {
        Toast.makeText(mContext, mText.getText().toString(), Toast.LENGTH_SHORT).show();
    }
}
