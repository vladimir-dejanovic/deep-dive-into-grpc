package xyz.itshark.conf.talk;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

public class StreamClient {

    public static Boolean shouldRun = true;

    public static void main(String[] args) throws InterruptedException {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost",
                8080)
                .usePlaintext()
                .build();

        BankGrpc.BankStub client = BankGrpc.newStub(channel);

        StreamObserver<BankTransfer> sending = client.bank2bank(new StreamObserver<BankTransferResponse>() {
            @Override
            public void onNext(BankTransferResponse bankTransferResponse) {
                System.out.println(bankTransferResponse);
            }

            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onCompleted() {
                shouldRun = false;
            }
        });

        for(int i=0;i<10;i++) {
            sending.onNext(BankTransfer.newBuilder()
                    .setTransId(i)
                    .setFromAccount(Account.newBuilder().setAccount(34).build())
                    .setFromAccount(Account.newBuilder().setAccount(24).build())
                    .setMessage("trans " + i)
                    .setAmount(Math.random() * i * 14)
                    .build());
        }

        sending.onCompleted();

        while(shouldRun) {
            Thread.sleep(500);
        }

    }
}
