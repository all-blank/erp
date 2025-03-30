package cn.iocoder.yudao.module.erp.dal.mysql.statistics;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * ERP 销售统计 Mapper
 *
 * @author allblank
 */
@Mapper
public interface ErpSaleStatisticsMapper {

    BigDecimal getSalePrice(@Param("beginTime") LocalDateTime beginTime,
                            @Param("endTime") LocalDateTime endTime);

}
