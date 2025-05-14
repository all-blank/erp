package cn.iocoder.yudao.module.erp.service.analysis;

import cn.iocoder.yudao.module.erp.dal.dataobject.analysis.SaleOrderStatusDO;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface AnalysisSaleService {

    BigDecimal getTotalSaleCount(LocalDateTime startTime, LocalDateTime endTime);

    BigDecimal getTotalSales(LocalDateTime startTime, LocalDateTime endTime);

    SaleOrderStatusDO getSaleOrderStatus(LocalDateTime startTime, LocalDateTime endTime);
}
