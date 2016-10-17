package zlc.season.demo.lineardrag;

import java.util.List;

/**
 * Author: Season(ssseasonnn@gmail.com)
 * Date: 2016/10/12
 * Time: 11:45
 * FIXME
 */
public interface LinearDragView {
    void onDataLoadSuccess(List<LinearDragBean> list);

    void onDataLoadFailed();
}
