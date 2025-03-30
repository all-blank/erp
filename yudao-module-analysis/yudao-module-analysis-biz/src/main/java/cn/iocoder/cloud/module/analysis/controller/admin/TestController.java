package cn.iocoder.cloud.module.analysis.controller.admin;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/get")
    @Operation(summary = "获取 test 信息")
    public CommonResult<String> get() {
        return success("哈哈哈");
    }

}