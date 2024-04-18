package client.transaction;

import org.springframework.stereotype.Service;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

@Service
public class TransactionService {

    public void getTransactions() throws InterruptedException {
        TransactionServiceGrpc client = new TransactionServiceGrpc("localhost", 9090);
        client.streamTransactions("123456789", 30);

        // Wait to receive all transactions.
        new CountDownLatch(1).await(10, TimeUnit.SECONDS);

        client.shutdown();
    }
}
