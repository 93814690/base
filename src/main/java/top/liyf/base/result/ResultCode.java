package top.liyf.base.result;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author liyf
 */
@Getter
@AllArgsConstructor
public enum  ResultCode {

    /**
     * 正确执行后的返回
     */
    SUCCESS("00000", "成功"),

    CLIENT_ERROR("A0001", "用户端错误"),

    NOT_LOGGED_IN("A0230", "未登录"),
    NOT_ALLOWED("A0301", "没有权限"),

    NOT_FOUND("A0404", "未找到"),

    SYSTEM_ERROR("B0001", "系统执行出错");

    private final String code;

    private final String msg;

}
