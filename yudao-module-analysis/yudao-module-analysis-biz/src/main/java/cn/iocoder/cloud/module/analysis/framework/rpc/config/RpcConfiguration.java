package cn.iocoder.cloud.module.analysis.framework.rpc.config;

import cn.iocoder.yudao.module.system.api.user.AdminUserApi;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: allblank
 * @Description: TODO
 * @Date: 2025-04-02 12:26
 * @Version: 1.0
 */


@Configuration(proxyBeanMethods = false)
@EnableFeignClients(clients = {AdminUserApi.class})
public class RpcConfiguration {
}