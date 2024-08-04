package winwin.customer.app.ui.splashform;

import winwin.customer.app.MVVMApplication;
import winwin.customer.app.data.Repository;
import winwin.customer.app.ui.welcome.WelcomeViewModel;

public class SplashFormViewModel extends WelcomeViewModel {
    public SplashFormViewModel(Repository repository, MVVMApplication application) {
        super(repository, application);
    }
}
