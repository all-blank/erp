package cn.iocoder.yudao.module.erp.framework.aop;

import cn.iocoder.yudao.module.erp.service.auth.PermissionService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils.getLoginUserId;
import static cn.iocoder.yudao.module.erp.enums.ErrorCodeConstants.EDIT_FAIL;

/**
 * @Author: allblank
 * @Description: 编辑权限切面注解类
 * @Date: 2025-04-03 11:58
 * @Version: 1.0
 */

@Aspect
@Component
public class EditPermissionAspect {

    @Resource
    private PermissionService permissionService;

    // 拦截所有标记了 @CheckEditPermission 的方法
    @Around("@annotation(CheckEditPermission)")
    public Object injectPermissionResult(ProceedingJoinPoint joinPoint) throws Throwable {
        // 1. 获取当前用户登录ID
        Long loginUserId = getLoginUserId();

        // 2、 计算权限结果
        boolean hasPermission = permissionService.hasPermission(loginUserId);

        // 3、校验编辑权限
        if (!hasPermission) {
            throw exception(EDIT_FAIL);
        }

        // 4、 执行原方法
        return joinPoint.proceed();
    }

}
