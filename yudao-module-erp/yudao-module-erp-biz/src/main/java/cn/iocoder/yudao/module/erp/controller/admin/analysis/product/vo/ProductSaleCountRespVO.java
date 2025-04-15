package cn.iocoder.yudao.module.erp.controller.admin.analysis.product.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @Author: allblank
 * @Description: TODO
 * @Date: 2025-04-10 11:23
 * @Version: 1.0
 */

@Data
public class ProductSaleCountRespVO {

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;

    private List<ProductCategoryVO> saleData;
}
