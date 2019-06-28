package grpc.search.oauth.o.server;

import com.alibaba.druid.pool.DruidDataSource;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import grpc.search.oauth.o.grpc.model.OauthReply;
import grpc.search.oauth.o.grpc.model.OauthRequest;
import grpc.search.oauth.o.grpc.service.GetOauthTokenGrpc;
import io.grpc.stub.StreamObserver;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.UUID;

/**
 * Created by tianjian on 2019/6/28.
 */
public class GetOauthTokenGrpcImpl extends GetOauthTokenGrpc.GetOauthTokenImplBase {

    @Autowired
    DruidDataSource druidDataSource;

    @Autowired
    Algorithm algorithm;


    public void getOauthToken(OauthRequest request,
                              io.grpc.stub.StreamObserver<OauthReply> responseObserver) {
        String token = JWT.create()
                .withIssuer("auth0").withExpiresAt(new Date(new Date().getTime() + 360000000))
                .withAudience("root")
                .withClaim("tianjian", true)
                .withSubject("tianjian")
                .withKeyId(UUID.randomUUID().toString())
                .sign(algorithm);
        writeResponseMessage(responseObserver, token, 0);

    }

    /**
     * 发送消息到客户端
     * @param responseObserver
     * @param messageCode
     */
    public void writeResponseMessage(StreamObserver<OauthReply> responseObserver, String token,
                                     int messageCode) {
        //给客户端返回值
        OauthReply reply = OauthReply.getDefaultInstance().newBuilder()
                .setMessagecode(messageCode).setToken(token).build();
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }


}
