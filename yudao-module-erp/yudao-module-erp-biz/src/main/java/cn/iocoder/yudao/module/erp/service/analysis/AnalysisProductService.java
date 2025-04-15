package cn.iocoder.yudao.module.erp.service.analysis;

import cn.iocoder.yudao.module.erp.dal.dataobject.analysis.ProductSaleCountDO;
import cn.iocoder.yudao.module.erp.dal.dataobject.analysis.ProductSalesDO;
import cn.iocoder.yudao.module.erp.dal.dataobject.analysis.ProductStockDO;
import cn.iocoder.yudao.module.erp.service.analysis.DTO.ProductInfoDTO;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public interface AnalysisProductService {

    List<ProductInfoDTO> getProductInfo();

    List<ProductSaleCountDO> getProductSaleCount(Date startTime, Date endTime);

    List<ProductStockDO> getProductStock();

    List<ProductSalesDO> getProductSales(Date startTime, Date endTime);


}
