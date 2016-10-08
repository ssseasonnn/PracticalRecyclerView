package zlc.season.demo.main;

import java.util.List;

/**
 * Author: Season(ssseasonnn@gmail.com)
 * Date: 2016/10/8
 * Time: 15:12
 * FIXME
 */
interface MainView {
    void onLoadSuccess(List<MenuBean> menu);

    void onLoadFailed();
}
