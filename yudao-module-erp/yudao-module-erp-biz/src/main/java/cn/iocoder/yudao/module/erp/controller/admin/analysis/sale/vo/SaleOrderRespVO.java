package cn.iocoder.yudao.module.erp.controller.admin.analysis.sale.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author: allblank
 * @Description: TODO
 * @Date: 2025-04-13 16:17
 * @Version: 1.0
 */

@Data
public class SaleOrderRespVO {

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endTime;

    private Long saleOrderTotalCount;      // erp_sale_order 总订单数

    private Long noApprovalSaleOrderCount;  // erp_sale_order 中未审批的订单数

    private Long saleOutOrderCount;         // erp_sale_out 总订单数

    private Long saleReturnOrderCount;      // erp_sale_return 总订单数

}
