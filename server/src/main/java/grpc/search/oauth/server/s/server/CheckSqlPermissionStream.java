package grpc.search.oauth.server.s.server;

import grpc.search.oauth.server.s.model.SqlSearchField;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by tianjian on 2019/7/8.
 */
public class CheckSqlPermissionStream {

    Map<String, CheckSqlPermission> checkSqlPermissions = new HashMap<>();


    public boolean checkSqlPermissions(List<SqlSearchField> fields, String role_id, String sqlType) {
        if(checkSqlPermissions.containsKey(sqlType)) {
            return checkSqlPermissions.get(sqlType).checkSqlPermission(fields, role_id);
        }
        return false;
    }

    public void registerCheckSqlPermission(String sqlType, CheckSqlPermission checkSqlPermission) {
        checkSqlPermissions.put(sqlType, checkSqlPermission);
    }
}
