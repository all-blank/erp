package cn.iocoder.yudao.module.erp.controller.admin.analysis.sale;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.erp.controller.admin.analysis.product.vo.ProductSalesRespVO;
import cn.iocoder.yudao.module.erp.controller.admin.analysis.sale.vo.SaleInfoRespVO;
import cn.iocoder.yudao.module.erp.controller.admin.analysis.sale.vo.SaleOrderRespVO;
import cn.iocoder.yudao.module.erp.service.analysis.AnalysisSaleService;
import cn.iocoder.yudao.module.erp.service.statistics.ErpSaleStatisticsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.annotation.security.PermitAll;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.Optional;

import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

/**
 * @Author: allblank
 * @Description: TODO
 * @Date: 2025-04-12 11:18
 * @Version: 1.0
 */


@Tag(name = "管理后台 - ERP 销售分析")
@RestController
@RequestMapping("/erp/analysis")
@Validated
public class AnalysisSaleController {

    @Resource
    private AnalysisSaleService analysisSaleService;


    @GetMapping("/sale-info")
    @Operation(summary = "查询销售数量和销售额")
    @PermitAll
    @Cacheable(
            value = "saleInfo",
            key = "#startTime.getTime() + ':' + #endTime.getTime()",
            condition = "#endTime < new java.util.Date()"
    )
    public CommonResult<SaleInfoRespVO> getSaleInfo(
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date startTime,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date endTime) {

        SaleInfoRespVO saleInfoRespVO = new SaleInfoRespVO();
        
        saleInfoRespVO.setStartTime(startTime);
        saleInfoRespVO.setEndTime(endTime);
        BigDecimal totalSaleCount = analysisSaleService.getTotalSaleCount(startTime, endTime);
        saleInfoRespVO.setTotalSaleCount(Optional.ofNullable(totalSaleCount)
                .map(bd -> bd.setScale(0, RoundingMode.HALF_UP).longValueExact()) // 四舍五入到整数
                .orElse(0L));
        BigDecimal totalSales = analysisSaleService.getTotalSales(startTime, endTime);
        saleInfoRespVO.setTotalSales(Optional.ofNullable(totalSales)
                .map(bd -> bd.setScale(0, RoundingMode.HALF_UP).longValueExact()) // 四舍五入到整数
                .orElse(0L));

        return success(saleInfoRespVO);

    }


    @GetMapping("/sale-order-info")
    @Operation(summary = "查询销售订单信息")
    @PermitAll
    @Cacheable(
            value = "saleOrderInfo",
            key = "#startTime.getTime() + ':' + #endTime.getTime()",
            condition = "#endTime < new java.util.Date()"
    )
    public CommonResult<SaleOrderRespVO> getSaleOrderStatus(
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date startTime,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date endTime) {

        SaleOrderRespVO saleOrderRespVO = new SaleOrderRespVO();

        BeanUtils.copyProperties(analysisSaleService.getSaleOrderStatus(startTime, endTime), saleOrderRespVO);
        saleOrderRespVO.setStartTime(startTime);
        saleOrderRespVO.setEndTime(endTime);

        return success(saleOrderRespVO);
    }


}
