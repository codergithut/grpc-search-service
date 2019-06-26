package grpc.search.service.model;

/**
 * Created by tianjian on 2019/6/26.
 */
public class SearchModeData {

    /**
     * 插叙数据返回码
     */
    private int code;

    /**
     * 查询消息
     */
    private String message;

    /**
     * 是否是缓存返回
     */
    private boolean cache;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
