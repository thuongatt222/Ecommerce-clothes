package ecom.clothes.controller.response.Shop;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ShopPageResponse {
    private List<ShopResponse> shops;
}
