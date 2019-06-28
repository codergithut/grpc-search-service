package grpc.search.discovery.d.grpc.server;

import grpc.search.discovery.d.server.GetDiscoverGrpcImpl;
import grpc.search.discovery.d.server.RegisterDiscoveryInfoGrpcImpl;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class DiscoveryServer {

    private int port = 3001;
    private Server server;


    @Autowired
    GetDiscoverGrpcImpl getDiscoverGrpc;

    @Autowired
    RegisterDiscoveryInfoGrpcImpl registerDiscoveryInfoGrpcl;

    public void start() throws IOException, InterruptedException {
        server = ServerBuilder.forPort(port)
                .addService(getDiscoverGrpc)
                .addService(registerDiscoveryInfoGrpcl)
                .build()
                .start();

        System.out.println("server start...");

        Runtime.getRuntime().addShutdownHook(new Thread() {

            @Override
            public void run() {

                System.err.println("*** shutting down gRPC service since JVM is shutting down");
                DiscoveryServer.this.stop();
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
