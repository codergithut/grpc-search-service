package grpc.search.oauth.o.server;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.fastjson.JSON;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import grpc.search.oauth.o.grpc.model.OauthReply;
import grpc.search.oauth.o.grpc.model.OauthRequest;
import grpc.search.oauth.o.grpc.service.GetOauthTokenGrpc;
import io.grpc.stub.StreamObserver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.AsyncResult;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

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
        if(checkUserInfo(request.getName(), request.getPassword())) {
            String token = JWT.create()
                    .withIssuer("auth0").withExpiresAt(new Date(new Date().getTime() + 360000000))
                    .withAudience("root")
                    .withClaim("tianjian", true)
                    .withSubject("tianjian")
                    .withKeyId(UUID.randomUUID().toString())
                    .sign(algorithm);
            writeResponseMessage(responseObserver, token, 0);
        } else {
            writeResponseMessage(responseObserver, "user auth fail", 1);
        }



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

    private boolean checkUserInfo(String userName, String passWord) {
        StringBuffer sql = new StringBuffer();
        sql.append("select * from docker.test where username = '" + userName + "'");
        List<Map<String, Object>> data = new ArrayList<>();
        PreparedStatement pstmt;
        try {
            pstmt = druidDataSource.getConnection().prepareStatement(sql.toString());
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


}
