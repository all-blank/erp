package cn.iocoder.yudao.module.erp.dal.mysql.analysis;


import cn.iocoder.yudao.module.erp.dal.dataobject.analysis.WarehouseStockDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AnalysisWarehouseMapper {

    List<WarehouseStockDO> getWarehouseStock();
}
