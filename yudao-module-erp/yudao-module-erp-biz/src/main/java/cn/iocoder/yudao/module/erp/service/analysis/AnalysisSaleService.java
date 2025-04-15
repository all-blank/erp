package cn.iocoder.yudao.module.erp.service.analysis;

import cn.iocoder.yudao.module.erp.dal.dataobject.analysis.SaleOrderStatusDO;

import java.math.BigDecimal;
import java.util.Date;

public interface AnalysisSaleService {

    BigDecimal getTotalSaleCount(Date startTime, Date endTime);

    BigDecimal getTotalSales(Date startTime, Date endTime);

    SaleOrderStatusDO getSaleOrderStatus(Date startTime, Date endTime);
}
