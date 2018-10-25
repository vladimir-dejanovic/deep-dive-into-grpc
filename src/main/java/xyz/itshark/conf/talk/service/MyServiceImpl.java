package xyz.itshark.conf.talk.service;

import io.grpc.stub.StreamObserver;
import xyz.itshark.conf.talk.BankGrpc;
import xyz.itshark.conf.talk.Transfer;
import xyz.itshark.conf.talk.TransferResponse;

public class MyServiceImpl extends BankGrpc.BankImplBase {

    @Override
    public void transfer(Transfer request,
                         StreamObserver<TransferResponse> responseObserver) {

        responseObserver.onNext(TransferResponse.newBuilder()
            .setId(request.getId())
            .setMessage("all is good for |" + request.getMessage() +"|" )
            .build());

        responseObserver.onCompleted();
    }
}
