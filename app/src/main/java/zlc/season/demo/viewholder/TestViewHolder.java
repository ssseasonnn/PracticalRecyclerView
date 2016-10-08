package zlc.season.demo.viewholder;

import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import zlc.season.demo.R;
import zlc.season.demo.data.TestBean;
import zlc.season.practicalrecyclerview.viewholder.BaseViewHolder;

/**
 * Author: Season(ssseasonnn@gmail.com)
 * Date: 2016/9/22
 * Time: 09:59
 * FIXME
 */
public class TestViewHolder extends BaseViewHolder<TestBean> {

    @BindView(R.id.button)
    Button mButton;
    @BindView(R.id.textView)
    TextView mTextView;

    public TestViewHolder(ViewGroup parent) {
        super(parent, R.layout.test_item);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void setData(TestBean data) {
        mTextView.setText(data.getText());
        mButton.setText(data.getButton());
    }
}