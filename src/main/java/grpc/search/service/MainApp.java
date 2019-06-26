package grpc.search.service;

import grpc.search.service.config.BeanConfig;
import grpc.search.service.grpc.server.HelloWorldServer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


/**
 * Created by tianjian on 2019/6/26.
 */
public class MainApp {
    public static void main(String[] args) throws Exception {
        ApplicationContext ctx =
                new AnnotationConfigApplicationContext(BeanConfig.class);

        HelloWorldServer helloWorldServer = ctx.getBean(HelloWorldServer.class);
        helloWorldServer.startServer();

    }
}
