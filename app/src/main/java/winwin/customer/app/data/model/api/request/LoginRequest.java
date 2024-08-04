package winwin.customer.app.data.model.api.request;

import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data
public class LoginRequest {
    @SerializedName("grant_type")
    private String grantType = "password";
    private String username;
    private String password;
}
