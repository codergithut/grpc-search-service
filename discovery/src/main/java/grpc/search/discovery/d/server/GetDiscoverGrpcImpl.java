package grpc.search.discovery.d.server;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSON;
import grpc.search.discovery.d.grpc.DiscoveryReply;
import grpc.search.discovery.d.grpc.DiscoveryRequest;
import grpc.search.discovery.d.grpc.service.GetDiscoveryGrpc;
import grpc.search.discovery.d.model.InstanceInfo;
import io.grpc.stub.StreamObserver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * Created by tianjian on 2019/6/28.
 */
public class GetDiscoverGrpcImpl extends GetDiscoveryGrpc.GetDiscoveryImplBase {

    @Autowired
    private DiscoveryInfo discoveryInfo;


    public void getDiscoveryInfo(DiscoveryRequest request,
                                 StreamObserver<DiscoveryReply> responseObserver) {
        List<InstanceInfo> instanceInfoList = discoveryInfo.getInstanceInfo();
        String message = null;
        if(!CollectionUtils.isEmpty(instanceInfoList)) {
            message = JSON.toJSONString(instanceInfoList);
        }
        if(StringUtils.isEmpty(message)) {
            writeResponseMessage(responseObserver, "", 1);
        } else {
            writeResponseMessage(responseObserver, message, 0);
        }
    }


    /**
     * 发送消息到客户端
     * @param responseObserver
     * @param message
     */
    public void writeResponseMessage(StreamObserver<DiscoveryReply> responseObserver, String message,
                                     int messageCode) {
        //给客户端返回值
        DiscoveryReply reply = DiscoveryReply.getDefaultInstance().newBuilder()
                .setMessagecode(messageCode)
                .setDiscoveryInfo(message).build();
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }
}
