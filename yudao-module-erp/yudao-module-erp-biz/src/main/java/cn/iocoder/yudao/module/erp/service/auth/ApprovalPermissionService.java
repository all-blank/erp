package cn.iocoder.yudao.module.erp.service.auth;

public interface ApprovalPermissionService {

    /**
     * 判断用户是否有审批权限
     *
     * @return true 有权限，false 无权限
     */
    boolean hasApprovalPermission();
}
