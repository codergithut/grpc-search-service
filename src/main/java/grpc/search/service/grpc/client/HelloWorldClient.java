package grpc.search.service.grpc.client;

import grpc.search.service.grpc.ServerReply;
import grpc.search.service.grpc.SqlRequest;
import grpc.search.service.grpc.service.GetDataBySqlGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * Created by tianjian on 2019/6/26.
 */
public class HelloWorldClient {

    String token = "eyJraWQiOiIxOGQ0ZDRkNi01MDU2LTRiZDctODJlZS1kNTNlOTAwNTYxZTYiLCJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJyb290Iiwic3ViIjoidGlhbmppYW4iLCJ0aWFuamlhbiI6dHJ1ZSwiaXNzIjoiYXV0aDAiLCJleHAiOjE1NjE5MDI5MDh9.MPDtxPJnbabiRwiOy0bRjOBV_ulLlPNumMkGVO0Sp7o";

    private final ManagedChannel channel;
    private final GetDataBySqlGrpc.GetDataBySqlBlockingStub blockingStub;


    public HelloWorldClient(String host,int port){
        channel = ManagedChannelBuilder.forAddress(host,port)
                .usePlaintext(true)
                .build();
        blockingStub = GetDataBySqlGrpc.newBlockingStub(channel);
    }


    public void shutdown() throws InterruptedException {
        channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
    }

    public  void getDataByServer(String name){
        SqlRequest request = SqlRequest.newBuilder().setSql("select * from user")
                .setToken(token).build();
        ServerReply response = blockingStub.getDataBySql(request);
        System.out.println(response.getMessage());
    }

    public static void main(String[] args) throws InterruptedException {
        HelloWorldClient client = new HelloWorldClient("127.0.0.1",50051);
        for(int i=0;i<5;i++){
            client.getDataByServer("world:"+i);
        }
    }
}
