package cn.iocoder.yudao.module.erp.service.analysis;

import cn.iocoder.yudao.module.erp.controller.admin.analysis.warehouse.vo.WarehouseVO;

import java.util.List;

public interface AnalysisWarehouseService {

    List<WarehouseVO> getWarehouseStock();

}
