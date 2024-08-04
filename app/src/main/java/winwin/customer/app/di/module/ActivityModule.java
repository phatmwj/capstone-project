package winwin.customer.app.di.module;

import android.content.Context;

import androidx.core.util.Supplier;
import androidx.lifecycle.ViewModelProvider;

import winwin.customer.app.MVVMApplication;
import winwin.customer.app.ViewModelProviderFactory;
import winwin.customer.app.data.Repository;
import winwin.customer.app.di.scope.ActivityScope;
import winwin.customer.app.ui.base.activity.BaseActivity;
import winwin.customer.app.ui.home.HomeViewModel;
import winwin.customer.app.ui.input.phone.PhoneViewModel;
import winwin.customer.app.ui.login.LoginViewModel;
import winwin.customer.app.ui.main.MainViewModel;
import winwin.customer.app.ui.otp.ForgetPasswordOTPViewModel;
import winwin.customer.app.ui.otp.LoginOTPViewModel;
import winwin.customer.app.ui.profile.EditProfileViewModel;
import winwin.customer.app.ui.profile.ProfileViewModel;
import winwin.customer.app.ui.welcome.WelcomeViewModel;
import winwin.customer.app.ui.splashform.SplashFormViewModel;
import winwin.customer.app.utils.GetInfo;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

@Module
public class ActivityModule {

    private final BaseActivity<?, ?> activity;

    public ActivityModule(BaseActivity<?, ?> activity) {
        this.activity = activity;
    }

    @Named("access_token")
    @Provides
    @ActivityScope
    String provideToken(Repository repository) {
        return repository.getToken();
    }

    @Named("device_id")
    @Provides
    @ActivityScope
    String provideDeviceId(Context applicationContext) {
        return GetInfo.getAll(applicationContext);
    }


    @Provides
    @ActivityScope
    MainViewModel provideMainViewModel(Repository repository, Context application) {
        Supplier<MainViewModel> supplier = () -> new MainViewModel(repository, (MVVMApplication) application);
        ViewModelProviderFactory<MainViewModel> factory = new ViewModelProviderFactory<>(MainViewModel.class, supplier);
        return new ViewModelProvider(activity, factory).get(MainViewModel.class);
    }

    @Provides
    @ActivityScope
    LoginViewModel provideLoginViewModel(Repository repository, Context application) {
        Supplier<LoginViewModel> supplier = () -> new LoginViewModel(repository, (MVVMApplication) application);
        ViewModelProviderFactory<LoginViewModel> factory = new ViewModelProviderFactory<>(LoginViewModel.class, supplier);
        return new ViewModelProvider(activity, factory).get(LoginViewModel.class);
    }

    @Provides
    @ActivityScope
    ProfileViewModel provideProfileViewModel(Repository repository, Context application) {
        Supplier<ProfileViewModel> supplier = () -> new ProfileViewModel(repository, (MVVMApplication) application);
        ViewModelProviderFactory<ProfileViewModel> factory = new ViewModelProviderFactory<>(ProfileViewModel.class, supplier);
        return new ViewModelProvider(activity, factory).get(ProfileViewModel.class);
    }

    @Provides
    @ActivityScope
    LoginOTPViewModel provideLoginOTPViewModel(Repository repository, Context application) {
        Supplier<LoginOTPViewModel> supplier = () -> new LoginOTPViewModel(repository, (MVVMApplication) application);
        ViewModelProviderFactory<LoginOTPViewModel> factory = new ViewModelProviderFactory<>(LoginOTPViewModel.class, supplier);
        return new ViewModelProvider(activity, factory).get(LoginOTPViewModel.class);
    }

    @Provides
    @ActivityScope
    EditProfileViewModel provideEditProfileViewModel(Repository repository, Context application) {
        Supplier<EditProfileViewModel> supplier = () -> new EditProfileViewModel(repository, (MVVMApplication) application);
        ViewModelProviderFactory<EditProfileViewModel> factory = new ViewModelProviderFactory<>(EditProfileViewModel.class, supplier);
        return new ViewModelProvider(activity, factory).get(EditProfileViewModel.class);
    }

    @Provides
    @ActivityScope
    ForgetPasswordOTPViewModel provideForgetPasswordOTPViewModel(Repository repository, Context application) {
        Supplier<ForgetPasswordOTPViewModel> supplier = () -> new ForgetPasswordOTPViewModel(repository, (MVVMApplication) application);
        ViewModelProviderFactory<ForgetPasswordOTPViewModel> factory = new ViewModelProviderFactory<>(ForgetPasswordOTPViewModel.class, supplier);
        return new ViewModelProvider(activity, factory).get(ForgetPasswordOTPViewModel.class);
    }

    @Provides
    @ActivityScope
    SplashFormViewModel provideSplashFormViewModel(Repository repository, Context application) {
        Supplier<SplashFormViewModel> supplier = () -> new SplashFormViewModel(repository, (MVVMApplication) application);
        ViewModelProviderFactory<SplashFormViewModel> factory = new ViewModelProviderFactory<>(SplashFormViewModel.class, supplier);
        return new ViewModelProvider(activity, factory).get(SplashFormViewModel.class);
    }

    @Provides
    @ActivityScope
    WelcomeViewModel provideWelcomeViewModel(Repository repository, Context application) {
        Supplier<WelcomeViewModel> supplier = () -> new WelcomeViewModel(repository, (MVVMApplication) application);
        ViewModelProviderFactory<WelcomeViewModel> factory = new ViewModelProviderFactory<>(WelcomeViewModel.class, supplier);
        return new ViewModelProvider(activity, factory).get(WelcomeViewModel.class);
    }

    @Provides
    @ActivityScope
    PhoneViewModel providePhoneViewModel(Repository repository, Context application) {
        Supplier<PhoneViewModel> supplier = () -> new PhoneViewModel(repository, (MVVMApplication) application);
        ViewModelProviderFactory<PhoneViewModel> factory = new ViewModelProviderFactory<>(PhoneViewModel.class, supplier);
        return new ViewModelProvider(activity, factory).get(PhoneViewModel.class);
    }

    @Provides
    @ActivityScope
    HomeViewModel provideHomeViewModel(Repository repository, Context application) {
        Supplier<HomeViewModel> supplier = () -> new HomeViewModel(repository, (MVVMApplication) application);
        ViewModelProviderFactory<HomeViewModel> factory = new ViewModelProviderFactory<>(HomeViewModel.class, supplier);
        return new ViewModelProvider(activity, factory).get(HomeViewModel.class);
    }
}
