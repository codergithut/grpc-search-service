package grpc.search.oauth.server.s.grpc.client;

import grpc.search.oauth.server.s.grpc.mdoel.SqlRequest;
import grpc.search.oauth.server.s.grpc.service.GetDataBySqlGrpc;
import grpc.search.oauth.server.s.grpc.mdoel.ServerReply;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

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
        SqlRequest request = SqlRequest.newBuilder().setSql("select text, username from test")
                .setToken(token).build();
        ServerReply response = blockingStub.getDataBySql(request);
        System.out.println("service recive data : " + response.getMessage());
        System.out.println("message code : " + response.getMessagecode());
    }

    public void getDataByServer() {

    }

    public static void main(String[] args) throws InterruptedException {
        HelloWorldClient client = new HelloWorldClient("127.0.0.1",3000);
        client.getDataByServer("world:");
    }
}
