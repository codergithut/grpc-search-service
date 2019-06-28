package grpc.search.oauth.server.s.grpc.client;

import grpc.search.discovery.d.grpc.RegisterReply;
import grpc.search.discovery.d.grpc.RegisterRequest;
import grpc.search.discovery.d.grpc.service.RegisterDiscoveryInfoGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.beans.factory.annotation.Value;

import java.util.concurrent.TimeUnit;

/**
 * Created by tianjian on 2019/6/26.
 */
public class DiscoveryClient {

    private final ManagedChannel channel;
    private final RegisterDiscoveryInfoGrpc.RegisterDiscoveryInfoBlockingStub registerBlockingStub;

    @Value("${server.port}")
    private int port;

    @Value("${server.url}")
    private String url;


    public DiscoveryClient(String host, int port){
        channel = ManagedChannelBuilder.forAddress(host,port)
                .usePlaintext(true)
                .build();
        registerBlockingStub = RegisterDiscoveryInfoGrpc.newBlockingStub(channel);
    }


    public void shutdown() throws InterruptedException {
        channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
    }

    public void registerDiscoveryInfo() {
        RegisterRequest request = RegisterRequest.newBuilder().setUrl(url + ":" + port).build();
        RegisterReply response = registerBlockingStub.registerDiscoverInfo(request);
        System.out.println("service data : " + response.getMessagecode());
    }
}
