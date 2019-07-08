package grpc.search.oauth.server.s.grpc.server;

import grpc.search.oauth.server.s.server.VerifySqlRequest;
import io.grpc.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tianjian on 2019/7/4.
 */
public class MyServerInterceptor implements ServerInterceptor {

    private static final ServerCall.Listener NOOP_LISTENER = new ServerCall.Listener() {
    };
    /**
     * 校验请求数据合法性
     */
    @Autowired
    private VerifySqlRequest verifySqlRequest;

    @Autowired
    private DataSource dataSource;

    @Override
    public <ReqT, RespT> ServerCall.Listener<ReqT> interceptCall(ServerCall<ReqT, RespT> call, Metadata headers, ServerCallHandler<ReqT, RespT> next) {
        String inetSocketString = call.getAttributes().get(Grpc.TRANSPORT_ATTR_REMOTE_ADDR).toString();
        if(!verifySqlRequest.verifyIpPermission(inetSocketString.split(":")[0].substring(1))){
            call.close(Status.UNAUTHENTICATED.withDescription("Ip refused"), headers);
            return NOOP_LISTENER;
        }
        return next.startCall(call, headers);
    }
}
