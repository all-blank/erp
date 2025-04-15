package cn.iocoder.yudao.module.erp.dal.dataobject.analysis;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @Author: allblank
 * @Description: TODO
 * @Date: 2025-04-12 13:02
 * @Version: 1.0
 */

@Data
public class ProductSalesDO {

    private Long productId;

    private BigDecimal totalSales;
}
