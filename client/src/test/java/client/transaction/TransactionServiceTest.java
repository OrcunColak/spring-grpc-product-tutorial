package client.transaction;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TransactionServiceTest {

    @Autowired
    private TransactionService service;


    @Test
    void getTransactions() throws InterruptedException {
        service.getTransactions();
    }
}