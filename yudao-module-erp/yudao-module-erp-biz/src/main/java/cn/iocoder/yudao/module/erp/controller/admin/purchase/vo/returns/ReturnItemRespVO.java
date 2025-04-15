package cn.iocoder.yudao.module.erp.controller.admin.purchase.vo.returns;

import cn.iocoder.yudao.module.erp.dal.dataobject.product.ErpProductDO;
import cn.iocoder.yudao.module.erp.dal.dataobject.purchase.ErpPurchaseInDO;
import cn.iocoder.yudao.module.erp.dal.dataobject.purchase.ErpPurchaseOrderItemDO;
import cn.iocoder.yudao.module.erp.dal.dataobject.stock.ErpWarehouseDO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @Author: allblank
 * @Description: TODO
 * @Date: 2025-04-09 15:37
 * @Version: 1.0
 */

@Schema(description = "管理后台 - ERP 采购退货项 Response VO")
@Data
public class ReturnItemRespVO {


    @Schema(description = "编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "21")
    private Long id;

    @Schema(description = "采购入库编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "17386")
    private Long inId;

    @Schema(description = "采购订单项编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "17386")
    private Long orderItemId;

    @Schema(description = "仓库编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "17386")
    private Long warehouseId;

    @Schema(description = "仓库产品数量", requiredMode = Schema.RequiredMode.REQUIRED, example = "100.00")
    private BigDecimal stockCount;

    @Schema(description = "产品编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "17386")
    private Long productId;

    @Schema(description = "产品单位编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "17386")
    private Long productUnitId;

    @Schema(description = "产品单位单价", requiredMode = Schema.RequiredMode.REQUIRED, example = "100.00")
    private BigDecimal productPrice;

    @Schema(description = "数量", requiredMode = Schema.RequiredMode.REQUIRED, example = "100.00")
    private BigDecimal count;

    @Schema(description = "总价", requiredMode = Schema.RequiredMode.REQUIRED, example = "100.00")
    private BigDecimal totalPrice;

    @Schema(description = "税率", requiredMode = Schema.RequiredMode.REQUIRED, example = "5")
    private BigDecimal taxPercent;

    @Schema(description = "税额", requiredMode = Schema.RequiredMode.REQUIRED, example = "100.00")
    private BigDecimal taxPrice;

    @Schema(description = "备注", requiredMode = Schema.RequiredMode.REQUIRED, example = "哈哈")
    private String remark;
}
