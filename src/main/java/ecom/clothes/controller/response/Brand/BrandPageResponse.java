package ecom.clothes.controller.response.Brand;

import ecom.clothes.controller.response.GlobalResponse;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BrandPageResponse extends GlobalResponse {
    private List<BrandResponse> brands;
}
