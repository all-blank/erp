package cn.iocoder.yudao.module.erp.service.analysis;

import cn.iocoder.yudao.module.erp.controller.admin.analysis.vo.ProductVO;
import cn.iocoder.yudao.module.erp.controller.admin.analysis.vo.WarehouseVO;

import java.util.List;

public interface WarehouseStockService {

    List<WarehouseVO> getWarehouseStock();

}
