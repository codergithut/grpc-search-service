package grpc.search.oauth.server.s.server;

import grpc.search.oauth.server.s.grpc.mdoel.SqlRequest;
import grpc.search.oauth.server.s.model.SearchModeData;

import java.util.concurrent.Future;

/**
 * Created by tianjian on 2019/6/26.
 */
public interface DataSearchServer {

    /**
     * 设置sql插叙数据体
     * @param sqlRequest
     */
    void setSQLRequest(SqlRequest sqlRequest);

    /**
     * 异步查询数据库数据
     * @return
     * @throws InterruptedException
     */
    Future<SearchModeData> getDataBySQLSever() throws InterruptedException;
}
