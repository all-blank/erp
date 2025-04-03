package cn.iocoder.yudao.module.erp.service.auth;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.module.erp.enums.ApprovalAuthConstants;
import cn.iocoder.yudao.module.erp.util.RedisKeyUtils;
import cn.iocoder.yudao.module.system.api.dept.PostApi;
import cn.iocoder.yudao.module.system.api.dept.dto.PostRespDTO;
import cn.iocoder.yudao.module.system.api.user.AdminUserApi;
import cn.iocoder.yudao.module.system.api.user.dto.AdminUserRespDTO;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;


import static cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils.getLoginUserId;

/**
 * @Author: allblank
 * @Description: 判断当前登录用户是否有审批权限
 * @Date: 2025-04-02 16:36
 * @Version: 1.0
 */

@Service
public class ApprovalPermissionServiceImpl implements ApprovalPermissionService {

    // 定义需要检查的职位集合（避免硬编码）
    private static final Set<String> ALLOWED_POSITIONS = new HashSet<>(Arrays.asList(
            ApprovalAuthConstants.POST_CEO,
            ApprovalAuthConstants.POST_MANAGER
    ));

    @Resource
    private RedisTemplate<String, Boolean> redisTemplate;

    @Resource
    private AdminUserApi adminUserApi;

    @Resource
    private PostApi postApi;

    @Override
    public boolean hasApprovalPermission() {

        // 1. 获取当前用户登录ID
        Long loginUserId = getLoginUserId();

        // 2. 通过adminUserApi接口获取当前用户信息
        CommonResult<AdminUserRespDTO> user = adminUserApi.getUser(loginUserId);
        // 2.1. 获取用户所属租户ID
        Long tenantId = user.getData().getTenantId();

        // 3. 先查 Redis 缓存
        String key = RedisKeyUtils.buildApprovalPermissionKey(tenantId, loginUserId);
        Boolean cachedResult = redisTemplate.opsForValue().get(key);
        if (cachedResult != null) {
            return cachedResult;
        }

        // 4. 缓存未命中，查询数据库判断权限
        boolean hasPermission = checkPermissionInDb(user);

        // 5. 结果写入 Redis，设置过期时间（如 1 天）
        redisTemplate.opsForValue().set(key, hasPermission, 1, TimeUnit.DAYS);

        return hasPermission;
    }


    private boolean checkPermissionInDb(CommonResult<AdminUserRespDTO> user) {

        // 1、获取当前用户职位集合
        Set<Long> postIds = user.getData().getPostIds();
        // 2、处理空集合或 null
        if (postIds == null || postIds.isEmpty()) {
            return false;
        }
        // 通过PostApi接口获取职位信息列表
        List<PostRespDTO> posts = postApi.getPostList(postIds).getData();
        // 3、获取职位编码集合
        Set<String> postCodes = posts.stream()
                .map(PostRespDTO::getCode)
                .filter(code -> code != null)
                .collect(Collectors.toSet());
        // 4、判断两个集合是否有交集
        return !Collections.disjoint(postCodes, ALLOWED_POSITIONS);
    }
}

