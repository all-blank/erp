package cn.iocoder.yudao.module.erp.controller.admin.analysis;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.module.erp.controller.admin.analysis.vo.ProductVO;
import cn.iocoder.yudao.module.erp.controller.admin.analysis.vo.WarehouseVO;
import cn.iocoder.yudao.module.erp.controller.admin.purchase.vo.in.ErpPurchaseInRespVO;
import cn.iocoder.yudao.module.erp.service.analysis.WarehouseStockService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.annotation.security.PermitAll;
import java.util.List;

import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

/**
 * @Author: allblank
 * @Description: TODO
 * @Date: 2025-04-06 16:07
 * @Version: 1.0
 */


@Tag(name = "管理后台 - ERP 仓库产品库存")
@RestController
@RequestMapping("/erp/analysis")
@Validated
public class WarehouseStockController {

    @Resource
    private WarehouseStockService warehouseStockService;

    @GetMapping("/warehouse-stock")
    @Operation(summary = "查询各仓库产品库存")
    // @PreAuthorize("@ss.hasPermission('erp:purchase-in:query')")
    @PermitAll
    public CommonResult<List<WarehouseVO>> getWarehouseStock() {

        return success(warehouseStockService.getWarehouseStock());
    }


}
