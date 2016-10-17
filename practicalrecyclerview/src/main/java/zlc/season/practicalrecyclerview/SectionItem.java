package zlc.season.practicalrecyclerview;

import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Author: Season(ssseasonnn@gmail.com)
 * Date: 2016/9/22
 * Time: 15:33
 * FIXME
 */
public interface SectionItem {

    View createView(ViewGroup parent);

    void onBind();

    void onBind(List<Object> payloads);
}
