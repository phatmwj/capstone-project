package winwin.customer.app.ui.input.phone;

import android.os.Bundle;

import eu.davidea.flexibleadapter.databinding.BR;
import winwin.customer.app.R;
import winwin.customer.app.databinding.ActivityPhoneBinding;
import winwin.customer.app.di.component.ActivityComponent;
import winwin.customer.app.ui.base.activity.BaseActivity;

public class PhoneActivity extends BaseActivity<ActivityPhoneBinding, PhoneViewModel> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_phone;
    }

    @Override
    public int getBindingVariable() {
        return BR.vm;
    }

    @Override
    public void performDependencyInjection(ActivityComponent buildComponent) {
        buildComponent.inject(this);
    }

}