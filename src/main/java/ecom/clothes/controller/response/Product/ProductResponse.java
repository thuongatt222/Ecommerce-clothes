package ecom.clothes.controller.response.Product;

import ecom.clothes.model.BrandEntity;
import ecom.clothes.model.CategoriesEntity;
import ecom.clothes.model.ShopEntity;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponse {
    private Long productId;
    private String title;
    private String image;
    private String video;
    private String description;
    private String specification;
    private Integer buyturn;
    private BrandEntity brand;
    private CategoriesEntity category;
    private ShopEntity shop;
    private List<String> attributeProduct;

    public ProductResponse(Long productId, String title) {
        this.productId = productId;
        this.title = title;
    }
}
