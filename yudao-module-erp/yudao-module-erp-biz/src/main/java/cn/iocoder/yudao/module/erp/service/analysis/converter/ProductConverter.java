package cn.iocoder.yudao.module.erp.service.analysis.converter;

/**
 * @Author: allblank
 * @Description: TODO
 * @Date: 2025-04-11 11:07
 * @Version: 1.0
 */


import cn.iocoder.yudao.module.erp.controller.admin.analysis.product.vo.ProductVO;
import cn.iocoder.yudao.module.erp.dal.dataobject.analysis.ProductStockDO;
import org.springframework.beans.BeanUtils;

import java.math.RoundingMode;
import java.util.List;
import java.util.stream.Collectors;

public class ProductConverter {

    public static ProductVO convertToVO(ProductStockDO productStockDO) {
        if (productStockDO == null) {
            return null;
        }

        ProductVO vo = new ProductVO();
        // 基础字段直接拷贝
        BeanUtils.copyProperties(productStockDO, vo);

        // 特殊字段处理
        // BigDecimal 转 Long（四舍五入取整）
        if (productStockDO.getStockCount() != null) {
            vo.setStockCount(productStockDO.getStockCount().setScale(0, RoundingMode.HALF_UP).longValue());
        }

        return vo;
    }

    // 批量转换方法
    public static List<ProductVO> convertToVOList(List<ProductStockDO> doList) {
        return doList.stream()
                .map(ProductConverter::convertToVO)
                .collect(Collectors.toList());
    }
}