package server;

import com.colak.proto.Category;
import com.colak.proto.Product;
import com.colak.proto.ProductList;
import com.colak.proto.ProductServiceGrpc;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

import java.util.ArrayList;
import java.util.List;

@GrpcService
public class ProductServiceImpl extends ProductServiceGrpc.ProductServiceImplBase {
    Product product1 = Product
            .newBuilder()
            .setProductId(1)
            .setName("product1")
            .setDescription("description1")
            .setPrice(1)
            .setCategoryId(1)
            .build();
    private final List<Product> productList = new ArrayList<>(List.of(product1));

    public ProductServiceImpl() {
        System.out.println("ProductServiceImpl");
    }

    @Override
    public void getProductById(Product request, StreamObserver<Product> responseObserver) {
        productList
                .stream()
                .filter(product -> product.getProductId() == request.getProductId())
                .findFirst()
                .ifPresent(responseObserver::onNext);
        responseObserver.onCompleted();
    }

    @Override
    public void getProductByCategoryId(Category request, StreamObserver<ProductList> responseObserver) {
        List<Product> productResult = productList
                .stream()
                .filter(product -> product.getCategoryId() == request.getCategoryId())
                .toList();

        ProductList resultList = ProductList.newBuilder().addAllProduct(productResult).build();

        responseObserver.onNext(resultList);
        responseObserver.onCompleted();
    }
}
