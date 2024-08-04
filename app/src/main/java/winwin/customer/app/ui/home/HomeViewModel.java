package winwin.customer.app.ui.home;

import winwin.customer.app.MVVMApplication;
import winwin.customer.app.data.Repository;
import winwin.customer.app.ui.base.activity.BaseViewModel;

public class HomeViewModel extends BaseViewModel {

    public HomeViewModel(Repository repository, MVVMApplication application) {
        super(repository, application);
    }
}
