package grpc.search.service.config;

/**
 * Created by tianjian on 2019/6/27.
 */
public class ResultCode {

    /**
     * 查询成功
     */
    public static final int SUCCESS = 0;

    /**
     * 用户权限错误
     */
    public static final int AUTH_ERROR_CODE = 1;

    /**
     * 查询数据数据错误
     */
    public static final int SEARCH_DATA_ERROR = 2;

    /**
     * 字段权限错误
     */
    public static final int AUTH_FIELD_CODE = 3;

    /**
     * 未发现可用的查询工具
     */
    public static final int SEARCH_TYPE_UNFOUND = 4;

    public static final int TIME_OUT_ERROR = 5;
}
