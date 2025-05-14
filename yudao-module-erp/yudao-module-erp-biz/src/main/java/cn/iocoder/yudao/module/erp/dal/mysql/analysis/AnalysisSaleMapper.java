package cn.iocoder.yudao.module.erp.dal.mysql.analysis;


import cn.iocoder.yudao.module.erp.dal.dataobject.analysis.SaleOrderStatusDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Mapper
public interface AnalysisSaleMapper {


    BigDecimal getTotalSaleCount(
            @Param("startTime") LocalDateTime startTime,
            @Param("endTime") LocalDateTime endTime
    );

    BigDecimal getTotalSales(
            @Param("startTime") LocalDateTime startTime,
            @Param("endTime") LocalDateTime endTime
    );

    SaleOrderStatusDO getSaleOrderStatus(
            @Param("startTime") LocalDateTime startTime,
            @Param("endTime") LocalDateTime endTime
    );
}
