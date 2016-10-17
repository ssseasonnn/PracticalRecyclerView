package zlc.season.demo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.bingoogolapple.bgabanner.BGABanner;
import zlc.season.practicalrecyclerview.SectionItem;

/**
 * Author: Season(ssseasonnn@gmail.com)
 * Date: 2016/10/10
 * Time: 11:47
 * FIXME
 */
public class Header implements SectionItem {
    @BindView(R.id.banner_guide_content)
    BGABanner mBanner;

    @Override
    public View createView(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.header_item, parent, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onBind() {
        mBanner.setAdapter(new BGABanner.Adapter() {
            @Override
            public void fillBannerItem(BGABanner banner, View view, Object model, int position) {
                ((ImageView) view).setImageResource((int) model);
            }
        });
        mBanner.setData(Arrays.asList(R.mipmap.a, R.mipmap.b, R.mipmap.c, R.mipmap.d, R.mipmap.e, R.mipmap.f,
                R.mipmap.g, R.mipmap.h), null);
    }
}