package zlc.season.demo.singleitem;

import zlc.season.practicalrecyclerview.ItemType;

/**
 * Author: Season(ssseasonnn@gmail.com)
 * Date: 2016/9/21
 * Time: 15:33
 * FIXME
 */
class NormalBean implements ItemType {
    String mImg;
    String mTitle;
    String mContent;

    NormalBean(String img, String title, String content) {
        mImg = img;
        mContent = content;
        mTitle = title;
    }

    @Override
    public int itemType() {
        return 0;
    }
}
