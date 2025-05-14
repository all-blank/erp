package cn.iocoder.yudao.module.erp.dal.mysql.analysis;

import cn.iocoder.yudao.module.erp.dal.dataobject.analysis.ProductSaleCountDO;
import cn.iocoder.yudao.module.erp.dal.dataobject.analysis.ProductSalesDO;
import cn.iocoder.yudao.module.erp.dal.dataobject.analysis.ProductStockDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;
import java.util.List;


@Mapper
public interface AnalysisProductMapper {


    List<ProductSaleCountDO> getProductSaleCount(
            @Param("startTime") LocalDateTime startTime,
            @Param("endTime") LocalDateTime endTime
    );

    List<ProductStockDO> getProductStock();

    List<ProductSalesDO> getProductSales(
            @Param("startTime") LocalDateTime startTime,
            @Param("endTime") LocalDateTime endTime
    );


}
