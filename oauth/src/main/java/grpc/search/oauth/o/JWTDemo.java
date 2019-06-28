package grpc.search.oauth.o;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Date;
import java.util.UUID;

/**
 * Created by tianjian on 2019/6/26.
 */
public class JWTDemo {
    public static void main(String[] args) {
        Algorithm algorithmHS = Algorithm.HMAC256("secret");

        String token = JWT.create()
                .withIssuer("auth0").withExpiresAt(new Date(new Date().getTime() + 360000000))
                .withAudience("root")
                .withClaim("tianjian", true)
                .withSubject("tianjian")
                .withKeyId(UUID.randomUUID().toString())
                .sign(algorithmHS);
        JWTVerifier verifier = JWT.require(algorithmHS)
                .withIssuer("auth0")
                .build(); //Reusable verifier instance
        DecodedJWT jwt = verifier.verify(token);
        System.out.println(jwt.getToken());
        System.out.println(jwt.getAudience());
        System.out.println(jwt.getSubject());
    }
}
