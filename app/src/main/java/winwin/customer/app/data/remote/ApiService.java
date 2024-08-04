package winwin.customer.app.data.remote;

import winwin.customer.app.data.model.api.request.LoginRequest;
import winwin.customer.app.data.model.api.response.LoginResponse;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiService {
    @POST("/api/token")
    Observable<LoginResponse> login(@Body LoginRequest request);
}
