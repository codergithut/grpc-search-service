package grpc.search.client.common;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import grpc.search.discovery.d.grpc.DiscoveryReply;
import grpc.search.discovery.d.grpc.DiscoveryRequest;
import grpc.search.discovery.d.grpc.service.GetDiscoveryGrpc;
import grpc.search.oauth.o.grpc.model.OauthReply;
import grpc.search.oauth.o.grpc.model.OauthRequest;
import grpc.search.oauth.o.grpc.service.GetOauthTokenGrpc;
import grpc.search.oauth.server.s.grpc.mdoel.ServerReply;
import grpc.search.oauth.server.s.grpc.mdoel.SqlRequest;
import grpc.search.oauth.server.s.grpc.service.GetDataBySqlGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.util.concurrent.TimeUnit;

/**
 * Created by tianjian on 2019/6/28.
 */
public class SearchDataClient {

    private final Integer discoveryPort = 3001;
    private final Integer oauthPort = 3002;
    private final String url = "127.0.0.1";

    private final ManagedChannel discovery_channel;

    private final ManagedChannel oauth_channel;

    private ManagedChannel server_channel;

    private final GetDiscoveryGrpc.GetDiscoveryBlockingStub discoveryBlockingStub;
    private GetDataBySqlGrpc.GetDataBySqlBlockingStub serverBlockingStub;
    private GetOauthTokenGrpc.GetOauthTokenBlockingStub oauthBlockingStub;


    public SearchDataClient(){
        discovery_channel = ManagedChannelBuilder.forAddress(url, discoveryPort)
                .usePlaintext(true)
                .build();
        oauth_channel = ManagedChannelBuilder.forAddress(url, oauthPort)
                .usePlaintext(true)
                .build();
        discoveryBlockingStub = GetDiscoveryGrpc.newBlockingStub(discovery_channel);

        oauthBlockingStub = GetOauthTokenGrpc.newBlockingStub(oauth_channel);
    }


    public void shutdown() throws InterruptedException {
        discovery_channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
        server_channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
    }

    private String getDiscoveryByServer(){
        DiscoveryRequest request = DiscoveryRequest.newBuilder()
                .setPassword("test")
                .setUsername("tianjian").build();
        DiscoveryReply response = discoveryBlockingStub.getDiscoveryInfo(request);
        System.out.println(response.getDiscoveryInfo());
        JSONArray jsonArray = JSON.parseArray(response.getDiscoveryInfo());
        JSONObject o = (JSONObject)jsonArray.get(0);
        return o.get("url").toString();
    }

    private String getTokenByUserNameAndPassWord(String userName, String passWord) {
        OauthRequest request = OauthRequest.newBuilder().setPassword(passWord).setName(userName).build();
        OauthReply response = oauthBlockingStub.getOauthToken(request);
        return response.getToken();

    }

    private String getDataByServer(String userName, String passWord, String sql) {
        String token = getTokenByUserNameAndPassWord(userName, passWord);
        String discover_url = getDiscoveryByServer();
        if(server_channel == null) {
            String serverUrl = discover_url.split(":")[0];
            Integer serverPort = Integer.valueOf(discover_url.split(":")[1]);
            server_channel = ManagedChannelBuilder.forAddress(serverUrl, serverPort)
                    .usePlaintext(true)
                    .build();
            serverBlockingStub = GetDataBySqlGrpc.newBlockingStub(server_channel);
        }
        SqlRequest request = SqlRequest.newBuilder().setSql(sql)
                .setToken(token).build();
        ServerReply response = serverBlockingStub.getDataBySql(request);
        return response.getMessage();
    }

    private String getDataByServer(String sql) {
        String token = "5550ea50-f129-4ce0-80e0-3a0d7dadf8b3";
        if(server_channel == null) {
            server_channel = ManagedChannelBuilder.forAddress("127.0.0.1", 3000)
                    .usePlaintext(true)
                    .build();
            serverBlockingStub = GetDataBySqlGrpc.newBlockingStub(server_channel);
        }
        SqlRequest request = SqlRequest.newBuilder().setSql(sql)
                .setToken(token).setName("client1").build();
        ServerReply response = serverBlockingStub.getDataBySql(request);
        return response.getMessage();
    }

    public static void main(String[] args) throws InterruptedException {
        SearchDataClient searchDataClient = new SearchDataClient();
        System.out.println(searchDataClient.getDataByServer("select text, username from docker.test"));

    }



}
