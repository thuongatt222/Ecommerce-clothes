package ecom.clothes.controller.response.ProductDetails;

import ecom.clothes.controller.response.Product.ProductResponse;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
public class ProductDetailsResponse {
    private Long productDetailId;
    private ProductResponse product;
    private String variantAttributes;
    private BigDecimal price;
    private Integer stock;
}
