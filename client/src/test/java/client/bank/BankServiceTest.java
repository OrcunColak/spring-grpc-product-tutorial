package client.bank;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BankServiceTest {

    @Autowired
    private BankService service;
    @Test
    void uploadAddressProof() {
        service.uploadAddressProof();
    }
}