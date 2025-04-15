package cn.iocoder.yudao.module.erp.controller.admin.analysis.product.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @Author: allblank
 * @Description: TODO
 * @Date: 2025-04-06 16:01
 * @Version: 1.0
 */

@Schema(description = "管理后台 - ERP 产品库存 Response VO")
@Data
public class ProductVO {

    @Schema(description = "产品编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "3")
    private Long productId;

    @Schema(description = "产品名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "土豆")
    private String productName;

    @Schema(description = "库存数量", requiredMode = Schema.RequiredMode.NOT_REQUIRED, example = "28684")
    private Long stockCount;

    @Schema(description = "产品单位ID", requiredMode = Schema.RequiredMode.NOT_REQUIRED, example = "2")
    private Long unitId;

    @Schema(description = "产品单位", requiredMode = Schema.RequiredMode.REQUIRED, example = "千克")
    private String unitName;

    @Schema(description = "产品销量", requiredMode = Schema.RequiredMode.NOT_REQUIRED, example = "3000")
    private Long saleCount;

    @Schema(description = "产品销售额", requiredMode = Schema.RequiredMode.NOT_REQUIRED, example = "3000")
    private Long totalSales;

}
