package client;

import com.colak.proto.Category;
import com.colak.proto.Product;
import com.colak.proto.ProductList;
import com.colak.proto.ProductServiceGrpc;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductGrpcClient {

    @GrpcClient("grpc-product-service")
    private ProductServiceGrpc.ProductServiceBlockingStub productServiceBlockingStub;

    public ProductDto getProductById(int id) {
        Product product = Product.newBuilder().setProductId(id).build();
        Product response = productServiceBlockingStub.getProductById(product);
        return DtoMappingHelper.mapProductToProductDTO(response);
    }

    public List<ProductDto> getProductByCategoryId(int id) {
        Category category = Category.newBuilder().setCategoryId(id).build();
        ProductList response = productServiceBlockingStub.getProductByCategoryId(category);
        return DtoMappingHelper.mapProductListToProductDTO(response);
    }
}
