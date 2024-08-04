package winwin.customer.app.ui.otp;

import android.content.Intent;
import android.os.CountDownTimer;

import androidx.databinding.ObservableField;

import java.util.Locale;

import winwin.customer.app.MVVMApplication;
import winwin.customer.app.data.Repository;
import winwin.customer.app.ui.base.activity.BaseViewModel;

public class ForgetPasswordOTPViewModel extends BaseViewModel {

    public ObservableField<String> countdownOTP = new ObservableField<>("");
    public ObservableField<String> fpOTP1 = new ObservableField<>("");
    public ObservableField<String> fpOTP2 = new ObservableField<>("");
    public ObservableField<String> fpOTP3 = new ObservableField<>("");
    public ObservableField<String> fpOTP4 = new ObservableField<>("");
    public ForgetPasswordOTPViewModel(Repository repository, MVVMApplication application) {
        super(repository, application);
        setCountdownOTP();
    }


    public void setCountdownOTP() {

        long OTPDurationInMillis = 30000; //30s
        long intervalInMillis = 1000; //1s tick

        CountDownTimer countDownTimer = new CountDownTimer(OTPDurationInMillis, intervalInMillis) {
            @Override
            public void onTick(long millisUntilFinished) {
                long minutes = (millisUntilFinished / (60 * 1000)) % 60;
                long seconds = (millisUntilFinished / 1000) % 60;
                String countdownText = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);
                countdownOTP.set(" (" + countdownText + ")");
            }
            @Override
            public void onFinish() {
                Intent intent = new Intent(application.getCurrentActivity(), LoginOTPActivity.class);
                application.getCurrentActivity().startActivity(intent);
            }
        };

        countDownTimer.start();
    }
}
