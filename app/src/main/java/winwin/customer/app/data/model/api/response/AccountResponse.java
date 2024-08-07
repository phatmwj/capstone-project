package winwin.customer.app.data.model.api.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountResponse {
    private Long id;
    private Integer kind;
    private String username;
    private String email;
    private String fullName;
    private String avatarPath;
    private Boolean isSuperAdmin;
    private Group group;
}
