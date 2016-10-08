package zlc.season.demo.multipleitem;

import zlc.season.demo.RecyclerItemType;
import zlc.season.practicalrecyclerview.ItemType;

/**
 * Author: Season(ssseasonnn@gmail.com)
 * Date: 2016/9/21
 * Time: 15:11
 * FIXME
 */
class TestBean implements ItemType {

    private String mText;
    private String mButton;

    public TestBean(String button, String text) {
        mButton = button;
        mText = text;
    }

    public String getText() {
        return mText;
    }

    public void setText(String text) {
        mText = text;
    }

    public String getButton() {
        return mButton;
    }

    public void setButton(String button) {
        mButton = button;
    }

    @Override
    public int itemType() {
        return RecyclerItemType.TEST1.getValue();
    }
}
