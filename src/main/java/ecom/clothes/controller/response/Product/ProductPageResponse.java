package ecom.clothes.controller.response.Product;

import ecom.clothes.controller.response.GlobalResponse;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductPageResponse extends GlobalResponse {
    private List<ProductResponse> products;
}
