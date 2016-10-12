package zlc.season.demo.lineardrag;

import android.os.Bundle;
import android.view.ViewGroup;

import java.util.List;

import zlc.season.practicalrecyclerview.AbstractAdapter;

/**
 * Author: Season(ssseasonnn@gmail.com)
 * Date: 2016/10/12
 * Time: 11:45
 * FIXME
 */
public class LinearDragAdapter extends AbstractAdapter<LinearDragBean, LinearDragViewHolder> {
    @Override
    protected LinearDragViewHolder onNewCreateViewHolder(ViewGroup parent, int viewType) {
        return new LinearDragViewHolder(parent);
    }

    @Override
    protected void onNewBindViewHolder(LinearDragViewHolder holder, int position) {
        holder.setData(get(position));
    }

    @Override
    protected void onNewBindViewHolder(LinearDragViewHolder holder, int position, List<Object> payloads) {
        Bundle o = (Bundle) payloads.get(0);
        for (String key : o.keySet()) {
            if ("status".equals(key)) {
                holder.updateStatus(o.getBoolean(key));
            }
        }
    }
}
