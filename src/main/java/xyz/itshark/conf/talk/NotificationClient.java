package xyz.itshark.conf.talk;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

public class NotificationClient {

    private static Boolean shouldRun = true;

    public static void main(String[] args) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost",
                8080)
                .usePlaintext()
                .build();

        BankGrpc.BankStub client = BankGrpc.newStub(channel);

        client.notificatoin(Account.newBuilder()
                        .setAccount(1234).build(),
                new StreamObserver<Notification>() {
                    @Override
                    public void onNext(Notification notification) {
                        System.out.println(notification);
                    }

                    @Override
                    public void onError(Throwable throwable) {

                    }

                    @Override
                    public void onCompleted() {
                        shouldRun = false;
                    }
                });

        while(shouldRun) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
