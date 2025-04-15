package cn.iocoder.yudao.module.erp.dal.mysql.analysis;


import cn.iocoder.yudao.module.erp.dal.dataobject.analysis.SaleOrderStatusDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.Date;

@Mapper
public interface AnalysisSaleMapper {


    BigDecimal getTotalSaleCount(
            @Param("startTime") Date startTime,
            @Param("endTime") Date endTime
    );

    BigDecimal getTotalSales(
            @Param("startTime") Date startTime,
            @Param("endTime") Date endTime
    );

    SaleOrderStatusDO getSaleOrderStatus(
            @Param("startTime") Date startTime,
            @Param("endTime") Date endTime
    );
}
