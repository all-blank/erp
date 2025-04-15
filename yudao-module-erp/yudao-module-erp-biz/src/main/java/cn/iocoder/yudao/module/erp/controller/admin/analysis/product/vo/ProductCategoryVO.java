package cn.iocoder.yudao.module.erp.controller.admin.analysis.product.vo;

import lombok.Data;

import java.util.List;

/**
 * @Author: allblank
 * @Description: TODO
 * @Date: 2025-04-09 20:12
 * @Version: 1.0
 */

@Data
public class ProductCategoryVO {

    private Long categoryId;

    private String categoryName;

    private List<ProductVO> products;
}
