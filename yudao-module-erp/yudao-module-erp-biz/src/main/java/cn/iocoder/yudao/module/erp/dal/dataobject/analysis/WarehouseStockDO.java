package cn.iocoder.yudao.module.erp.dal.dataobject.analysis;

import lombok.Data;

/**
 * @Author: allblank
 * @Description: TODO
 * @Date: 2025-04-06 16:31
 * @Version: 1.0
 */

@Data
public class WarehouseStockDO {

    private Long warehouseId;

    private String warehouseName;

    private Long productId;

    private String productName;

    private Long stock;

    private Long unitId;

    private String unitName;
}
