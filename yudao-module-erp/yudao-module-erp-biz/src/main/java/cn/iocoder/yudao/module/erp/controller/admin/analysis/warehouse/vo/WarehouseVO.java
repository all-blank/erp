package cn.iocoder.yudao.module.erp.controller.admin.analysis.warehouse.vo;

import cn.iocoder.yudao.module.erp.controller.admin.analysis.product.vo.ProductVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

/**
 * @Author: allblank
 * @Description: TODO
 * @Date: 2025-04-06 15:42
 * @Version: 1.0
 */

@Schema(description = "管理后台 - ERP 仓库 Response VO")
@Data
public class WarehouseVO {

    @Schema(description = "仓库编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    private Long warehouseId;

    @Schema(description = "仓库名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "华北总仓")
    private String warehouseName;

    @Schema(description = "产品列表", requiredMode = Schema.RequiredMode.REQUIRED)
    private List<ProductVO> products;

}
