package winwin.customer.app.data.remote;

import okhttp3.RequestBody;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;
import winwin.customer.app.data.model.api.ResponseListObj;
import winwin.customer.app.data.model.api.ResponseWrapper;
import winwin.customer.app.data.model.api.request.LoginRequest;
import winwin.customer.app.data.model.api.response.KeyGroupResponse;
import winwin.customer.app.data.model.api.response.KeyResponse;
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

    @GET("/v1/key-information-group/list?sort=createdDate,desc")
    Observable<ResponseWrapper<ResponseListObj<KeyGroupResponse>>> getKeyGroupList(@Query("page") Integer pageNumber,
                                                                                   @Query("size") Integer pageSize);

    @GET("/v1/key-information/list")
    Observable<ResponseWrapper<ResponseListObj<KeyResponse>>> getKeyInformation(@Query("page") Integer pageNumber,
                                                                                @Query("size") Integer pageSize,
                                                                                @Query("kind") Integer kind,
                                                                                @Query("keyInformationGroupId") Long keyInformationGroupId,
                                                                                @Query("isPaged") Integer isPaged);

}
