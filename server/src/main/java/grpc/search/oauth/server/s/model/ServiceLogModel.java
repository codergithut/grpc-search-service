package grpc.search.oauth.server.s.model;

import com.alibaba.fastjson.JSON;

/**
 * Created by tianjian on 2019/6/27.
 */
public class ServiceLogModel {

    /**
     * 返回码
     */
    private int code;

    /**
     * 查询时间
     */
    private Long searchTime;

    /**
     * 查询参数
     */
    private String param;

    //private String result;

    public int getCode() {
        return code;
    }

    public ServiceLogModel bulidCode(int code) {
        this.code = code;
        return this;
    }

    public Long getSearchTime() {
        return searchTime;
    }

    public ServiceLogModel buildSearchTime(Long searchTime) {
        this.searchTime = searchTime;
        return this;
    }

    public String getParam() {
        return param;
    }

    public ServiceLogModel buildParam(String param) {
        this.param = param;
        return this;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
