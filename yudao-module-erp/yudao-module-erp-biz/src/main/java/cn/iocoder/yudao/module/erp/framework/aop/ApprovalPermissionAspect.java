package cn.iocoder.yudao.module.erp.framework.aop;

import cn.iocoder.yudao.module.erp.enums.ErpAuditStatus;
import cn.iocoder.yudao.module.erp.service.auth.PermissionService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils.getLoginUserId;
import static cn.iocoder.yudao.module.erp.enums.ErrorCodeConstants.APPROVAL_FAIL;
import static cn.iocoder.yudao.module.erp.enums.ErrorCodeConstants.PROCESS_FAIL;

/**
 * @Author: allblank
 * @Description: 审批权限切面注解类
 * @Date: 2025-04-02 20:29
 * @Version: 1.0
 */


@Aspect
@Component
public class ApprovalPermissionAspect {

    @Resource
    private PermissionService permissionService;

    // 拦截所有标记了 @CheckApprovalPermission 的方法
    @Around("@annotation(CheckApprovalPermission)")
    public Object injectPermissionResult(ProceedingJoinPoint joinPoint) throws Throwable {
        // 1. 获取当前用户登录ID
        Long loginUserId = getLoginUserId();

        // 2、获取连接点方法的参数并取出status计算款项审批状态
        Object[] args = joinPoint.getArgs();
        boolean approve = ErpAuditStatus.APPROVE.getStatus().equals(args[1]);

        // 3、 计算权限结果
        boolean hasPermission = permissionService.hasPermission(loginUserId);

        // 4、校验审批权限
        if (!hasPermission) {
            throw exception(approve ? APPROVAL_FAIL : PROCESS_FAIL);
        }

        // 5、 执行原方法
        return joinPoint.proceed();
    }
}