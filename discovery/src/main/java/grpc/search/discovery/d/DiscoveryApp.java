package grpc.search.discovery.d;


import grpc.search.discovery.d.config.BeanConfig;
import grpc.search.discovery.d.grpc.server.DiscoveryServer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


/**
 * Created by tianjian on 2019/6/26.
 */
public class DiscoveryApp {
    public static void main(String[] args) throws Exception {
        ApplicationContext ctx =
                new AnnotationConfigApplicationContext(BeanConfig.class);

        DiscoveryServer discoveryServer = ctx.getBean(DiscoveryServer.class);
        discoveryServer.startServer();

    }
}
