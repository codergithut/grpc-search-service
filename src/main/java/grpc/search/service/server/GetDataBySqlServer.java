package grpc.search.service.server;

import com.auth0.jwt.interfaces.DecodedJWT;
import grpc.search.service.grpc.ServerReply;
import grpc.search.service.grpc.SqlRequest;
import grpc.search.service.grpc.service.GetDataBySqlGrpc;
import grpc.search.service.model.CacheModel;
import grpc.search.service.model.SearchModeData;
import io.grpc.stub.StreamObserver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * Created by tianjian on 2019/6/26.
 */
public class GetDataBySqlServer
        extends GetDataBySqlGrpc.GetDataBySqlImplBase {

    private static final String SQL_TYPE = "mysql";

    /**
     * 底层数据获取
     */
    @Autowired
    private DataSearchServerManage dataSearchServerManage;

    /**
     * 校验请求数据合法性
     */
    @Autowired
    private VerifySqlRequest verifySqlRequest;

    /**
     * 数据缓存服务
     */
    @Autowired
    private CacheSearchDataServer cacheSearchDataServer;

    /**
     * 获取数据库数据
     * @param request
     * @param responseObserver
     */
    @Override
    public void getDataBySql(SqlRequest request,
                             StreamObserver<ServerReply> responseObserver) throws InterruptedException, ExecutionException{
        DataSearchServer dataSearchServer = dataSearchServerManage.getDataSearchSeverByType(SQL_TYPE);
        DecodedJWT jwt = verifySqlRequest.verifyTokenPermission(request);
        String keyId = jwt.getKeyId();
        System.out.println("keyId:" + keyId);
        verifySqlRequest.verifySqlPermission(request, keyId);
        dataSearchServer.setSQLRequest(request);
        CacheModel cacheModel = new CacheModel();
        String message = null;
        Future<SearchModeData> searchModeData = null;

        /**
         * 判断是否可以缓存
         */
        if(cacheModel.isReadCache()) {
            message = cacheSearchDataServer.getSearchDataServer(getSqlRequestParamSign(request));
            writeResponseMessage(responseObserver, message);
            return ;
        }


        /**
         * 判断缓存是否命中
         */
        if(message == null) {
            searchModeData = dataSearchServer.getDataBySQLSever();
        }

        /**
         * 判断数据是否可以写入缓存
         */
        if(cacheModel.isWriteCache()) {
            cacheSearchDataServer.cacheSearchDataServer(getSqlRequestParamSign(request), searchModeData.get().getMessage());
        }

        try {
            SearchModeData data = searchModeData.get(1, TimeUnit.SECONDS);
            message = data.getMessage();
        } catch (TimeoutException e) {
            e.printStackTrace();
            message = "error";
        } finally {
            writeResponseMessage(responseObserver, message);
        }
    }

    /**
     * 发送消息到客户端
     * @param responseObserver
     * @param message
     */
    public void writeResponseMessage(StreamObserver<ServerReply> responseObserver,String message) {
        ServerReply reply = ServerReply.getDefaultInstance().newBuilder().setMessage(message).build();
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }

    /**
     * 为请求数据进行签名
     * @param sqlRequest
     * @return
     */
    public static String getSqlRequestParamSign(SqlRequest sqlRequest) {
        StringBuffer key = new StringBuffer();
        key.append(sqlRequest.getSql()).append(sqlRequest.getName());
        return key.toString();

    }
}
