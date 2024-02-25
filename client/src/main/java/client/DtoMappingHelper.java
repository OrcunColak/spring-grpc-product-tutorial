package client;

import com.colak.proto.Product;
import com.colak.proto.ProductList;

import java.util.ArrayList;
import java.util.List;

public class DtoMappingHelper {

    public static List<ProductDto> mapProductListToProductDTO(ProductList productList) {
        List<ProductDto> products = new ArrayList<>();
        productList.getProductList().forEach(product -> {
            ProductDto dto = mapProductToProductDTO(product);
            products.add(dto);
        });
        return products;
    }

    public static ProductDto mapProductToProductDTO(Product product) {
        ProductDto dto = new ProductDto();
        dto.setId(product.getProductId());
        dto.setCategoryId(product.getCategoryId());
        dto.setName(product.getName());
        dto.setDescription(product.getDescription());
        dto.setPrice(product.getPrice());
        return dto;
    }

}
