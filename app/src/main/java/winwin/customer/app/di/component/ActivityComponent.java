package winwin.customer.app.di.component;

import winwin.customer.app.di.module.ActivityModule;
import winwin.customer.app.di.scope.ActivityScope;
import winwin.customer.app.ui.home.HomeActivity;
import winwin.customer.app.ui.login.LoginActivity;
import winwin.customer.app.ui.main.MainActivity;

import dagger.Component;
import winwin.customer.app.ui.otp.ForgetPasswordOTPActivity;
import winwin.customer.app.ui.otp.LoginOTPActivity;
import winwin.customer.app.ui.profile.EditProfileActivity;
import winwin.customer.app.ui.welcome.WelcomeActivity;
import winwin.customer.app.ui.input.phone.PhoneActivity;
import winwin.customer.app.ui.splashform.SplashFormActivity;

@ActivityScope
@Component(modules = {ActivityModule.class}, dependencies = AppComponent.class)
public interface ActivityComponent {
    void inject(MainActivity activity);
    void inject(LoginActivity loginActivity);
    void inject(SplashFormActivity splashFormActivity);
    void inject(WelcomeActivity welcomeActivity);
    void inject(PhoneActivity phoneActivity);

    void inject(LoginOTPActivity loginOTPActivity);

    void inject(EditProfileActivity editProfileActivity);

    void inject(ForgetPasswordOTPActivity forgetPasswordOTPActivity);

    void inject(HomeActivity homeActivity);
}

