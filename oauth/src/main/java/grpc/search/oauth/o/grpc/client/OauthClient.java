package grpc.search.oauth.o.grpc.client;

import grpc.search.oauth.o.grpc.model.OauthReply;
import grpc.search.oauth.o.grpc.model.OauthRequest;
import grpc.search.oauth.o.grpc.service.GetOauthTokenGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.util.concurrent.TimeUnit;

/**
 * Created by tianjian on 2019/6/26.
 */
public class OauthClient {

    private final ManagedChannel channel;
    private final GetOauthTokenGrpc.GetOauthTokenBlockingStub blockingStub;


    public OauthClient(String host, int port){
        channel = ManagedChannelBuilder.forAddress(host,port)
                .usePlaintext(true)
                .build();
        blockingStub = GetOauthTokenGrpc.newBlockingStub(channel);
    }


    public void shutdown() throws InterruptedException {
        channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
    }

    public  void getDataByServer(String name){
        OauthRequest request = OauthRequest.newBuilder().setPassword("haha").setName("tianjian").build();
        OauthReply response = blockingStub.getOauthToken(request);
        System.out.println("service recive data : " + response.getToken());
        System.out.println("message code : " + response.getMessagecode());
    }

    public static void main(String[] args) throws InterruptedException {
        OauthClient client = new OauthClient("127.0.0.1",3002);
        for(int i=0;i<5;i++){
            client.getDataByServer("world:"+i);
        }
    }
}
