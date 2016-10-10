package zlc.season.demo.grid;

import zlc.season.practicalrecyclerview.ItemType;

/**
 * Author: Season(ssseasonnn@gmail.com)
 * Date: 2016/10/10
 * Time: 10:15
 * FIXME
 */
public class GridBean implements ItemType {
    String text;

    public GridBean(String text) {
        this.text = text;
    }

    @Override
    public int itemType() {
        return 0;
    }
}
