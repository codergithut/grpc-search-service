package grpc.search.service.server.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import grpc.search.service.grpc.SqlRequest;
import grpc.search.service.server.VerifySqlRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

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
        System.out.println("TOKEN" + sqlRequest.getToken());
        String token = sqlRequest.getToken();
        JWTVerifier verifier = JWT.require(algorithm)
                .withIssuer("auth0")
                .build(); //Reusable verifier instance
        return verifier.verify(token);



    }

    @Override
    public boolean verifySqlPermission(SqlRequest sqlRequest, String userId) {
        return true;
    }
}