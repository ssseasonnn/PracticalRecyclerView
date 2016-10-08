package zlc.season.demo.singleitem;

import java.util.List;

/**
 * Author: Season(ssseasonnn@gmail.com)
 * Date: 2016/9/23
 * Time: 15:17
 * FIXME
 */
interface SingleItemView {
    void onDataLoadSuccess(List<NormalBean> list, boolean isRefresh);

    void onDataLoadFailed(boolean isRefresh);
}
