package zlc.season.demo.drag;

import android.support.v7.widget.helper.ItemTouchHelper;

/**
 * Author: Season(ssseasonnn@gmail.com)
 * Date: 2016/10/17
 * Time: 10:08
 * FIXME
 */
class ItemTouchHelperProvider {
    private static ItemTouchHelper ourInstance;

    private ItemTouchHelperProvider() {
    }

    static ItemTouchHelper getInstance() {
        if (ourInstance == null) {
            throw new IllegalStateException("you should call init first");
        }
        return ourInstance;
    }

    static void init(ItemTouchHelper.Callback callback) {
        ourInstance = new ItemTouchHelper(callback);
    }
}
