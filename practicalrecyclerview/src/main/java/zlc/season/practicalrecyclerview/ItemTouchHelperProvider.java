package zlc.season.practicalrecyclerview;

import android.support.v7.widget.helper.ItemTouchHelper;

/**
 * Author: Season(ssseasonnn@gmail.com)
 * Date: 2016/10/17
 * Time: 10:08
 * FIXME
 */
public class ItemTouchHelperProvider {
    private static ItemTouchHelper ourInstance;

    private ItemTouchHelperProvider() {
    }

    public static ItemTouchHelper getInstance() {
        if (ourInstance == null) {
            throw new IllegalStateException("you should call init first");
        }
        return ourInstance;
    }

    public static void init(ItemTouchHelper.Callback callback) {
        ourInstance = new ItemTouchHelper(callback);
    }
}
