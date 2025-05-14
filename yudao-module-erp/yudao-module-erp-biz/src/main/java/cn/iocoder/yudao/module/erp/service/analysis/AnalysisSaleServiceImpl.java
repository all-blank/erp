package cn.iocoder.yudao.module.erp.service.analysis;

import cn.iocoder.yudao.module.erp.dal.dataobject.analysis.SaleOrderStatusDO;
import cn.iocoder.yudao.module.erp.dal.mysql.analysis.AnalysisSaleMapper;
import com.baomidou.dynamic.datasource.annotation.Slave;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @Author: allblank
 * @Description: TODO
 * @Date: 2025-04-13 15:45
 * @Version: 1.0
 */

@Service
@Slave
public class AnalysisSaleServiceImpl implements AnalysisSaleService {

    @Resource
    private AnalysisSaleMapper analysisSaleMapper;

    @Override
    public BigDecimal getTotalSaleCount(LocalDateTime startTime, LocalDateTime endTime) {
        return analysisSaleMapper.getTotalSaleCount(startTime, endTime);
    }

    @Override
    public BigDecimal getTotalSales(LocalDateTime startTime, LocalDateTime endTime) {
        return analysisSaleMapper.getTotalSales(startTime, endTime);
    }

    @Override
    public SaleOrderStatusDO getSaleOrderStatus(LocalDateTime startTime, LocalDateTime endTime) {
        return analysisSaleMapper.getSaleOrderStatus(startTime, endTime);
    }
}
