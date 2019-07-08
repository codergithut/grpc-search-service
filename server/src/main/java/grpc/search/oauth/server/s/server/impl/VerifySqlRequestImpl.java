package grpc.search.oauth.server.s.server.impl;

import com.alibaba.druid.sql.SQLUtils;
import com.alibaba.druid.sql.ast.SQLStatement;
import com.alibaba.druid.sql.dialect.mysql.visitor.MySqlSchemaStatVisitor;
import com.alibaba.druid.stat.TableStat;
import com.alibaba.druid.util.JdbcConstants;
import com.auth0.jwt.algorithms.Algorithm;
import grpc.search.oauth.server.s.grpc.mdoel.SqlRequest;
import grpc.search.oauth.server.s.model.SqlSearchField;
import grpc.search.oauth.server.s.server.CheckSqlPermissionStream;
import grpc.search.oauth.server.s.server.VerifySqlRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by tianjian on 2019/6/26.
 */
@Service
@ComponentScan
public class VerifySqlRequestImpl implements VerifySqlRequest {

    private final String SEARCH = "select value from config_system where type = '" ;

    private final String CLIENTSEARCH = "select secret_key from auth_client where client_name = '";

    @Autowired
    private Algorithm algorithm;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private CheckSqlPermissionStream checkSqlPermissionStream;

    private final Set<String> receiveIps = new HashSet();

    private final Map<String, Set<String>> tokens = new HashMap();

    @Override
    public boolean verifyTokenPermission(SqlRequest sqlRequest) {
        String token = sqlRequest.getToken();
//        JWTVerifier verifier = JWT.require(algorithm)
//                .withIssuer("auth0")
//                .build(); //Reusable verifier instance


        if(!tokens.containsKey(sqlRequest.getName())) {
            Set<String> details = getTokenByClientName(sqlRequest.getName());
            tokens.put(sqlRequest.getName(), details);
        }

        Set<String> details = tokens.get(sqlRequest.getName());

        if(details != null && details.contains(token)) {
            return true;
        }

        return false;
    }

    @Override
    public boolean verifySqlPermission(SqlRequest sqlRequest, String userId) {
        List<SqlSearchField> fields = parseSql(sqlRequest.getSql());
        return checkSqlPermissionStream.checkSqlPermissions(fields, "1", "IDE");
    }

    @Override
    public boolean verifyIpPermission(String ip) {

        if(CollectionUtils.isEmpty(receiveIps)) {
            receiveIps.addAll(initVerifyData("1"));
        }

        if(receiveIps.contains(ip)) {
            return true;
        }

        return false;
    }

    private Set<String> initVerifyData(String type) {
        Set<String> data = new HashSet<String>();
        PreparedStatement pstmt;
        try {
            pstmt = dataSource.getConnection().prepareStatement(SEARCH + type + "'");
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                data.add(rs.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }

    private Set<String> getTokenByClientName(String clientName) {
        PreparedStatement pstmt;
        Set<String> data = new HashSet<>();
        try {
            pstmt = dataSource.getConnection().prepareStatement(CLIENTSEARCH + clientName + "'");
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                data.add(rs.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
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
                sqlSearchField.setOpt("select");
                datas.add(sqlSearchField);
            }
        }
        return datas;
    }
}
