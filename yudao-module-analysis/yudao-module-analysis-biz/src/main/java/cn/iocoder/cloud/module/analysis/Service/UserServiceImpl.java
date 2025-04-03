package cn.iocoder.cloud.module.analysis.Service;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.module.system.api.user.AdminUserApi;
import cn.iocoder.yudao.module.system.api.user.dto.AdminUserRespDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author: allblank
 * @Description: TODO
 * @Date: 2025-04-02 12:41
 * @Version: 1.0
 */

@Service("UserService")
@Slf4j
public class UserServiceImpl implements UserService {

    @Resource
    private AdminUserApi adminUserApi;

    @Override
    public CommonResult<AdminUserRespDTO> getUser(Long id) {
        return adminUserApi.getUser(id);
    }
}
