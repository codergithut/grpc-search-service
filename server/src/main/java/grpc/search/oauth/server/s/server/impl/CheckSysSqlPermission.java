package grpc.search.oauth.server.s.server.impl;

import grpc.search.oauth.server.s.model.SqlSearchField;
import grpc.search.oauth.server.s.server.CheckSqlPermission;
import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by tianjian on 2019/7/8.
 */
public class CheckSysSqlPermission implements CheckSqlPermission{

    @Autowired
    private DataSource dataSource;

    private final String SYSCONFIGSEARCH = "SELECT priv_type, priv_value FROM docker.sys_role_data " +
            "where role_id = '?' and target_name = '#';";

    @Override
    public boolean checkSqlPermission(List<SqlSearchField> sqlSearchFields, String role_id) {
        PreparedStatement pstmt;

        for(SqlSearchField sqlSearchField : sqlSearchFields) {
            StringBuffer privValues = new StringBuffer();
            StringBuffer privTypes = new StringBuffer();
            try {
                String table_name = sqlSearchField.getTableName();
                String currentSql = SYSCONFIGSEARCH.replace("?", role_id).replace("#", table_name);
                pstmt = dataSource.getConnection().prepareStatement(currentSql);
                ResultSet rs = pstmt.executeQuery();
                while (rs.next()) {
                    privTypes.append(rs.getString(1));
                    privValues.append(rs.getString(2));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            String v = privValues.toString();

            if(v.indexOf(sqlSearchField.getOpt()) == -1) {
                return false;
            }

        }
        return true;
    }
}
