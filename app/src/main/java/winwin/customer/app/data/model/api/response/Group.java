    package winwin.customer.app.data.model.api.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Group {
    private Long id;
    private String name;
    private Integer kind;
    private String description;
    private List<Permission> permissions;
}
