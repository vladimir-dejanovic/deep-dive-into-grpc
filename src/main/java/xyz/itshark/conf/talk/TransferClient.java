package xyz.itshark.conf.talk;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class TransferClient {

    public static void main(String[] args) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost",
                8080)
                .usePlaintext()
                .build();

        BankGrpc.BankBlockingStub client = BankGrpc.newBlockingStub(channel);

        TransferResponse response = client.transfer(Transfer.newBuilder()
                .setId(1)
                .setAccount(123)
                .setAmount(40)
                .setMessage("here is money")
                .build());

        System.out.println(response);
    }
}
