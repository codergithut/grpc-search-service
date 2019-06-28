package grpc.search.oauth.server.s.server.impl;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.fastjson.JSON;
import grpc.search.oauth.server.s.config.ResultCode;
import grpc.search.oauth.server.s.grpc.mdoel.SqlRequest;
import grpc.search.oauth.server.s.model.SearchModeData;
import grpc.search.oauth.server.s.server.DataSearchServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;

/**
 * Created by tianjian on 2019/6/26.
 */
@Service
@ComponentScan
@EnableAsync
public class MysqlDataSearchServer implements DataSearchServer {

    private SqlRequest sqlRequest;

    @Autowired
    DruidDataSource druidDataSource;

    public void setSQLRequest(SqlRequest sqlRequest) {
        this.sqlRequest = sqlRequest;
    }

    @Async("taskExecutor")
    @Override
    public Future<SearchModeData> getDataBySQLSever() throws InterruptedException {
        String sql = sqlRequest.getSql();
        int code = 0;
        List<Map<String, Object>> data = new ArrayList<>();
        PreparedStatement pstmt;
        try {
            pstmt = druidDataSource.getConnection().prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            int col = rs.getMetaData().getColumnCount();
            List<String> cloNames = new ArrayList<String>();
            for(int i = 1; i <=col; i++) {
                cloNames.add(rs.getMetaData().getColumnName(i));
            }
            while (rs.next()) {
                Map<String,Object> detail = new HashMap<>();
                for (int i = 1; i <= col; i++) {
                    detail.put(cloNames.get(i-1), rs.getObject(i));
                }
                data.add(detail);
            }
        } catch (SQLException e) {
            code = ResultCode.SEARCH_DATA_ERROR;
            e.printStackTrace();
        } finally {
            SearchModeData searchModeData = new SearchModeData();
            searchModeData.setCode(code);
            searchModeData.setMessage(JSON.toJSONString(data));
            return new AsyncResult<>(searchModeData);
        }
    }
}
