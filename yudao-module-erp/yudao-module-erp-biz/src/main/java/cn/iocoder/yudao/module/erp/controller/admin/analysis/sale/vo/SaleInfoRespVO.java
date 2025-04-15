package cn.iocoder.yudao.module.erp.controller.admin.analysis.sale.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;

/**
 * @Author: allblank
 * @Description: TODO
 * @Date: 2025-04-13 15:35
 * @Version: 1.0
 */

@Schema(description = "管理后台 - ERP 销售信息 Response VO")
@Data
public class SaleInfoRespVO {

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;

    @Schema(description = "销售数量", requiredMode = Schema.RequiredMode.REQUIRED, example = "3000")
    private Long totalSaleCount;

    @Schema(description = "销售总额", requiredMode = Schema.RequiredMode.REQUIRED, example = "3000")
    private Long totalSales;
}
