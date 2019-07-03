package grpc.search.oauth.server.s.server.impl;

import com.alibaba.druid.sql.SQLUtils;
import com.alibaba.druid.sql.ast.SQLStatement;
import com.alibaba.druid.sql.ast.statement.SQLCreateViewStatement;
import com.alibaba.druid.sql.ast.statement.SQLSelectGroupByClause;
import com.alibaba.druid.sql.dialect.mysql.visitor.MySqlSchemaStatVisitor;
import com.alibaba.druid.stat.TableStat;
import com.alibaba.druid.util.JdbcConstants;
import com.alibaba.druid.util.StringUtils;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.google.protobuf.MapEntry;
import grpc.search.oauth.server.s.grpc.mdoel.SqlRequest;
import grpc.search.oauth.server.s.model.SqlSearchField;
import grpc.search.oauth.server.s.server.VerifySqlRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by tianjian on 2019/6/26.
 */
@Service
@ComponentScan
public class VerifySqlRequestImpl implements VerifySqlRequest {

    @Autowired
    private Algorithm algorithm;

    @Override
    public DecodedJWT verifyTokenPermission(SqlRequest sqlRequest) {
        String token = sqlRequest.getToken();
        JWTVerifier verifier = JWT.require(algorithm)
                .withIssuer("auth0")
                .build(); //Reusable verifier instance
        return verifier.verify(token);
    }

    @Override
    public boolean verifySqlPermission(SqlRequest sqlRequest, String userId) {
        List<SqlSearchField> fields = parseSql(sqlRequest.getSql());
        if(fields != null) {
            return false;
        }
        return true;
    }

    @Override
    public boolean verifyIpPermission(String ip) {
        //todo 读取配置文件 或数据库数据
        if(!StringUtils.isEmpty(ip)) {
            return true;
        }
        return false;
    }

    public List<SqlSearchField> parseSql(String sql) {
        String dbType = JdbcConstants.MYSQL;
        List<SqlSearchField> datas = new ArrayList<>();

        //格式化输出
        String result = SQLUtils.format(sql, dbType);

        List<SQLStatement> stmtList = SQLUtils.parseStatements(sql, dbType);

        if(CollectionUtils.isEmpty(stmtList)) {
            return null;
        }

        for (int i = 0; i < stmtList.size(); i++) {

            SQLStatement stmt = stmtList.get(i);
            MySqlSchemaStatVisitor visitor = new MySqlSchemaStatVisitor();
            stmt.accept(visitor);
            Map<String, List<TableStat.Column>> filterData =
                    visitor.getColumns().stream()
                            .filter(e -> e.isSelect())
                            .collect(Collectors.groupingBy(e -> e.getTable()));
            for( Map.Entry<String, List<TableStat.Column>> entry : filterData.entrySet()) {
                SqlSearchField sqlSearchField = new SqlSearchField();
                sqlSearchField.setTableName(entry.getKey());
                Set<String> searchColumns = entry.getValue().stream()
                        .map(e -> e.getName()).collect(Collectors.toSet());
                sqlSearchField.setSearchCloumNames(searchColumns);
                datas.add(sqlSearchField);
            }
        }
        return datas;
    }
}
