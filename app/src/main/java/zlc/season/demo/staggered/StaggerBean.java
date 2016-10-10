package zlc.season.demo.staggered;

import zlc.season.practicalrecyclerview.ItemType;

/**
 * Author: Season(ssseasonnn@gmail.com)
 * Date: 2016/10/10
 * Time: 13:38
 * FIXME
 */
public class StaggerBean implements ItemType {
    String text;

    public StaggerBean(String text) {
        this.text = text;
    }

    @Override
    public int itemType() {
        return 0;
    }
}
