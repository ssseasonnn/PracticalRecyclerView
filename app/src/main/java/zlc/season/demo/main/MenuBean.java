package zlc.season.demo.main;

import zlc.season.practicalrecyclerview.ItemType;

/**
 * Author: Season(ssseasonnn@gmail.com)
 * Date: 2016/10/8
 * Time: 14:21
 * FIXME
 */
class MenuBean implements ItemType {
    public String menu;
    public int type;

    @Override
    public int itemType() {
        return 0;
    }
}
