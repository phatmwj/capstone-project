package winwin.customer.app.ui.input.phone;

import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;

import androidx.databinding.ObservableField;

import winwin.customer.app.MVVMApplication;
import winwin.customer.app.data.Repository;
import winwin.customer.app.ui.base.activity.BaseViewModel;
import winwin.customer.app.ui.home.HomeActivity;
import winwin.customer.app.ui.welcome.WelcomeActivity;

public class PhoneViewModel extends BaseViewModel {

    public ObservableField<String> phoneNumber = new ObservableField<>();
    public PhoneViewModel(Repository repository, MVVMApplication application) {
        super(repository, application);
    }

    public void doBack(){
        Intent intent = new Intent(getApplication().getCurrentActivity(), WelcomeActivity.class);
        getApplication().getCurrentActivity().startActivity(intent);
    }

    public void onTextChanged(CharSequence s, int start, int before, int count) {
        Log.w("tag", "onTextChanged " + s);
        phoneNumber.set(s.toString());
    }

    public void clickButton(){
        Log.w("tag", "onTextdgfsdgds");
        Intent intent = new Intent(getApplication().getCurrentActivity(), HomeActivity.class);
        getApplication().getCurrentActivity().startActivity(intent);
    }
}
