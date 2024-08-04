package winwin.customer.app.ui.welcome;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;

import androidx.core.content.ContextCompat;
import androidx.databinding.ObservableBoolean;

import winwin.customer.app.MVVMApplication;
import winwin.customer.app.data.Repository;
import winwin.customer.app.ui.base.activity.BaseViewModel;
import winwin.customer.app.ui.input.phone.PhoneActivity;

public class WelcomeViewModel extends BaseViewModel {

    public ObservableBoolean checkLocationPermission = new ObservableBoolean(false);
    public WelcomeViewModel(Repository repository, MVVMApplication application) {
        super(repository, application);
    }
    public void openInputPhone(){
        Intent intent = new Intent(getApplication().getCurrentActivity(), PhoneActivity.class);
        getApplication().getCurrentActivity().startActivity(intent);
    }


}
