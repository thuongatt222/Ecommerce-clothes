package ecom.clothes.controller.response.Shop;

import ecom.clothes.controller.response.GlobalResponse;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ShopPageResponse extends GlobalResponse {
    private List<ShopResponse> shops;
}
