package cn.iocoder.yudao.module.erp.service.auth;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.module.erp.dal.redis.RedisKeyConstants;
import cn.iocoder.yudao.module.erp.enums.AuthPostConstants;
import cn.iocoder.yudao.module.system.api.dept.PostApi;
import cn.iocoder.yudao.module.system.api.dept.dto.PostRespDTO;
import cn.iocoder.yudao.module.system.api.user.AdminUserApi;
import cn.iocoder.yudao.module.system.api.user.dto.AdminUserRespDTO;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @Author: allblank
 * @Description: 判断当前登录用户是否有审批权限
 * @Date: 2025-04-02 16:36
 * @Version: 1.0
 */

@Service
public class PermissionServiceImpl implements PermissionService {

    // 定义需要检查的职位集合（避免硬编码）
    private static final Set<String> ALLOWED_POSITIONS = new HashSet<>(Arrays.asList(
            AuthPostConstants.POST_CEO,
            AuthPostConstants.POST_MANAGER
    ));

    @Resource
    private AdminUserApi adminUserApi;

    @Resource
    private PostApi postApi;

    @Override
    @Cacheable(value = RedisKeyConstants.PERMISSION_KEY + "#1h", key = "#loginUserId")
    public boolean hasPermission(Long loginUserId) {

        // 1. 通过adminUserApi接口获取当前用户信息
        CommonResult<AdminUserRespDTO> user = adminUserApi.getUser(loginUserId);

        // 2. 查询数据库判断权限
        return checkPermissionInDb(user);
    }


    private boolean checkPermissionInDb(CommonResult<AdminUserRespDTO> user) {

        // 1、获取当前用户职位集合
        Set<Long> postIds = user.getData().getPostIds();

        // 2、处理空集合或 null
        if (postIds == null || postIds.isEmpty()) {
            return false;
        }

        // 3、通过PostApi接口获取职位信息列表
        List<PostRespDTO> posts = postApi.getPostList(postIds).getData();

        // 4、获取职位编码集合
        Set<String> postCodes = posts.stream()
                .map(PostRespDTO::getCode)
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());

        // 5、判断两个集合是否有交集
        return !Collections.disjoint(postCodes, ALLOWED_POSITIONS);
    }
}

