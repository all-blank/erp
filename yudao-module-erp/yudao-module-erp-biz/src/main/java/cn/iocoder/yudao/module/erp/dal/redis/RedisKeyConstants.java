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


    String ANALYSIS_KEY_PREFIX = "erp:analysis:";

    /**
     * 权限的缓存
     *
     * KEY 格式：erp:permission:{loginUserId}
     * VALUE 数据格式：权限列表
     */
    String PERMISSION_KEY = "erp:permission";

    /**
     * 产品销量额的缓存
     *
     * KEY 格式：product_sales:{startTime}:{endTime}
     * VALUE 数据格式 String, 模版信息
     */
    String PRODUCT_SALES = "product_sales";

    /**
     * 产品销量额（根据产品分类进行分组）的缓存
     *
     * KEY 格式：product_saleCount_basedCategory:{startTime}:{endTime}
     * VALUE 数据格式 String, 模版信息
     */
    String PRODUCT_SALECOUNT_BASE_DCATEGORY = "product_saleCount_basedCategory";

    /**
     * 总销售数量和销售额的缓存
     *
     * KEY 格式：sale_info:{startTime}:{endTime}
     * VALUE 数据格式 String, 模版信息
     */
    String SALE_INFO = "sale_info";

    /**
     * 产品信息的缓存
     *
     * KEY 格式：product_info_cache:product_info_cache
     * VALUE 数据格式 String, 模版信息
     */
    String PRODUCT_INFO_CACHE = "product_info_cache";


}
