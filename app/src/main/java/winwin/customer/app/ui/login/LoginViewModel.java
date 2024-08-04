package winwin.customer.app.ui.login;

import androidx.databinding.ObservableField;

import winwin.customer.app.MVVMApplication;
import winwin.customer.app.R;
import winwin.customer.app.data.Repository;
import winwin.customer.app.data.model.api.request.LoginRequest;
import winwin.customer.app.ui.base.activity.BaseViewModel;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class LoginViewModel extends BaseViewModel {
    public ObservableField<String> username = new ObservableField<>("customerdemo");
    public ObservableField<String> password = new ObservableField<>("123456");

    public LoginViewModel(Repository repository, MVVMApplication application) {
        super(repository, application);
    }

    public void login() {
        // check
        if (username.get() == null || username.get().isEmpty() || password.get() == null || password.get().isEmpty()) {
            showErrorMessage(application.getString(R.string.empty_user_pass));
            return;
        }
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setPassword(password.get());
        loginRequest.setUsername(username.get());
        showLoading();
        compositeDisposable.add(repository.getApiService().login(loginRequest)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(data -> {
                    repository.getSharedPreferences().setToken(data.getAccessToken());
                    hideLoading();
                }, err -> {
                    hideLoading();
                    showErrorMessage(application.getString(R.string.wrong_user_pass));
                }));
    }
}
