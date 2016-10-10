package zlc.season.demo.staggered;

import java.util.List;

/**
 * Author: Season(ssseasonnn@gmail.com)
 * Date: 2016/10/10
 * Time: 11:42
 * FIXME
 */
public interface StaggerView {
    void onDataLoadSuccess(List<StaggerBean> list, boolean isRefresh);

    void onDataLoadFailed(boolean isRefresh);
}
