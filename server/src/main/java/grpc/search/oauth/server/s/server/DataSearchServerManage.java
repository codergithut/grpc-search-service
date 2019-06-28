package grpc.search.oauth.server.s.server;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by tianjian on 2019/6/26.
 */
public class DataSearchServerManage {

    /**
     * 服务注册
     */
    private static Map<String, DataSearchServer> register =
            new HashMap<String, DataSearchServer>();

    /**
     * 注册服务
     * @param type
     * @param dataSearchServer
     */
    public void registerDataSearchServer(String type, DataSearchServer dataSearchServer) {
        register.put(type, dataSearchServer);
    }

    /**
     * 清除服务
     * @param type
     */
    public void removeDataSearchServer(String type) {
        if(!register.containsKey(type)) {
            return ;
        }
        register.remove(type);
    }

    /**
     * 根据插叙模版插叙数据
     * @param type
     * @return
     */
    public DataSearchServer getDataSearchSeverByType(String type) {
        if(register.containsKey(type)) {
            return register.get(type);
        }
        return null;
    }
}
