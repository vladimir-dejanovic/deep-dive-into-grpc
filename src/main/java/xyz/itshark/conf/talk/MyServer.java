package xyz.itshark.conf.talk;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import xyz.itshark.conf.talk.service.MyServiceImpl;

import java.io.IOException;

public class MyServer {

    public static void main(String[] args) throws IOException, InterruptedException {
        Server server = ServerBuilder.forPort(8080)
                .addService(new MyServiceImpl())
                .build();

        server.start();

        server.awaitTermination();
    }
}
