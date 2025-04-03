package cn.iocoder.yudao.module.erp.util;

/**
 * @Author: allblank
 * @Description: redis key 工具类(生成存储审批权限的key)
 * @Date: 2025-04-02 22:16
 * @Version: 1.0
 */


public class RedisKeyUtils {

    // 统一键格式：系统名:模块名:租户ID:用户ID
    private static final String PERMISSION_KEY_FORMAT = "erp:approval:tenant:%s:user:%s";

    public static String buildApprovalPermissionKey(Long tenantId, Long userId) {
        return String.format(
                PERMISSION_KEY_FORMAT,
                tenantId,
                userId
        );
    }
}