package grpc.search.discovery.d.server;

import grpc.search.discovery.d.grpc.RegisterReply;
import grpc.search.discovery.d.grpc.RegisterRequest;
import grpc.search.discovery.d.grpc.service.RegisterDiscoveryInfoGrpc;
import io.grpc.stub.StreamObserver;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by tianjian on 2019/6/28.
 */
public class RegisterDiscoveryInfoGrpcImpl extends RegisterDiscoveryInfoGrpc.RegisterDiscoveryInfoImplBase {

    @Autowired
    private DiscoveryInfo discoveryInfo;

    public void registerDiscoverInfo(RegisterRequest request,
                                     StreamObserver<RegisterReply> responseObserver) {
        discoveryInfo.saveOrUpdateInstance(request.getUrl());
        writeResponseMessage(responseObserver, "register ok", 0);

    }

    /**
     * 发送消息到客户端
     * @param responseObserver
     * @param message
     */
    public void writeResponseMessage(StreamObserver<RegisterReply> responseObserver, String message,
                                     int messageCode) {
        //给客户端返回值
        RegisterReply reply = RegisterReply.getDefaultInstance().newBuilder()
                .setMessagecode(1).build();
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }
}
