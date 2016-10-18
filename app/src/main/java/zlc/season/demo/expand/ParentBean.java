package zlc.season.demo.expand;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import zlc.season.demo.RecyclerItemType;
import zlc.season.practicalrecyclerview.ItemType;

/**
 * Author: Season(ssseasonnn@gmail.com)
 * Date: 2016/10/17
 * Time: 15:28
 * FIXME
 */
class ParentBean implements ItemType {

    @SerializedName("text")
    int text;

    @SerializedName("child")
    List<ChildBean> mChild;

    /**
     * 是否展开
     */
    boolean isExpand;

    @Override
    public int itemType() {
        return RecyclerItemType.PARENT.getValue();
    }
}
