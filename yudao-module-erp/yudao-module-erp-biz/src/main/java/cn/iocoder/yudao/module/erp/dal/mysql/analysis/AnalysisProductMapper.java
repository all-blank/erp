package cn.iocoder.yudao.module.erp.dal.mysql.analysis;

import cn.iocoder.yudao.module.erp.dal.dataobject.analysis.ProductSaleCountDO;
import cn.iocoder.yudao.module.erp.dal.dataobject.analysis.ProductSalesDO;
import cn.iocoder.yudao.module.erp.dal.dataobject.analysis.ProductStockDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


@Mapper
public interface AnalysisProductMapper {


    List<ProductSaleCountDO> getProductSaleCount(
            @Param("startTime") Date startTime,
            @Param("endTime") Date endTime
    );

    List<ProductStockDO> getProductStock();

    List<ProductSalesDO> getProductSales(
            @Param("startTime") Date startTime,
            @Param("endTime") Date endTime
    );


}
