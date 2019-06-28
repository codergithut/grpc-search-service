package grpc.search.oauth.server.s.model;

/**
 * Created by tianjian on 2019/6/26.
 * 客户端请求体携带该数据
 */
public class CacheModel {

    /**
     * 是否可以开启缓存
     */
    private boolean writeCache;

    /**
     * 是否可以读取缓存
     */
    private boolean readCache;


    public boolean isWriteCache() {
        return writeCache;
    }

    public void setWriteCache(boolean writeCache) {
        this.writeCache = writeCache;
    }

    public boolean isReadCache() {
        return readCache;
    }

    public void setReadCache(boolean readCache) {
        this.readCache = readCache;
    }
}
