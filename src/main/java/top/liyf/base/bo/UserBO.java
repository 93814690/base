package top.liyf.base.bo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author liyf
 * Created in 2020-05-02
 */
@Data
public class UserBO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String userId;

    private String userName;

    private List<String> userPermissions;

}
