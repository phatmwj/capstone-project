package winwin.customer.app.ui.otp;

import android.content.Intent;

import androidx.databinding.ObservableField;


import winwin.customer.app.MVVMApplication;
import winwin.customer.app.data.Repository;
import winwin.customer.app.ui.base.activity.BaseViewModel;
import winwin.customer.app.ui.profile.EditProfileActivity;

public class LoginOTPViewModel extends BaseViewModel {


    public ObservableField<String> otp1 = new ObservableField<>();
    public ObservableField<String> otp2 = new ObservableField<>();
    public ObservableField<String> otp3 = new ObservableField<>();
    public ObservableField<String> otp4 = new ObservableField<>();
    public ObservableField<String> otp5 = new ObservableField<>();
    public ObservableField<String> otp6 = new ObservableField<>();
    public ObservableField<Boolean> isShown = new ObservableField<>(false);


    public LoginOTPViewModel(Repository repository, MVVMApplication application) {
        super(repository, application);
    }


    public void verifyOTP(){
        Intent intent = new Intent(application.getCurrentActivity(), EditProfileActivity.class);
        application.getCurrentActivity().startActivity(intent);
    }

    public void showPw(){
        boolean a = isShown.get();
        isShown.set(!a);
    }

}
