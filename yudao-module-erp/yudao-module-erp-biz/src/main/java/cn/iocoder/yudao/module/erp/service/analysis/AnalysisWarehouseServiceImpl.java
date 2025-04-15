package cn.iocoder.yudao.module.erp.service.analysis;

import cn.iocoder.yudao.module.erp.controller.admin.analysis.product.vo.ProductVO;
import cn.iocoder.yudao.module.erp.controller.admin.analysis.warehouse.vo.WarehouseVO;
import cn.iocoder.yudao.module.erp.dal.dataobject.analysis.WarehouseStockDO;
import cn.iocoder.yudao.module.erp.dal.mysql.analysis.AnalysisWarehouseMapper;
import com.baomidou.dynamic.datasource.annotation.Slave;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Author: allblank
 * @Description: TODO
 * @Date: 2025-04-06 16:43
 * @Version: 1.0
 */

@Service
@Slave
public class AnalysisWarehouseServiceImpl implements AnalysisWarehouseService {

    @Resource
    private AnalysisWarehouseMapper analysisWarehouseMapper;

    @Override
    public List<WarehouseVO> getWarehouseStock() {
        List<WarehouseStockDO> warehouseStockDOList =  analysisWarehouseMapper.getWarehouseStock();
        // 按 warehouseId 分组
        Map<Long, List<WarehouseStockDO>> warehouseGroups = warehouseStockDOList.stream()
                .collect(Collectors.groupingBy(WarehouseStockDO::getWarehouseId));

        // 构建 WarehouseVO 列表
        List<WarehouseVO> warehouseVOList = new ArrayList<>();

        for (Map.Entry<Long, List<WarehouseStockDO>> entry : warehouseGroups.entrySet()) {
            List<WarehouseStockDO> group = entry.getValue();
            if (group.isEmpty()) continue;

            WarehouseVO warehouseVO = new WarehouseVO();
            warehouseVO.setWarehouseId(group.get(0).getWarehouseId());
            warehouseVO.setWarehouseName(group.get(0).getWarehouseName());

            List<ProductVO> productList = group.stream()
                    .map(item -> {
                        ProductVO product = new ProductVO();
                        product.setProductId(item.getProductId());
                        product.setProductName(item.getProductName());
                        product.setStockCount(item.getStock());
                        product.setUnitId(item.getUnitId());
                        product.setUnitName(item.getUnitName());
                        return product;
                    })
                    .collect(Collectors.toList());

            warehouseVO.setProducts(productList);
            warehouseVOList.add(warehouseVO);
        }

        return warehouseVOList;
    }

}
