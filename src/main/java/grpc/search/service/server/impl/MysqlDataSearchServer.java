package grpc.search.service.server.impl;

import grpc.search.service.grpc.SqlRequest;
import grpc.search.service.model.SearchModeData;
import grpc.search.service.server.DataSearchServer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

import java.util.concurrent.Future;

/**
 * Created by tianjian on 2019/6/26.
 */
@Service
@ComponentScan
@EnableAsync
public class MysqlDataSearchServer implements DataSearchServer {

    private SqlRequest sqlRequest;

    public void setSQLRequest(SqlRequest sqlRequest) {
        this.sqlRequest = sqlRequest;
    }

    @Async("taskExecutor")
    @Override
    public Future<SearchModeData> getDataBySQLSever() throws InterruptedException {
        //todo 获取mysql数据库数据
//        String sql = sqlRequest.getSql();
        SearchModeData searchModeData = new SearchModeData();
        searchModeData.setCode(11);
        searchModeData.setMessage("haha");
        return new AsyncResult<>(searchModeData);
    }
}
