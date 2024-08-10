package winwin.customer.app.ui.edittext;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import winwin.customer.app.BR;
import winwin.customer.app.R;
import winwin.customer.app.data.model.api.response.CancelReason;
import winwin.customer.app.databinding.ActivityEdittextBinding;
import winwin.customer.app.di.component.ActivityComponent;
import winwin.customer.app.ui.base.activity.BaseActivity;
import winwin.customer.app.ui.edittext.adapter.CancelReasonAdapter;

public class EdittextActivity extends BaseActivity<ActivityEdittextBinding, EdittextViewModel> {

    private CancelReasonAdapter cancelReasonAdapter1;
    @Override
    public int getLayoutId() {
        return R.layout.activity_edittext;
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
        dropDownAutoCompleteTextView();
    }

    public void dropDownAutoCompleteTextView(){
        List<CancelReason> cancelReasonList1 = new ArrayList<>();
        cancelReasonList1.add(new CancelReason("1", "Tìm được chỗ", false));
        cancelReasonList1.add(new CancelReason("2", "Tìm được chỗ gần hơn", false));
        cancelReasonList1.add(new CancelReason("3", "Có việc gấp", false));
        cancelReasonList1.add(new CancelReason("1", "Tìm được chỗ", false));
        cancelReasonList1.add(new CancelReason("2", "Tìm được chỗ gần hơn", false));
        cancelReasonList1.add(new CancelReason("3", "Có việc gấp", false));
        cancelReasonList1.add(new CancelReason("1", "Tìm được chỗ", false));
        cancelReasonList1.add(new CancelReason("2", "Tìm được chỗ gần hơn", false));
        cancelReasonList1.add(new CancelReason("3", "Có việc gấp", false));
        cancelReasonList1.add(new CancelReason("1", "Tìm được chỗ", false));
        cancelReasonList1.add(new CancelReason("2", "Tìm được chỗ gần hơn", false));
        cancelReasonList1.add(new CancelReason("3", "Có việc gấp", false));
        cancelReasonList1.add(new CancelReason("1", "Tìm được chỗ", false));
        cancelReasonList1.add(new CancelReason("2", "Tìm được chỗ gần hơn", false));
        cancelReasonList1.add(new CancelReason("3", "Có việc gấp", false));
        cancelReasonList1.add(new CancelReason("1", "Tìm được chỗ", false));
        cancelReasonList1.add(new CancelReason("2", "Tìm được chỗ gần hơn", false));
        cancelReasonList1.add(new CancelReason("3", "Có việc gấp", false));



        cancelReasonAdapter1 = new CancelReasonAdapter(this,0, cancelReasonList1);

        viewBinding.autoCompleteCR1.setAdapter(cancelReasonAdapter1);

        viewBinding.autoCompleteCR1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.d("TAG", "onItemClick: ");
            }
        });
    }
}
