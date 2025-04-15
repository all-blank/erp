package cn.iocoder.yudao.module.erp.dal.dataobject.analysis;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @Author: allblank
 * @Description: TODO
 * @Date: 2025-04-09 20:14
 * @Version: 1.0
 */

@Data
public class ProductSaleCountDO {

    private Long productId;

    private BigDecimal saleCount;


}
