package zlc.season.demo.expand;

import java.util.List;

/**
 * Author: Season(ssseasonnn@gmail.com)
 * Date: 2016/10/10
 * Time: 11:42
 * FIXME
 */
public interface ExpandView {
    void onDataLoadSuccess(List<ParentBean> list, boolean isRefresh);

    void onDataLoadFailed(boolean isRefresh);
}
