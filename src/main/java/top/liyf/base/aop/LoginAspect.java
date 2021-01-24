package top.liyf.base.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import top.liyf.base.annotation.AnonymousInterface;
import top.liyf.base.bo.UserBO;
import top.liyf.base.result.ResultBean;
import top.liyf.base.result.ResultCode;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * @author liyf
 */
@Aspect
@Component
@Order(-99)
@Slf4j
public class LoginAspect {

    /**
     * 定义切点
     */
    @Pointcut("execution( * top.liyf.base.web..*(..))")
    public void privilege() {
    }

    @Around("privilege()")
    public Object checkLogin(ProceedingJoinPoint joinPoint) throws Throwable {
        //获取访问目标方法
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method targetMethod = methodSignature.getMethod();

        // 判断是否可以匿名
        AnonymousInterface annotation = targetMethod.getAnnotation(AnonymousInterface.class);
        if (annotation != null) {
            return joinPoint.proceed();
        }

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        UserBO user = (UserBO) request.getSession().getAttribute("user");

        if (user == null) {
            String returnName = methodSignature.getReturnType().getSimpleName();
            if ("ResultBean".equals(returnName)) {
                // 接口
                return new ResultBean<>(ResultCode.NOT_LOGGED_IN);
            } else {
                // 跳转登录页面
                return "login.html";
            }
        }

        return joinPoint.proceed();
    }
}
