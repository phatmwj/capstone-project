package winwin.customer.app.di.component;


import winwin.customer.app.di.module.FragmentModule;
import winwin.customer.app.di.scope.FragmentScope;

import dagger.Component;
import winwin.customer.app.ui.fragment.home.HomeFragment;

@FragmentScope
@Component(modules = {FragmentModule.class}, dependencies = AppComponent.class)
public interface FragmentComponent {
    void inject(HomeFragment homeFragment);
}
