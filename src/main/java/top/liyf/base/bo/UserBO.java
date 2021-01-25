package top.liyf.base.bo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author liyf
 */
@Data
public class UserBO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String userId;

    private String userName;

    private List<String> roles;

    private List<String> userPermissions;

}
