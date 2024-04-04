package client;

import com.colak.proto.Category;
import com.colak.proto.Product;
import com.colak.proto.ProductList;
import com.colak.proto.ProductServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.Status;
import io.grpc.StatusRuntimeException;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ProductGrpcClient {

    // grpc-product-service is defined in application.yaml
    @GrpcClient("grpc-product-service")
    private ProductServiceGrpc.ProductServiceBlockingStub productServiceBlockingStub;

    public ProductDto getProductById(int id) {
        ProductDto result = null;
        try {
            Product product = Product.newBuilder().setProductId(id).build();
            Product response = productServiceBlockingStub.getProductById(product);
            result = DtoMappingHelper.mapProductToProductDTO(response);
        } catch (StatusRuntimeException ex) {
            Status status = ex.getStatus();
            log.info("error code -" + status.getCode());
            log.info("error description -" + status.getDescription());
        }
        return result;
    }

    // See https://medium.com/@javainuse/spring-boot-3-grpc-error-handling-example-a63711fc45ae
    // This shows another example of using Grpc without application.yaml
    public ProductDto getProductByIdUsingManagedChannel(int id) {

        ProductDto result = null;

        ManagedChannel channel = ManagedChannelBuilder
                .forAddress("localhost", 8090)
                .usePlaintext()
                .build();

        ProductServiceGrpc.ProductServiceBlockingStub stub = ProductServiceGrpc.newBlockingStub(channel);

        Product product = Product.newBuilder().setProductId(id).build();
        try {
            Product response = stub.getProductById(product);
            result = DtoMappingHelper.mapProductToProductDTO(response);
        } catch (StatusRuntimeException ex) {
            Status status = ex.getStatus();
            log.info("error code -" + status.getCode());
            log.info("error description -" + status.getDescription());
        }
        return result;
    }

    public List<ProductDto> getProductByCategoryId(int id) {
        Category category = Category.newBuilder().setCategoryId(id).build();
        ProductList response = productServiceBlockingStub.getProductByCategoryId(category);
        return DtoMappingHelper.mapProductListToProductDTO(response);
    }
}
