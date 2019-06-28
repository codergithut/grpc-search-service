package grpc.search.oauth.server.s.server.impl;

import grpc.search.oauth.server.s.server.SystemLogServer;
import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by tianjian on 2019/6/27.
 */
public class SystemLogServerImpl implements SystemLogServer {

    @Autowired
    Log log;

    @Override
    public void warn(Object warn) {
        log.warn(warn);
    }

    @Override
    public void info(Object info) {
        log.info(info);
    }

    @Override
    public void error(Object error) {
        log.error(error);
    }
}
