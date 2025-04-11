package ecom.clothes.controller.request.ProductDetails;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProductDetailsUpdateRequest {
    private Long productDetailId;
    private String variantAttributes;
    private BigDecimal price;
    private Integer stock;
}
