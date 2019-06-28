package grpc.search.discovery.d.grpc.client;

import grpc.search.discovery.d.grpc.RegisterReply;
import grpc.search.discovery.d.grpc.service.RegisterDiscoveryInfoGrpc;
import grpc.search.discovery.d.grpc.DiscoveryReply;
import grpc.search.discovery.d.grpc.DiscoveryRequest;
import grpc.search.discovery.d.grpc.RegisterRequest;
import grpc.search.discovery.d.grpc.service.GetDiscoveryGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.util.concurrent.TimeUnit;

/**
 * Created by tianjian on 2019/6/26.
 */
public class DiscoveryClient {

    private final ManagedChannel channel;
    private final GetDiscoveryGrpc.GetDiscoveryBlockingStub discoveryBlockingStub;
    private final RegisterDiscoveryInfoGrpc.RegisterDiscoveryInfoBlockingStub registerBlockingStub;


    public DiscoveryClient(String host, int port){
        channel = ManagedChannelBuilder.forAddress(host,port)
                .usePlaintext(true)
                .build();
        discoveryBlockingStub = GetDiscoveryGrpc.newBlockingStub(channel);
        registerBlockingStub = RegisterDiscoveryInfoGrpc.newBlockingStub(channel);
    }


    public void shutdown() throws InterruptedException {
        channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
    }

    public void getDiscoveryByServer(){
        DiscoveryRequest request = DiscoveryRequest.newBuilder()
                .setPassword("test")
                .setUsername("tianjian").build();
        DiscoveryReply response = discoveryBlockingStub.getDiscoveryInfo(request);
        System.out.println("discovery info : " + response.getDiscoveryInfo());
    }

    public void registerDiscoveryInfo(String url) {
        RegisterRequest request = RegisterRequest.newBuilder().setUrl(url).build();
        RegisterReply response = registerBlockingStub.registerDiscoverInfo(request);
        System.out.println("service data : " + response.getMessagecode());
    }

    public static void main(String[] args) throws InterruptedException {
        DiscoveryClient client = new DiscoveryClient("127.0.0.1",3001);
        for(int i=0;i<5;i++){
            client.getDiscoveryByServer();
            //client.registerDiscoveryInfo("127.0.0.1:" + (8080 + i));
        }
    }
}
