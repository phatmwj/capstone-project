package winwin.customer.app.ui.flexibleadapter;

import androidx.annotation.Nullable;

import java.util.List;

import eu.davidea.flexibleadapter.FlexibleAdapter;
import eu.davidea.flexibleadapter.items.AbstractFlexibleItem;

public class PosTableMappingAdapter<VH extends AbstractFlexibleItem> extends FlexibleAdapter<VH> {

    public PosTableMappingAdapter(@Nullable List<VH> items) {
        super(items);
    }
}
