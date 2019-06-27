package grpc.search.service.server.impl;

import grpc.search.service.model.ServiceLogModel;
import grpc.search.service.server.ServiceLogServer;
import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by tianjian on 2019/6/27.
 */
public class ServiceLogServerImpl implements ServiceLogServer {

    @Autowired
    private Log log;

    @Override
    public boolean saveServiceLogToService(ServiceLogModel serviceLogModel) {
        log.info(serviceLogModel);
        return true;
    }
}
