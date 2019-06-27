package grpc.search.service.server;

import grpc.search.service.model.ServiceLogModel;

/**
 * Created by tianjian on 2019/6/27.
 */
public interface ServiceLogServer {

    boolean saveServiceLogToService(ServiceLogModel serviceLogModel);

}
