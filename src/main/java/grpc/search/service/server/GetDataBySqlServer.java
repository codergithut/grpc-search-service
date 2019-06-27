package grpc.search.service.server;

import com.auth0.jwt.interfaces.DecodedJWT;
import grpc.search.service.config.ResultCode;
import grpc.search.service.grpc.ServerReply;
import grpc.search.service.grpc.SqlRequest;
import grpc.search.service.grpc.service.GetDataBySqlGrpc;
import grpc.search.service.model.CacheModel;
import grpc.search.service.model.SearchModeData;
import grpc.search.service.model.ServiceLogModel;
import io.grpc.stub.StreamObserver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.util.Date;
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

    @Value("${timeout.value}")
    private int timeout;

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
     * 业务日志收集服务
     */
    @Autowired
    private ServiceLogServer serviceLogServer;

    /**
     * 系统日志收集服务
     */
    @Autowired
    private SystemLogServer systemLogServer;

    /**
     * 获取数据库数据
     * @param request
     * @param responseObserver
     */
    @Override
    public void getDataBySql(SqlRequest request,
                             StreamObserver<ServerReply> responseObserver) throws InterruptedException, ExecutionException{
        int messageCode = ResultCode.SUCCESS;

        DataSearchServer dataSearchServer = dataSearchServerManage.getDataSearchSeverByType(SQL_TYPE);
        if(dataSearchServer == null) {
            systemLogServer.error("can not found type");
            messageCode = ResultCode.SEARCH_TYPE_UNFOUND;
            writeResponseMessage(responseObserver, "can not foud type", messageCode, request.getSql());
            return ;
        }

        DecodedJWT jwt = null;
        String keyId = null;
        try {
            jwt = verifySqlRequest.verifyTokenPermission(request);
            keyId = jwt.getKeyId();
        } catch (Exception e) {
            messageCode = ResultCode.AUTH_ERROR_CODE;
            systemLogServer.error("Auth fail");
            writeResponseMessage(responseObserver, "verfiy fail", messageCode, request.getSql());
            return ;
        }
        verifySqlRequest.verifySqlPermission(request, keyId);
        dataSearchServer.setSQLRequest(request);
        CacheModel cacheModel = new CacheModel();
        cacheModel.setReadCache(true);
        cacheModel.setWriteCache(true);
        String message = null;
        Future<SearchModeData> searchModeData = null;

        /**
         * 判断是否可以缓存
         */
        if(cacheModel.isReadCache()) {
            message = cacheSearchDataServer.getSearchDataServer(getSqlRequestParamSign(request));
            if(message != null) {
                systemLogServer.info("read data from cache ...");
                writeResponseMessage(responseObserver, message, messageCode, request.getSql());
                return ;
            }
        }


        /**
         * 判断缓存是否命中
         */
        if(message == null) {
            systemLogServer.info("read data from service ...");
            searchModeData = dataSearchServer.getDataBySQLSever();
        }

        try {
            SearchModeData data = searchModeData.get(timeout, TimeUnit.SECONDS);
            message = data.getMessage();
            /**
             * 判断数据是否可以写入缓存
             */
            if(cacheModel.isWriteCache()) {
                systemLogServer.info("write data to cache");
                cacheSearchDataServer.cacheSearchDataServer(getSqlRequestParamSign(request), message);
            }
        } catch (TimeoutException e) {
            systemLogServer.warn("time out error");
            e.printStackTrace();
            message = "time out error";
            messageCode = ResultCode.TIME_OUT_ERROR;
        } finally {
            writeResponseMessage(responseObserver, message, messageCode, request.getSql());
        }
    }

    /**
     * 发送消息到客户端
     * @param responseObserver
     * @param message
     */
    public void writeResponseMessage(StreamObserver<ServerReply> responseObserver,String message,
                                     int messageCode, String sql) {
        //todo 异步插入数据
        ServiceLogModel serviceLogModel = new ServiceLogModel();
        serviceLogModel
                .bulidCode(messageCode)
                .buildSearchTime(new Date().getTime())
                .buildParam(sql);
        serviceLogServer.saveServiceLogToService(serviceLogModel);
        //给客户端返回值
        ServerReply reply = ServerReply.getDefaultInstance().newBuilder()
                .setMessage(message).setMessagecode(messageCode).build();
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
