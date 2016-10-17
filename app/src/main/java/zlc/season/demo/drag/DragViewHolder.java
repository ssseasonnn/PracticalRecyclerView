package zlc.season.demo.drag;

import android.content.Context;
import android.view.MotionEvent;
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
import zlc.season.practicalrecyclerview.ItemTouchHelperProvider;

/**
 * Author: Season(ssseasonnn@gmail.com)
 * Date: 2016/10/12
 * Time: 11:43
 * FIXME
 */
public class DragViewHolder extends AbstractViewHolder<DragBean> {
    @BindView(R.id.text)
    TextView mText;
    @BindView(R.id.reorder)
    ImageView mReorder;
    private Context mContext;

    public DragViewHolder(ViewGroup parent) {
        super(parent, R.layout.drag_item);
        ButterKnife.bind(this, itemView);
        mContext = parent.getContext();

        //触摸拖拽
        mReorder.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                ItemTouchHelperProvider.getInstance().startDrag(DragViewHolder.this);
                return true;
            }
        });

        //长按拖拽
        //        mText.setOnLongClickListener(new View.OnLongClickListener() {
        //            @Override
        //            public boolean onLongClick(View v) {
        //                ItemTouchHelperProvider.getInstance().startDrag(DragViewHolder.this);
        //                return true;
        //            }
        //        });
    }

    @Override
    public void setData(DragBean data) {
        mText.setText(data.text);
        if (data.status) {
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
