package zlc.season.demo.data;

import zlc.season.demo.RecyclerItemType;
import zlc.season.practicalrecyclerview.ItemType;

/**
 * Author: Season(ssseasonnn@gmail.com)
 * Date: 2016/9/21
 * Time: 15:33
 * FIXME
 */
public class NormalBean implements ItemType {
    private String mImg;
    private String mTitle;
    private String mContent;

    public NormalBean(String img, String title, String content) {
        mImg = img;
        mContent = content;
        mTitle = title;
    }

    public String getImg() {
        return mImg;
    }

    public void setImg(String img) {
        mImg = img;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getContent() {
        return mContent;
    }

    public void setContent(String content) {
        mContent = content;
    }

    @Override
    public int itemType() {
        return RecyclerItemType.NORMAL.getValue();
    }
}
