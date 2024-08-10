package winwin.customer.app.ui.edittext;

import androidx.databinding.ObservableField;

import winwin.customer.app.MVVMApplication;
import winwin.customer.app.data.Repository;
import winwin.customer.app.ui.base.activity.BaseViewModel;

public class EdittextViewModel extends BaseViewModel {

    public ObservableField<String> password = new ObservableField<>();
    public EdittextViewModel(Repository repository, MVVMApplication application) {
        super(repository, application);
    }
}
