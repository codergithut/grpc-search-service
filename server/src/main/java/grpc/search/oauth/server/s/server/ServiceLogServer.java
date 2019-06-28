package grpc.search.oauth.server.s.server;

import grpc.search.oauth.server.s.model.ServiceLogModel;

/**
 * Created by tianjian on 2019/6/27.
 */
public interface ServiceLogServer {

    boolean saveServiceLogToService(ServiceLogModel serviceLogModel);

}
