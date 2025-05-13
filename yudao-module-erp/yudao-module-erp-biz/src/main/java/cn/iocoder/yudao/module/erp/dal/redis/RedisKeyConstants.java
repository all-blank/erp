package cn.iocoder.yudao.module.erp.dal.redis;

/**
 * ERP Redis Key 枚举类
 *
 * @author allblank
 */
public interface RedisKeyConstants {

    /**
     * 序号的缓存
     *
     * KEY 格式：trade_no:{prefix}
     * VALUE 数据格式：编号自增
     */
    String NO = "erp:seq_no:";

    /**
     * 产品销量额的缓存
     *
     * KEY 格式：product_sales_template:{startTime}:{endTime}
     * VALUE 数据格式 String, 模版信息
     */
    String PRODUCT_SALES = "product_sales";

}
