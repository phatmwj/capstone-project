package winwin.customer.app.ui.home;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.sunmi.printerx.SdkException;
import com.sunmi.printerx.api.LcdApi;
import com.sunmi.printerx.api.inner.InLcdImpl;
import com.sunmi.printerx.enums.Command;

import timber.log.Timber;
import winwin.customer.app.BR;
import winwin.customer.app.R;
import winwin.customer.app.databinding.ActivityHomeBinding;
import winwin.customer.app.di.component.ActivityComponent;
import winwin.customer.app.ui.base.activity.BaseActivity;
import winwin.customer.app.ui.fragment.home.HomeFragment;
import winwin.customer.app.ui.rxjava.ObservableTester;

public class HomeActivity extends BaseActivity<ActivityHomeBinding, HomeViewModel>
        implements NavigationBarView.OnItemSelectedListener{

    @Override
    public int getLayoutId() {
        return R.layout.activity_home;
    }

    @Override
    public int getBindingVariable() {
        return BR.vm;
    }

    @Override
    public void performDependencyInjection(ActivityComponent buildComponent) {
        buildComponent.inject(this);
    }

    public LcdApi lcdApi;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewBinding.navigationView.setOnItemSelectedListener(this);
        viewBinding.navigationView.setSelectedItemId(R.id.home);
//        try {
//            ObservableTester.Test();
//        }catch (Throwable e){
//            Timber.d(e);
//        }
        lcdApi = new LcdApi() {
            @Override
            public void config(Command command) throws SdkException {

            }

            @Override
            public void showTexts(String[] strings, int[] ints) throws SdkException {

            }

            @Override
            public void showText(String s, int i, boolean b) throws SdkException {

            }

            @Override
            public void showBitmap(Bitmap bitmap) throws SdkException {

            }

            @Override
            public void showDigital(String s) throws SdkException {

            }
        };

        // Configure LCD on app start
        try {
            lcdApi.config(Command.INIT);  // Initialize LCD
            lcdApi.config(Command.WAKE);  // Wake the LCD
            lcdApi.config(Command.CLEAR); // Clear screen
        } catch (SdkException e) {
            e.printStackTrace();
        }

        try {
            // Display "Hello, Sunmi!" on the LCD with font size 32 and non-bold text
            lcdApi.showDigital("111111");
        } catch (SdkException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.home:
                HomeFragment homeFragment = new HomeFragment();
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.frameLayout, homeFragment)
                        .setReorderingAllowed(true)
                        .commit();
                return true;
        }
        return false;
    }
}
