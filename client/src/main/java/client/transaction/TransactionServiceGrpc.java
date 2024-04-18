package client.transaction;

import com.colak.banking.AccountRequest;
import com.colak.banking.TransactionDetailList;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

@Slf4j
public class TransactionServiceGrpc {

    private final ManagedChannel channel;
    private final com.colak.banking.TransactionServiceGrpc.TransactionServiceStub asyncStub;

    public TransactionServiceGrpc(String host, int port) {
        this(ManagedChannelBuilder.forAddress(host, port).usePlaintext().build());
    }

    public TransactionServiceGrpc(ManagedChannel channel) {
        this.channel = channel;
        this.asyncStub = com.colak.banking.TransactionServiceGrpc.newStub(channel);
    }

    public void streamTransactions(String accountNumber, int durationInDays) {
        AccountRequest request = AccountRequest.newBuilder().setAccountNumber(accountNumber)
                .setDurationInDays(durationInDays).build();

        asyncStub.streamTransactions(request, new StreamObserver<>() {
            @Override
            public void onNext(TransactionDetailList transactionDetail) {
                // Handle each incoming TransactionDetail here
                log.info("Received transaction detail: {}", transactionDetail);
            }

            @Override
            public void onError(Throwable throwable) {
                log.error("Error occurred during transaction streaming : ", throwable);
            }

            @Override
            public void onCompleted() {
                log.info("Transaction streaming completed");
            }
        });
    }

    public void shutdown() throws InterruptedException {
        channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
    }
}
