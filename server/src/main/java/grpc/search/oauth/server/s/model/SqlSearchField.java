package grpc.search.oauth.server.s.model;

import com.alibaba.fastjson.JSON;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by tianjian on 2019/7/1.
 */
public class SqlSearchField {

    /**
     * 表明
     */
    private String tableName;

    /**
     * 需要查询的列名
     */
    private Set<String> searchCloumNames = new HashSet<>();

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public Set<String> getSearchCloumNames() {
        return searchCloumNames;
    }

    public void setSearchCloumNames(Set<String> searchCloumNames) {
        this.searchCloumNames = searchCloumNames;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
