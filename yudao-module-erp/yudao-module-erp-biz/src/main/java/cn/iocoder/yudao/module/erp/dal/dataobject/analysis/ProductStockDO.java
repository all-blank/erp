package cn.iocoder.yudao.module.erp.dal.dataobject.analysis;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @Author: allblank
 * @Description: TODO
 * @Date: 2025-04-11 10:37
 * @Version: 1.0
 */

@Data
public class ProductStockDO {


    private Long productId;

    private String productName;

    private BigDecimal stockCount;

    private String unitName;
}
