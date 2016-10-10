package zlc.season.demo.grid;

import java.util.List;

/**
 * Author: Season(ssseasonnn@gmail.com)
 * Date: 2016/10/10
 * Time: 11:42
 * FIXME
 */
public interface GridView {
    void onDataLoadSuccess(List<GridBean> list, boolean isRefresh);

    void onDataLoadFailed(boolean isRefresh);
}
