package cn.iocoder.yudao.module.erp.dal.dataobject.analysis;


import lombok.Data;

/**
 * @Author: allblank
 * @Description: TODO
 * @Date: 2025-04-13 16:08
 * @Version: 1.0
 */


@Data
public class SaleOrderStatusDO {
    private Long saleOrderTotalCount;      // erp_sale_order 总订单数
    private Long noApprovalSaleOrderCount;  // erp_sale_order 中未审批的订单数
    private Long saleOutOrderCount;         // erp_sale_out 总订单数
    private Long saleReturnOrderCount;      // erp_sale_return 总订单数
}
