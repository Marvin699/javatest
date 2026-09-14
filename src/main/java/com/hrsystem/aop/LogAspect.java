package com.hrsystem.aop;

import com.hrsystem.entity.OperationLog;
import com.hrsystem.service.LogService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@Aspect
@Component
public class LogAspect {

    @Autowired
    private LogService logService;

    @Pointcut("execution(* com.hrsystem.controller.admin.AdminController.delete*(..)) || " +
              "execution(* com.hrsystem.controller.admin.EmployeeController.delete*(..)) || " +
              "execution(* com.hrsystem.controller.admin.DepartmentController.delete*(..))")
    public void deletePointcut() {}

    @Around("deletePointcut()")
    public Object doDeleteLog(ProceedingJoinPoint joinPoint) throws Throwable {
        Object result = joinPoint.proceed();

        OperationLog log = new OperationLog();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            log.setUsername(auth.getName());
        }

        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getTarget().getClass().getSimpleName();

        if (methodName.startsWith("delete")) {
            log.setOperation("删除操作: " + className + "." + methodName);
        } else {
            log.setOperation(className + "." + methodName);
        }

        log.setMethod(joinPoint.getSignature().toString());
        log.setParams(Arrays.toString(joinPoint.getArgs()));

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes != null) {
            HttpServletRequest request = attributes.getRequest();
            log.setIp(request.getRemoteAddr());
        }

        logService.addLog(log);
        return result;
    }
}
