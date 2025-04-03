package cn.iocoder.cloud.module.analysis.Service;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.module.system.api.user.dto.AdminUserRespDTO;


public interface UserService {
    public CommonResult<AdminUserRespDTO> getUser(Long id);
}
