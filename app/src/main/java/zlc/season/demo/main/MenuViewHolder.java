package zlc.season.demo.main;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import zlc.season.demo.R;
import zlc.season.demo.singleitem.SingleItemActivity;
import zlc.season.practicalrecyclerview.viewholder.BaseViewHolder;

/**
 * Author: Season(ssseasonnn@gmail.com)
 * Date: 2016/10/8
 * Time: 14:22
 * FIXME
 */
class MenuViewHolder extends BaseViewHolder<MenuBean> {
    @BindView(R.id.menu)
    TextView mMenu;
    @BindView(R.id.container)
    LinearLayout mLayout;

    private Context mContext;
    private MenuBean mBean;

    MenuViewHolder(ViewGroup parent) {
        super(parent, R.layout.menu_item);
        ButterKnife.bind(this, itemView);
        mContext = parent.getContext();
    }

    @OnClick(R.id.container)
    public void onClick(View view) {
        int type = mBean.type;
        switch (type) {
            case 0:
                mContext.startActivity(new Intent(mContext, SingleItemActivity.class));
                break;
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
        }

    }

    @Override
    public void setData(MenuBean data) {
        mBean = data;
        mMenu.setText(data.menu);
    }
}
