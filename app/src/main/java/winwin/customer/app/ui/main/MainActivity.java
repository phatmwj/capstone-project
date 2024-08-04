package winwin.customer.app.ui.main;

import android.os.Bundle;

import androidx.annotation.Nullable;

import winwin.customer.app.BR;
import winwin.customer.app.R;
import winwin.customer.app.databinding.ActivityMainBinding;
import winwin.customer.app.di.component.ActivityComponent;
import winwin.customer.app.ui.base.activity.BaseActivity;


public class MainActivity extends BaseActivity<ActivityMainBinding, MainViewModel> {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        // lay ssid tu share ref
        super.onCreate(savedInstanceState);
        viewBinding.setA(this);
        viewBinding.setVm(viewModel);


    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
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
