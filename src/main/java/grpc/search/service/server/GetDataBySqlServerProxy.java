package grpc.search.service.server;

import com.google.common.util.concurrent.RateLimiter;
import grpc.search.service.grpc.ServerReply;
import grpc.search.service.grpc.SqlRequest;
import grpc.search.service.grpc.service.GetDataBySqlGrpc;
import io.grpc.stub.StreamObserver;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.ExecutionException;

/**
 * Created by tianjian on 2019/6/26.
 */
// 流量控制 guava 控制
public class GetDataBySqlServerProxy extends GetDataBySqlGrpc.GetDataBySqlImplBase {

    @Autowired
    private GetDataBySqlServer getDataBySqlServer;

    @Autowired
    private RateLimiter rateLimiter;

    public void getDataBySql(SqlRequest request,
                             StreamObserver<ServerReply> responseObserver) throws ExecutionException, InterruptedException {
        rateLimiter.acquire();
        getDataBySqlServer.getDataBySql(request, responseObserver);
    }




}
