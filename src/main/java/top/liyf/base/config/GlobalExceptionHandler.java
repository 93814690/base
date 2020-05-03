package top.liyf.base.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import top.liyf.base.exception.BusinessException;
import top.liyf.base.result.ResultBean;
import top.liyf.base.result.ResultCode;

/**
 * @author liyf
 * Created in 2020-05-02
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public ResultBean businessExceptionHandler(BusinessException e) {
        return new ResultBean(e);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResultBean runtimeExceptionHandler(RuntimeException e) {

        log.error("系统异常", e);
        return new ResultBean(ResultCode.SYSTEM_ERROR);
    }
}
