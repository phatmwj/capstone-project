package winwin.customer.app.ui.flexibleadapter;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;

import java.util.ArrayList;
import java.util.List;

import eu.davidea.flexibleadapter.common.SmoothScrollGridLayoutManager;
import eu.davidea.flexibleadapter.items.AbstractFlexibleItem;
import winwin.customer.app.BR;
import winwin.customer.app.R;
import winwin.customer.app.databinding.ActivityTableMappingBinding;
import winwin.customer.app.di.component.ActivityComponent;
import winwin.customer.app.ui.base.activity.BaseActivity;

public class TableMappingActivity extends BaseActivity<ActivityTableMappingBinding, TableMappingViewModel> {

    private List<AbstractFlexibleItem> dataTables = new ArrayList<>();
    private PosTableMappingAdapter adapter;
    private GridLayoutManager gridLayoutManager;
    private PosTableItemClick posTableItemClick = pos -> {
//        ShareDataModel.getInstance().getMappingBundle().putString("TABLE_NUMBER",((PosTableItem)dataTables.get(pos)).getItem().getTable());
//        backScreen();
    };
    @Override
    public int getLayoutId() {
        return R.layout.activity_table_mapping;
    }

    @Override
    public int getBindingVariable() {
        return BR.vm;
    }

    @Override
    public void performDependencyInjection(ActivityComponent buildComponent) {
        buildComponent.inject(this);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        PosTableItem posTableItem;

        for (int i = 0; i < 50; i++) {
            posTableItem = new PosTableItem(new PosTableObject(String.valueOf(i)), posTableItemClick);
            dataTables.add(posTableItem);
        }

        adapter = new PosTableMappingAdapter(dataTables);
        adapter.setDisplayHeadersAtStartUp(true)
                .setStickyHeaders(false);

        gridLayoutManager = new SmoothScrollGridLayoutManager(this, 3);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return adapter.getItem(position).getSpanSize(3, position);
            }
        });

        viewBinding.posTableGridview.setAdapter(adapter);
        viewBinding.posTableGridview.setLayoutManager(gridLayoutManager);
        viewBinding.posTableGridview.setHasFixedSize(true);
        viewBinding.posTableGridview.setItemAnimator(new DefaultItemAnimator());
        viewBinding.posTableGridview.addItemDecoration(new VerticalSpaceItemDecoration(getResources().getDimensionPixelSize(R.dimen._5sdp)));
    }
}
