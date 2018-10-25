package xyz.itshark.conf.talk.service;

import io.grpc.stub.StreamObserver;
import xyz.itshark.conf.talk.*;

import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

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

    @Override
    public void notificatoin(Account account,
                             StreamObserver<Notification> responseObserver) {
     for(int i = 0;i<10;i++) {
         responseObserver.onNext(Notification.newBuilder()
                 .setAccount(account)
                 .setAmount(Math.random()*10 * i)
                 .setMessage("notification " +i)
                 .build());
         try {
             Thread.sleep(300);
         } catch (InterruptedException e) {
             e.printStackTrace();
         }
     }
     responseObserver.onCompleted();
    }
}
