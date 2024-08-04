package winwin.customer.app.di.module;

import android.content.Context;

import androidx.core.util.Supplier;
import androidx.lifecycle.ViewModelProvider;

import java.util.ArrayList;

import winwin.customer.app.MVVMApplication;
import winwin.customer.app.ViewModelProviderFactory;
import winwin.customer.app.data.Repository;
import winwin.customer.app.di.scope.FragmentScope;
import winwin.customer.app.ui.base.fragment.BaseFragment;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import winwin.customer.app.ui.fragment.home.adapter.CategoryAdapter;
import winwin.customer.app.ui.fragment.home.HomeFragmentViewModel;

@Module
public class FragmentModule {

    private final BaseFragment<?, ?> fragment;

    public FragmentModule(BaseFragment<?, ?> fragment) {
        this.fragment = fragment;
    }

    @Named("access_token")
    @Provides
    @FragmentScope
    String provideToken(Repository repository) {
        return repository.getToken();
    }

    @Provides
    @FragmentScope
    HomeFragmentViewModel provideProfileFragmentViewModel(Repository repository, Context application){
        Supplier<HomeFragmentViewModel> supplier = () -> new HomeFragmentViewModel(repository, (MVVMApplication) application);
        ViewModelProviderFactory<HomeFragmentViewModel> factory = new ViewModelProviderFactory<>(HomeFragmentViewModel.class, supplier);
        return new ViewModelProvider(fragment, factory).get(HomeFragmentViewModel.class);
    }

}
