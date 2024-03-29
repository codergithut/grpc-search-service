package grpc.search.oauth.server.s.server;

/**
 * Created by tianjian on 2019/6/26.
 */
public interface CacheSearchDataServer {

    /**
     * 缓存添加
     * @param key
     * @param value
     */
    void cacheSearchDataServer(String key, String value);

    /**
     * 获取缓存
     * @param key
     * @return
     */
    String getSearchDataServer(String key);

    /**
     * 清除缓存
     */
    void cleanSearchDataServer();

}
