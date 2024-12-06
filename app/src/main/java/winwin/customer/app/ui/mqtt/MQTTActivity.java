package winwin.customer.app.ui.mqtt;


import winwin.customer.app.BR;
import winwin.customer.app.R;
import winwin.customer.app.databinding.ActivityMqttBinding;
import winwin.customer.app.di.component.ActivityComponent;
import winwin.customer.app.ui.base.activity.BaseActivity;

public class MQTTActivity extends BaseActivity<ActivityMqttBinding, MQTTViewModel> {
    @Override
    public int getLayoutId() {
        return R.layout.activity_mqtt;
    }

    @Override
    public int getBindingVariable() {
        return BR.vm;
    }

    @Override
    public void performDependencyInjection(ActivityComponent buildComponent) {
        buildComponent.inject(this);
    }
}
