package zlc.season.demo.lineardrag;

import zlc.season.practicalrecyclerview.ItemType;

/**
 * Author: Season(ssseasonnn@gmail.com)
 * Date: 2016/10/12
 * Time: 11:42
 * FIXME
 */
public class LinearDragBean implements ItemType, Cloneable {
    final boolean status = false;
    String text;

    public LinearDragBean(String text) {
        this.text = text;
    }

    @Override
    public int itemType() {
        return 0;
    }

    @Override
    protected LinearDragBean clone() throws CloneNotSupportedException {
        LinearDragBean bean = null;
        try {
            bean = (LinearDragBean) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return bean;
    }
}
