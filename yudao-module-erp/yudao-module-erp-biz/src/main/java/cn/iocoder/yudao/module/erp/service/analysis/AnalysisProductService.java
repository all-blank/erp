package cn.iocoder.yudao.module.erp.service.analysis;

import cn.iocoder.yudao.module.erp.dal.dataobject.analysis.ProductSaleCountDO;
import cn.iocoder.yudao.module.erp.dal.dataobject.analysis.ProductSalesDO;
import cn.iocoder.yudao.module.erp.dal.dataobject.analysis.ProductStockDO;
import cn.iocoder.yudao.module.erp.service.analysis.DTO.ProductInfoDTO;

import java.time.LocalDateTime;
import java.util.List;

public interface AnalysisProductService {

    List<ProductInfoDTO> getProductInfo();

    List<ProductSaleCountDO> getProductSaleCount(LocalDateTime startTime, LocalDateTime endTime);

    List<ProductStockDO> getProductStock();

    List<ProductSalesDO> getProductSales(LocalDateTime startTime, LocalDateTime endTime);


}
