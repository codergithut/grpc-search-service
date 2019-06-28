package grpc.search.oauth.o;

import grpc.search.oauth.o.config.BeanConfig;
import grpc.search.oauth.o.grpc.server.OauthServer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by tianjian on 2019/6/28.
 */
public class OauthApp {
    public static void main(String[] args) throws Exception {
        ApplicationContext ctx =
                new AnnotationConfigApplicationContext(BeanConfig.class);
        OauthServer discoveryServer = ctx.getBean(OauthServer.class);
        discoveryServer.startServer();

    }
}
