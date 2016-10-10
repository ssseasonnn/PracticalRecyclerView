package zlc.season.demo.multipleitem;

import zlc.season.demo.RecyclerItemType;
import zlc.season.practicalrecyclerview.ItemType;

/**
 * Author: Season(ssseasonnn@gmail.com)
 * Date: 2016/9/21
 * Time: 15:11
 * FIXME
 */
class TypeOneBean implements ItemType {

    public String text;

    public TypeOneBean(String text) {
        this.text = text;
    }

    @Override
    public int itemType() {
        return RecyclerItemType.TYPE1.getValue();
    }
}
