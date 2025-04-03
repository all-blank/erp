package cn.iocoder.cloud.module.analysis.controller.admin;

import cn.iocoder.cloud.module.analysis.Service.UserService;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.module.system.api.user.AdminUserApi;
import cn.iocoder.yudao.module.system.api.user.dto.AdminUserRespDTO;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;
import static cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils.getLoginUserDeptId;
import static cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils.getLoginUserId;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.annotation.security.PermitAll;
import java.util.Set;

/**
 * @Author: allblank
 * @Description: TODO
 * @Date: 2025-03-29 15:06
 * @Version: 1.0
 */



@Tag(name = "管理后台 - Test")
@RestController
@RequestMapping("/analysis/test")
@Validated
public class TestController {

    @Resource
    private UserService userService;

    @Resource
    private AdminUserApi adminUserApi;

    @GetMapping("/get")
    @Operation(summary = "获取 test 信息")
    public CommonResult<String> get() {
        return success("哈哈哈");
    }

    @GetMapping("/getDept")
    @Operation(summary = "获取当前用户职位信息")
    @PermitAll
    public CommonResult<String> getDept() {
        Long loginUserId = getLoginUserId();
        System.out.println(loginUserId);
        CommonResult<AdminUserRespDTO> user = adminUserApi.getUser(loginUserId);
        Set<Long> postIds = user.getData().getPostIds();
        System.out.println(postIds);
        return success("成功");
    }

}