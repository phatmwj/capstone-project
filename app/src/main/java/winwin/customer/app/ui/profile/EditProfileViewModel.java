package winwin.customer.app.ui.profile;

import androidx.databinding.ObservableField;

import winwin.customer.app.MVVMApplication;
import winwin.customer.app.data.Repository;
import winwin.customer.app.ui.base.activity.BaseViewModel;

public class EditProfileViewModel extends BaseViewModel {

    public ObservableField<String> avatar = new ObservableField<>();
    public ObservableField<String> fullName = new ObservableField<>();
    public ObservableField<String> password = new ObservableField<>();
    public ObservableField<String> confirmPassword = new ObservableField<>();


    public EditProfileViewModel(Repository repository, MVVMApplication application) {
        super(repository, application);
    }

    public void updateProfile(){

    }

}
