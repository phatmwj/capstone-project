package winwin.customer.app.data.model.api.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class KeyResponse {
    private Long id;
    private Integer status;
    private String createdDate;
    private String modifiedDate;
    private String name;
    private Integer kind;
    private String description;
    private AccountResponse account;
    private KeyGroupResponse keyInformationGroup;
    private String additionalInformation;
}