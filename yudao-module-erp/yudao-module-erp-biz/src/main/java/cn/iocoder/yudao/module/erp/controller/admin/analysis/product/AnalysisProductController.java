package cn.iocoder.yudao.module.erp.controller.admin.analysis.product;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.module.erp.controller.admin.analysis.product.vo.ProductCategoryVO;
import cn.iocoder.yudao.module.erp.controller.admin.analysis.product.vo.ProductSaleCountRespVO;
import cn.iocoder.yudao.module.erp.controller.admin.analysis.product.vo.ProductSalesRespVO;
import cn.iocoder.yudao.module.erp.controller.admin.analysis.product.vo.ProductVO;
import cn.iocoder.yudao.module.erp.dal.dataobject.analysis.ProductSaleCountDO;
import cn.iocoder.yudao.module.erp.dal.dataobject.analysis.ProductSalesDO;
import cn.iocoder.yudao.module.erp.dal.dataobject.analysis.ProductStockDO;
import cn.iocoder.yudao.module.erp.dal.redis.RedisKeyConstants;
import cn.iocoder.yudao.module.erp.service.analysis.AnalysisProductService;
import cn.iocoder.yudao.module.erp.service.analysis.DTO.ProductInfoDTO;
import cn.iocoder.yudao.module.erp.service.analysis.converter.ProductConverter;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.format.annotation.DateTimeFormat;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.annotation.security.PermitAll;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

/**
 * @Author: allblank
 * @Description: TODO
 * @Date: 2025-04-09 19:18
 * @Version: 1.0
 */


@Tag(name = "管理后台 - ERP 产品分析")
@RestController
@RequestMapping("/erp/analysis")
@Validated
public class AnalysisProductController {

    @Resource
    private AnalysisProductService analysisProductService;

    @GetMapping("/product-stock")
    @Operation(summary = "查询产品库存")
    @PreAuthorize("@ss.hasPermission('erp:analysis:query')")
    public CommonResult<List<ProductVO>> getProductStock() {
        List<ProductStockDO> productStock = analysisProductService.getProductStock();
        return success(ProductConverter.convertToVOList(productStock));
    }


    @GetMapping("/product-sales")
    @Operation(summary = "获取产品销售额信息")
    @PreAuthorize("@ss.hasPermission('erp:analysis:query')")
    @Cacheable(
            value = RedisKeyConstants.ANALYSIS_KEY_PREFIX + RedisKeyConstants.PRODUCT_SALES + "#7d",
            key = "#startTime.toString() + ':' + #endTime.toString()",
            condition = "#endTime.isBefore(T(java.time.LocalDateTime).now())"
    )
    public CommonResult<ProductSalesRespVO> getProductSaleInfo(
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime startTime,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime endTime) {

        // 1. 获取销售额数据
        List<ProductSalesDO> productSalesList = analysisProductService.getProductSales(startTime, endTime);

        // 2. 获取所有产品详细信息
        List<ProductInfoDTO> productInfoList = analysisProductService.getProductInfo();

        // 3. 构建产品ID -> 产品详情的映射（快速查询）
        Map<Long, ProductInfoDTO> productInfoMap = productInfoList.stream()
                .collect(Collectors.toMap(ProductInfoDTO::getProductId, Function.identity()));

        // 4. 数据转换处理
        List<ProductVO> productVOList = productSalesList.stream()
                // 过滤无效数据（确保有对应的产品信息）
                .filter(sale -> productInfoMap.containsKey(sale.getProductId()))
                .map(salesDO -> convertToProductVO(salesDO, productInfoMap))
                .collect(Collectors.toList());

        // 5. 构建响应对象
        ProductSalesRespVO respVO = new ProductSalesRespVO()
                .setStartTime(startTime)
                .setEndTime(endTime)
                .setProducts(productVOList);

        return success(respVO);

    }

    @GetMapping("/product-sale-count-based-category")
    @Operation(summary = "获取产品销量（根据产品分类进行分组）")
    @PreAuthorize("@ss.hasPermission('erp:analysis:query')")
    @Cacheable(
            value = RedisKeyConstants.ANALYSIS_KEY_PREFIX + RedisKeyConstants.PRODUCT_SALECOUNT_BASE_DCATEGORY + "#7d",
            key = "#startTime.toString() + ':' + #endTime.toString()",
            condition = "#endTime.isBefore(T(java.time.LocalDateTime).now())"
    )
    public CommonResult<ProductSaleCountRespVO> getProductSaleCountBasedCategory(
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime startTime,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime endTime) {

        // 1. 获取销售数据
        List<ProductSaleCountDO> saleData = analysisProductService.getProductSaleCount(startTime, endTime);

        // 2. 获取所有产品详细信息（假设已实现获取方法）
        List<ProductInfoDTO> productInfoList = analysisProductService.getProductInfo();

        // 3. 构建产品ID -> 产品详情的映射（快速查询）
        Map<Long, ProductInfoDTO> productInfoMap = productInfoList.stream()
                .collect(Collectors.toMap(ProductInfoDTO::getProductId, Function.identity()));

        // 4. 数据转换处理
        List<ProductCategoryVO> categoryVOS = saleData.stream()
                // 过滤无效数据（确保有对应的产品信息）
                .filter(sale -> productInfoMap.containsKey(sale.getProductId()))
                // 按分类分组
                .collect(Collectors.groupingBy(
                        sale -> productInfoMap.get(sale.getProductId()).getTopCategoryId(),
                        Collectors.mapping(sale -> convertToProductVO(sale, productInfoMap), Collectors.toList())
                ))
                .entrySet().stream()
                .map(entry -> {
                    ProductInfoDTO sample = productInfoList.stream()
                            .filter(p -> p.getTopCategoryId().equals(entry.getKey()))
                            .findFirst()
                            .orElseThrow(() -> new RuntimeException("找不到对应分类"));

                    return new ProductCategoryVO()
                            .setCategoryId(entry.getKey())
                            .setCategoryName(sample.getTopCategoryName())
                            .setProducts(entry.getValue());
                })
                .collect(Collectors.toList());

        // 5. 构建响应对象
        ProductSaleCountRespVO respVO = new ProductSaleCountRespVO()
                .setStartTime(startTime)
                .setEndTime(endTime)
                .setSaleData(categoryVOS);

        return success(respVO);
    }

    private <T> ProductVO convertToProductVO(T saleDO, Map<Long, ProductInfoDTO> productInfoMap) {
        ProductVO productVO = new ProductVO();
        Long productId;

        // 类型判断并设置特有属性
        if (saleDO instanceof ProductSaleCountDO) {
            ProductSaleCountDO saleCountDO = (ProductSaleCountDO) saleDO;
            productId = saleCountDO.getProductId();
            productVO.setSaleCount(Optional.ofNullable(saleCountDO.getSaleCount())
                    .map(BigDecimal::longValueExact) // 精确转换 BigDecimal 到 Long
                    .orElse(0L)); // 空值时设为 0
            // totalSales 保持默认值 null
        } else if (saleDO instanceof ProductSalesDO) {
            ProductSalesDO salesDO = (ProductSalesDO) saleDO;
            productId = salesDO.getProductId();
            productVO.setTotalSales(Optional.ofNullable(salesDO.getTotalSales())
                    .map(bd -> bd.setScale(0, RoundingMode.HALF_UP).longValueExact()) // 四舍五入到整数
                    .orElse(0L));
            // saleCount 保持默认值 null
        } else {
            throw new IllegalArgumentException("不支持的类型: " + saleDO.getClass().getName());
        }

        // 设置共有属性
        ProductInfoDTO info = productInfoMap.get(productId);
        if (info != null) {
            productVO.setProductId(productId);
            productVO.setProductName(info.getProductName());
            productVO.setUnitName(info.getUnitName());
            productVO.setUnitId(info.getUnitId());
            // stockCount 暂无数据源，设为 null
            productVO.setStockCount(null);
        }

        return productVO;
    }
}



