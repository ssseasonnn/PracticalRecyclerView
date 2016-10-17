package zlc.season.demo.lineardrag;

import zlc.season.practicalrecyclerview.DeepCopy;
import zlc.season.practicalrecyclerview.ItemType;

/**
 * Author: Season(ssseasonnn@gmail.com)
 * Date: 2016/10/12
 * Time: 11:42
 * FIXME
 */
public class LinearDragBean implements ItemType, DeepCopy<LinearDragBean> {
    String text;
    boolean status = false;

    public LinearDragBean(String text) {
        this.text = text;
    }

    @Override
    public int itemType() {
        return 0;
    }

    @Override
    public LinearDragBean getCopy() {
        LinearDragBean copy = new LinearDragBean(text);
        copy.status = status;
        return copy;
    }
}
