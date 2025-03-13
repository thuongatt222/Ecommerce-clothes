package ecom.clothes.controller.response.Shop;

import ecom.clothes.model.UserEntity;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ShopResponse {
    private Long shopId;
    private UserEntity user;
    private String shopName;
    private String shopBanner;
    private String shopDescription;
    private BigDecimal shopRating;
}
