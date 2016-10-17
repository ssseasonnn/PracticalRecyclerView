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
import zlc.season.demo.drag.DragActivity;
import zlc.season.demo.grid.GridActivity;
import zlc.season.demo.multipleitem.MultiItemActivity;
import zlc.season.demo.singleitem.ManualLoadMoreActivity;
import zlc.season.demo.singleitem.SingleItemActivity;
import zlc.season.demo.staggered.StaggeredActivity;
import zlc.season.practicalrecyclerview.AbstractViewHolder;

/**
 * Author: Season(ssseasonnn@gmail.com)
 * Date: 2016/10/8
 * Time: 14:22
 * FIXME
 */
class MenuViewHolder extends AbstractViewHolder<MenuBean> {
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
                mContext.startActivity(new Intent(mContext, MultiItemActivity.class));
                break;
            case 2:
                mContext.startActivity(new Intent(mContext, GridActivity.class));
                break;
            case 3:
                mContext.startActivity(new Intent(mContext, StaggeredActivity.class));
                break;
            case 4:
                mContext.startActivity(new Intent(mContext, ManualLoadMoreActivity.class));
                break;
            case 5:
                mContext.startActivity(new Intent(mContext, DragActivity.class));
                break;
        }

    }

    @Override
    public void setData(MenuBean data) {
        mBean = data;
        mMenu.setText(data.menu);
    }
}
