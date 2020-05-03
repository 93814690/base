package top.liyf.base.exception;



import lombok.Getter;
import lombok.Setter;
import top.liyf.base.result.ResultCode;

import java.io.Serializable;

/**
 * @author liyf
 * Created in 2020-05-02
 */
@Getter
@Setter
public class BusinessException extends RuntimeException implements Serializable {

    private static final long serialVersionUID = 1L;

    private String code;

    private String msg;

    public BusinessException(String msg) {
        super(msg);
        this.code = ResultCode.SYSTEM_ERROR.getCode();
        this.msg = msg;
    }

    public BusinessException(ResultCode code) {
        super(code.getMsg());
        this.code = code.getCode();
        this.msg = code.getMsg();
    }

    public BusinessException(ResultCode code, String msg) {
        super(msg);
        this.code = code.getCode();
        this.msg = msg;
    }
}
