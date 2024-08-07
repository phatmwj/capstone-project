package winwin.customer.app.data.remote;

import android.app.Application;
import android.content.Intent;

import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import winwin.customer.app.constant.Constants;
import winwin.customer.app.data.local.prefs.PreferencesService;
import winwin.customer.app.utils.LogService;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class AuthInterceptor implements Interceptor {

    private final PreferencesService appPreferences;
    private final Application application;

    public AuthInterceptor(PreferencesService appPreferences, Application application) {
        this.appPreferences = appPreferences;
        this.application = application;
    }

    @NotNull
    @Override
    public Response intercept(@NotNull Interceptor.Chain chain) throws IOException {
        String isIgnore = chain.request().header("IgnoreAuth");
        if (isIgnore != null && isIgnore.equals("1")) {
            Request.Builder newRequest = chain.request().newBuilder();
            newRequest.removeHeader("IgnoreAuth");
            return chain.proceed(newRequest.build());
        }

        //Add Authentication
        Request.Builder newRequest = chain.request().newBuilder();
        appPreferences.setToken("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX2tpbmQiOjEsInVzZXJfaWQiOjc2NDU1NDA0NDM2MTkzMjgsImdyYW50X3R5cGUiOiJwYXNzd29yZCIsImFkZGl0aW9uYWxfaW5mbyI6ImVKd3pOek14TlRVeE1ERXhOak8wTkRheXFORTFyREdzc2JHRDBBVVppU1VscWNVbElCNFFwU1htRktjQ0FGa1JEM0E9IiwidXNlcl9uYW1lIjoicGhhdHRlc3QiLCJzY29wZSI6WyJyZWFkIiwid3JpdGUiXSwiZXhwIjoxNzI1NTUyMTE3LCJhdXRob3JpdGllcyI6WyJST0xFX0tFX0lfQyIsIlJPTEVfS0VfSV9EIiwiUk9MRV9DQV9WIiwiUk9MRV9BQ0NfRCIsIlJPTEVfQ0FfVSIsIlJPTEVfVFJfQyIsIlJPTEVfS0VfSV9MIiwiUk9MRV9DQV9MIiwiUk9MRV9QQV9QX0MiLCJST0xFX1VTX0dfTl9VIiwiUk9MRV9VU19HX05fViIsIlJPTEVfVFJfR19EIiwiUk9MRV9BQ0NfVV9BRCIsIlJPTEVfQ0FfQyIsIlJPTEVfUEFfUF9EIiwiUk9MRV9VU19HX05fTCIsIlJPTEVfVFJfSV9FIiwiUk9MRV9DQV9EIiwiUk9MRV9UUl9MIiwiUk9MRV9UUl9FX0UiLCJST0xFX1RSX0dfQyIsIlJPTEVfVFJfR19MIiwiUk9MRV9UUl9SIiwiUk9MRV9VU19HX05fQyIsIlJPTEVfUEFfUF9MIiwiUk9MRV9VU19HX05fRCIsIlJPTEVfREVfViIsIlJPTEVfQUNDX0NfQUQiLCJST0xFX0RFX1UiLCJST0xFX1RSX0NfUyIsIlJPTEVfVFJfViIsIlJPTEVfU0VfU19VIiwiUk9MRV9TRV9TX1YiLCJST0xFX1RSX1UiLCJST0xFX1BBX1BfQ19TIiwiUk9MRV9TRV9EIiwiUk9MRV9TRV9HX1UiLCJST0xFX1NFX0dfViIsIlJPTEVfVFJfSF9VIiwiUk9MRV9UUl9IX1YiLCJST0xFX1NFX1NfTCIsIlJPTEVfU0VUX0MiLCJST0xFX1RSX1ZfRCIsIlJPTEVfTk9fTCIsIlJPTEVfU0VfR19MIiwiUk9MRV9TRV9MIiwiUk9MRV9TRV9TX0MiLCJST0xFX1NFX1NfRCIsIlJPTEVfTk9fRCIsIlJPTEVfU0VfR19EIiwiUk9MRV9TRV9VIiwiUk9MRV9QRVJfTCIsIlJPTEVfS0VfSV9VIiwiUk9MRV9TRV9WIiwiUk9MRV9BQ0NfViIsIlJPTEVfTk9fR19VIiwiUk9MRV9LRV9JX1YiLCJST0xFX05PX0dfViIsIlJPTEVfQUNDX0wiLCJST0xFX1NFX0dfQyIsIlJPTEVfRklMRV9VIiwiUk9MRV9OT19HX0wiLCJST0xFX0dSX0MiLCJST0xFX1BFUl9DIiwiUk9MRV9OT19HX0MiLCJST0xFX1NFVF9WIiwiUk9MRV9OT19HX0QiLCJST0xFX0tFX0lfR19MIiwiUk9MRV9UUl9IX0QiLCJST0xFX1NFVF9VIiwiUk9MRV9LRV9JX0dfVSIsIlJPTEVfVFJfSF9MIiwiUk9MRV9TRVRfTCIsIlJPTEVfTk9fViIsIlJPTEVfU0VfQyIsIlJPTEVfS0VfSV9HX1YiLCJST0xFX05PX1UiLCJST0xFX1BBX1BfVSIsIlJPTEVfVFJfR19VIiwiUk9MRV9UUl9HX1YiLCJST0xFX1BBX1BfViIsIlJPTEVfS0VfSV9HX0QiLCJST0xFX0tFX0lfRV9FIiwiUk9MRV9LRV9JX0dfQyIsIlJPTEVfREVfTCIsIlJPTEVfS0VfSV9JX0UiLCJST0xFX0RFX0QiLCJST0xFX0RFX0MiLCJST0xFX0dSX1UiLCJST0xFX0dSX1YiLCJST0xFX0dSX0wiXSwianRpIjoiNjk5Nzg1ZDYtYzQzOS00MmQ2LTk3YjYtZDYzMGRjZWRjYjRhIiwiY2xpZW50X2lkIjoiYWJjX2NsaWVudCJ9.yLJ-swzhWlg87fa4to-re2UEOc-a7b0KEBZ3EuS9avo");
        String token = appPreferences.getToken();
        if (token != null && !token.equals("")) {
            newRequest.addHeader("Authorization", "Bearer " + token);
        }

        Response origResponse = chain.proceed(newRequest.build());
        if (origResponse.code() == 403 || origResponse.code() == 401) {
            LogService.i("Error http =====================> code: " + origResponse.code());
            appPreferences.removeKey(PreferencesService.KEY_BEARER_TOKEN);
            Intent intent = new Intent();
            intent.setAction(Constants.ACTION_EXPIRED_TOKEN);
            LocalBroadcastManager.getInstance(application.getApplicationContext()).sendBroadcast(intent);
        }
        return origResponse;
    }
}
