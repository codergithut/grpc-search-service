package grpc.search.service.server.impl;

import grpc.search.service.server.CacheSearchDataServer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

/**
 * Created by tianjian on 2019/6/26.
 */
@Service
@ComponentScan
public class CacheSearchDataServerImpl implements CacheSearchDataServer {

    @Override
    public void cacheSearchDataServer(String key, String value) {

    }

    @Override
    public String getSearchDataServer(String key) {
        return null;
    }

    @Override
    public void cleanSearchDataServer() {

    }

    @Override
    public void updateCacheSearchData(String key, String value) {

    }
}
