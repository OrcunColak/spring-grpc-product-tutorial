package client.product;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
@Slf4j
class ProductServiceTest {

    @Autowired
    private ProductService productService;

    @Test
    void getProductById() {
        ProductDto result = productService.getProductById(1);
        assertNotNull(result);
        log.info("Product : {}", result);
    }

    @Test
    void getProductByIdUsingManagedChannel() {
        ProductDto result = productService.getProductByIdUsingManagedChannel(1);
        assertNull(result);
    }

    @Test
    void getProductByCategoryId() {
        List<ProductDto> result = productService.getProductByCategoryId(1);
        assertNotNull(result);
        log.info("Product List : {}", result);
    }
}
