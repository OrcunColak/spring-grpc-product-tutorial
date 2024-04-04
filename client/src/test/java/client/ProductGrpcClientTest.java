package client;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
@Slf4j
class ProductGrpcClientTest {

    @Autowired
    private ProductGrpcClient productGrpcClient;

    @Test
    void getProductById() {
        ProductDto result = productGrpcClient.getProductById(1);
        assertNotNull(result);
        log.info("Product : {}", result);
    }

    @Test
    void getProductByIdUsingManagedChannel() {
        ProductDto result = productGrpcClient.getProductByIdUsingManagedChannel(1);
        assertNull(result);
    }

    @Test
    void getProductByCategoryId() {
        List<ProductDto> result = productGrpcClient.getProductByCategoryId(1);
        assertNotNull(result);
        log.info("Product List : {}", result);
    }
}
