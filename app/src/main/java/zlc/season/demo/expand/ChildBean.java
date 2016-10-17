package zlc.season.demo.expand;

import com.google.gson.annotations.SerializedName;

import zlc.season.demo.RecyclerItemType;
import zlc.season.practicalrecyclerview.ItemType;

/**
 * Author: Season(ssseasonnn@gmail.com)
 * Date: 2016/10/17
 * Time: 15:29
 * FIXME
 */
public class ChildBean implements ItemType {
    @SerializedName("text")
    int text;

    @Override
    public int itemType() {
        return RecyclerItemType.CHILD.getValue();
    }
}
