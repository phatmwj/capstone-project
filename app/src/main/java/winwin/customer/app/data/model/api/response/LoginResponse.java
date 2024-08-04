package winwin.customer.app.data.model.api.response;

import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data
public class LoginResponse {
    @SerializedName("access_token")
    private String accessToken;
}
