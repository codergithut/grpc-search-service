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
public class CheckIDESqlPermission implements CheckSqlPermission {

    @Autowired
    private DataSource dataSource;

    private final String IDESEARCH = "select priv_value from ide as a where (a.tbl_name like '?%' or a.tbl_name = '?.*')" +
            " and a.role_id='#'";

    @Override
    public boolean checkSqlPermission(List<SqlSearchField> sqlSearchFields, String role_id) {
        PreparedStatement pstmt;
        for(SqlSearchField sqlSearchField : sqlSearchFields) {
            StringBuffer privValues = new StringBuffer();
            try {
                String scheme = sqlSearchField.getTableName().split("\\.")[0];
                String currentSql = IDESEARCH.replace("?", scheme).replace("#", role_id);
                pstmt = dataSource.getConnection().prepareStatement(currentSql);
                ResultSet rs = pstmt.executeQuery();
                while (rs.next()) {
                    privValues.append(rs.getString(1));
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
