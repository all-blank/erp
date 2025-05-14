package cn.iocoder.yudao.module.erp.service.analysis;


import cn.iocoder.yudao.framework.common.enums.CommonStatusEnum;
import cn.iocoder.yudao.module.erp.controller.admin.product.vo.category.ErpProductCategoryListReqVO;
import cn.iocoder.yudao.module.erp.controller.admin.product.vo.product.ErpProductRespVO;
import cn.iocoder.yudao.module.erp.dal.dataobject.analysis.ProductSaleCountDO;
import cn.iocoder.yudao.module.erp.dal.dataobject.analysis.ProductSalesDO;
import cn.iocoder.yudao.module.erp.dal.dataobject.analysis.ProductStockDO;
import cn.iocoder.yudao.module.erp.dal.dataobject.product.ErpProductCategoryDO;
import cn.iocoder.yudao.module.erp.dal.mysql.analysis.AnalysisProductMapper;
import cn.iocoder.yudao.module.erp.dal.redis.RedisKeyConstants;
import cn.iocoder.yudao.module.erp.service.analysis.DTO.CategoryInfoDTO;
import cn.iocoder.yudao.module.erp.service.analysis.DTO.ProductInfoDTO;
import cn.iocoder.yudao.module.erp.service.product.ErpProductCategoryService;
import cn.iocoder.yudao.module.erp.service.product.ErpProductService;
import com.baomidou.dynamic.datasource.annotation.Slave;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.erp.enums.ErrorCodeConstants.PRODUCT_CATEGORY_NOT_EXISTS;

/**
 * @Author: allblank
 * @Description: TODO
 * @Date: 2025-04-09 17:28
 * @Version: 1.0
 */

@Service
@Slave
public class AnalysisProductServiceImpl implements AnalysisProductService {

    @Resource
    private AnalysisProductMapper analysisProductMapper;

    @Resource
    private ErpProductService productService;

    @Resource
    private ErpProductCategoryService productCategoryService;


    @Override
    public List<ProductSaleCountDO> getProductSaleCount(LocalDateTime startTime, LocalDateTime endTime) {
        return analysisProductMapper.getProductSaleCount(startTime, endTime);
    }

    @Override
    public List<ProductStockDO> getProductStock() {
        return analysisProductMapper.getProductStock();
    }

    @Override
    public List<ProductSalesDO> getProductSales(LocalDateTime startTime, LocalDateTime endTime) {
        return analysisProductMapper.getProductSales(startTime, endTime);
    }


    // 查询所有产品及其一级分类和单位，并缓存到 Redis
    @Override
    @Cacheable(value = RedisKeyConstants.ANALYSIS_KEY_PREFIX + RedisKeyConstants.PRODUCT_INFO_CACHE + "#10d", key = "'product_info_cache'")
    public List<ProductInfoDTO> getProductInfo() {
        // 1. 查询启用的产品列表
        List<ErpProductRespVO> products = productService.getProductVOListByStatus(CommonStatusEnum.ENABLE.getStatus());

        // 2. 查询分类数据并构建缓存
        List<ErpProductCategoryDO> categories = productCategoryService.getProductCategoryList(
                new ErpProductCategoryListReqVO().setStatus(CommonStatusEnum.ENABLE.getStatus()));
        Map<Long, ErpProductCategoryDO> categoryMap = categories.stream()
                .collect(Collectors.toMap(ErpProductCategoryDO::getId, Function.identity()));

        // 3. 转换为DTO列表（含分类处理）
        return products.stream()
                .map(product -> convertToDTO(product, categoryMap))
                .filter(Objects::nonNull) // 过滤无效数据
                .collect(Collectors.toList());
    }

    private ProductInfoDTO convertToDTO(ErpProductRespVO product, Map<Long, ErpProductCategoryDO> categoryMap) {
        // 获取顶级分类信息
        CategoryInfoDTO topCategory = findTopCategory(product.getCategoryId(), categoryMap);
        if (topCategory == null) {
            throw exception(PRODUCT_CATEGORY_NOT_EXISTS);
        }

        // 构建DTO对象
        return new ProductInfoDTO()
                .setProductId(product.getId())
                .setProductName(product.getName())
                .setUnitId(product.getUnitId())
                .setUnitName(product.getUnitName())
                .setTopCategoryId(topCategory.getId())
                .setTopCategoryName(topCategory.getName());
    }

    // 递归查找顶级分类（返回强类型对象）
    private CategoryInfoDTO findTopCategory(Long categoryId, Map<Long, ErpProductCategoryDO> categoryMap) {
        ErpProductCategoryDO category = categoryMap.get(categoryId);
        if (category == null) return null;

        if (category.getParentId() == 0L) {
            return new CategoryInfoDTO(category.getId(), category.getName());
        }
        return findTopCategory(category.getParentId(), categoryMap);
    }


}
