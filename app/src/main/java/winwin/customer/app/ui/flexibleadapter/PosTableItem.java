package winwin.customer.app.ui.flexibleadapter;

import android.view.View;
import android.widget.TextView;

import java.util.List;
import java.util.Objects;

import eu.davidea.flexibleadapter.FlexibleAdapter;
import eu.davidea.flexibleadapter.items.AbstractFlexibleItem;
import eu.davidea.flexibleadapter.items.IFlexible;
import eu.davidea.viewholders.FlexibleViewHolder;
import lombok.Getter;
import winwin.customer.app.R;

public class PosTableItem extends AbstractFlexibleItem<PosTableItem.ViewHolder> {

    @Getter
    private PosTableObject item;
    private PosTableItemClick callback;

    public PosTableItem(PosTableObject item, PosTableItemClick callback) {
        this.item = item;
        this.callback = callback;
    }

    @Override
    public boolean equals(Object inObject) {
        if (inObject instanceof PosTableItem) {
            PosTableItem inItem = (PosTableItem) inObject;
            return Objects.equals(getItem().getTable(),inItem.getItem().getTable());
        }
        return false;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.pos_table_item;
    }

    @Override
    public ViewHolder createViewHolder(View view, FlexibleAdapter<IFlexible> adapter) {
        return new ViewHolder(view, adapter);
    }

    @Override
    public void bindViewHolder(FlexibleAdapter<IFlexible> adapter, ViewHolder holder, int position, List<Object> payloads) {
        holder.tableName.setText(item.getTable());
        holder.rootView.setOnClickListener(v1 -> {
            if(callback!=null){
                callback.onClick(position);
            }
        });
    }

    static class ViewHolder extends FlexibleViewHolder {
        View rootView;
        TextView tableName;
        ViewHolder(View v, FlexibleAdapter adapter) {
            super(v, adapter, false);//True for sticky
            rootView = v;
            tableName = v.findViewById(R.id.pos_table_item_postion);

        }
    }
}
