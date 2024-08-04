package winwin.customer.app.data.remote;

import okhttp3.RequestBody;
import retrofit2.http.Headers;
import winwin.customer.app.data.model.api.ResponseWrapper;
import winwin.customer.app.data.model.api.request.LoginRequest;
import winwin.customer.app.data.model.api.response.LoginResponse;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;
import winwin.customer.app.data.model.api.response.UploadFileResponse;

public interface ApiService {
    @POST("/api/token")
    Observable<LoginResponse> login(@Body LoginRequest request);

    @POST("/v1/file/upload")
    @Headers({"isMedia:1"})
    Observable<ResponseWrapper<UploadFileResponse>> uploadFile(@Body RequestBody requestBody);
}
