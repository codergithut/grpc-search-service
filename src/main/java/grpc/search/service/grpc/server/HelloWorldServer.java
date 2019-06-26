package grpc.search.service.grpc.server;

import grpc.search.service.server.GetDataBySqlServerProxy;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class HelloWorldServer {

    private int port = 50051;
    private Server server;

    @Autowired
    private GetDataBySqlServerProxy getDataBySqlServerProxy;

    public void start() throws IOException, InterruptedException {
        server = ServerBuilder.forPort(port)
                .addService(getDataBySqlServerProxy)
                .build()
                .start();

        System.out.println("server start...");

        Runtime.getRuntime().addShutdownHook(new Thread() {

            @Override
            public void run() {

                System.err.println("*** shutting down gRPC service since JVM is shutting down");
                HelloWorldServer.this.stop();
                System.err.println("*** service shut down");
            }
        });
    }

    private void stop() {
        if (server != null) {
            server.shutdown();
        }
    }

    // block 一直到退出程序
    public void blockUntilShutdown() throws InterruptedException {
        if (server != null) {
            server.awaitTermination();
        }
    }


    public void startServer() throws IOException, InterruptedException {
        start();
        blockUntilShutdown();
    }
}
