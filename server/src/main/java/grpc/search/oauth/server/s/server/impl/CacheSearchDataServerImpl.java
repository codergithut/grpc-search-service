package grpc.search.oauth.server.s.server.impl;

import grpc.search.oauth.server.s.server.CacheSearchDataServer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by tianjian on 2019/6/26.
 */
@Service
@ComponentScan
public class CacheSearchDataServerImpl implements CacheSearchDataServer {

    Map<String,String> cacheData = new HashMap<String, String>();

    @Override
    public void cacheSearchDataServer(String key, String value) {
        cacheData.put(key, value);
    }

    @Override
    public String getSearchDataServer(String key) {

        if(cacheData.containsKey(key)) {
            return cacheData.get(key);
        }

        return null;
    }

    @Override
    public void cleanSearchDataServer() {
        cacheData.clear();
    }

}
