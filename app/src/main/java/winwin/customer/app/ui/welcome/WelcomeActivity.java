package winwin.customer.app.ui.welcome;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;

import winwin.customer.app.BR;
import winwin.customer.app.R;
import winwin.customer.app.constant.Constants;
import winwin.customer.app.databinding.ActivityWelcomeBinding;
import winwin.customer.app.di.component.ActivityComponent;
import winwin.customer.app.ui.base.activity.BaseActivity;
import winwin.customer.app.ui.input.phone.PhoneActivity;

public class WelcomeActivity extends BaseActivity<ActivityWelcomeBinding, WelcomeViewModel> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(!checkLocationPermissions()) {
            requestLocationPermissions();
        }
    }
    @Override
    public int getLayoutId() {
        return R.layout.activity_welcome;
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
    public void onRequestPermissionsResult(
            int requestCode,
            @NonNull String[] permissions,
            @NonNull int[] grantResults) {

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == Constants.LOCATION_PERMISSION_CODE) {
            // Kiểm tra kết quả yêu cầu quyền truy cập vị trí
            boolean allPermissionsGranted = true;
            for (int grantResult : grantResults) {
                if (grantResult != PackageManager.PERMISSION_GRANTED) {
                    allPermissionsGranted = false;
                    break;
                }
            }
            if (allPermissionsGranted) {
                viewModel.checkLocationPermission.set(true);
            } else {
                Log.d("AAA", "onRequestPermissionsResult: ");
                finishAffinity();
                System.exit(0);
            }
        }
    }

}