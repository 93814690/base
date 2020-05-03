package top.liyf.base.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import top.liyf.base.annotation.NeedPermission;
import top.liyf.base.bo.UserBO;
import top.liyf.base.exception.BusinessException;
import top.liyf.base.result.ResultCode;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.List;

/**
 * @author liyf
 * Created in 2020-05-02
 */
@Aspect
@Component
public class PermissionAspect {

    @Pointcut("@annotation(top.liyf.base.annotation.NeedPermission)")
    public void privilege() {
    }

    @Around("privilege()")
    public Object checkPermission(ProceedingJoinPoint joinPoint) throws Throwable {
        //获取访问目标方法
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method targetMethod = methodSignature.getMethod();

        NeedPermission annotation = targetMethod.getAnnotation(NeedPermission.class);
        String permission = annotation.value();

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        UserBO user = (UserBO) request.getSession().getAttribute("user");
        List<String> userPermissions = user.getUserPermissions();
        if (userPermissions != null && userPermissions.contains(permission)) {
            return joinPoint.proceed();
        }

        throw new BusinessException(ResultCode.NOT_ALLOWED);
    }

}
