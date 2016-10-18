package zlc.season.demo.drag;

import java.util.List;

/**
 * Author: Season(ssseasonnn@gmail.com)
 * Date: 2016/10/12
 * Time: 11:45
 * FIXME
 */
interface DragView {
    void onDataLoadSuccess(List<DragBean> list);

    void onDataLoadFailed();
}
