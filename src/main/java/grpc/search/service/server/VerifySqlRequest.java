package grpc.search.service.server;

import com.auth0.jwt.interfaces.DecodedJWT;
import grpc.search.service.grpc.SqlRequest;

/**
 * Created by tianjian on 2019/6/26.
 */
public interface VerifySqlRequest {

    /**
     * 用户权限验证
     * @param sqlRequest
     * @return
     */
    DecodedJWT verifyTokenPermission(SqlRequest sqlRequest);

    /**
     * 用户数据字段验证
     * @param sqlRequest
     * @param userId
     * @return
     */
    boolean verifySqlPermission(SqlRequest sqlRequest, String userId);
}
