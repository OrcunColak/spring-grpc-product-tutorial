package client.product;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ProductDto {
    private int id;
    private int categoryId;
    private String name;
    private String description;
    private float price;
}
