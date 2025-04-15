package cn.iocoder.yudao.module.erp.service.analysis.DTO;

import lombok.Data;

/**
 * @Author: allblank
 * @Description: TODO
 * @Date: 2025-04-10 11:52
 * @Version: 1.0
 */

@Data
public class ProductInfoDTO {

    private Long productId;

    private String productName;

    private Long unitId;

    private String unitName;

    private Long topCategoryId;

    private String topCategoryName;

}
