package winwin.customer.app.ui.splashform;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.annotation.Nullable;

import eu.davidea.flexibleadapter.databinding.BR;
import winwin.customer.app.R;
import winwin.customer.app.databinding.ActivitySplashFormBinding;
import winwin.customer.app.di.component.ActivityComponent;
import winwin.customer.app.ui.base.activity.BaseActivity;
import winwin.customer.app.ui.input.phone.PhoneActivity;
import winwin.customer.app.ui.welcome.WelcomeActivity;

public class SplashFormActivity extends BaseActivity<ActivitySplashFormBinding, SplashFormViewModel> {

    @Override
    public int getLayoutId() {
        return R.layout.activity_splash_form;
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
        // Delay for 5 seconds (5000 milliseconds)
        long delayMillis = 3000;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (checkLocationPermissions()) {
                    startActivity(new Intent(SplashFormActivity.this, PhoneActivity.class));
                    finish();
                } else {
                    startActivity(new Intent(SplashFormActivity.this, WelcomeActivity.class));
                    finish();
                }
            }
        }, delayMillis);
    }
}