package ecom.clothes.controller.request.Shop;

import ecom.clothes.model.UserEntity;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShopCreateRequest {

    @NotBlank(message = "Tên shop không được để trống")
    private String shopName;

    @NotBlank(message = "Id người dùng không được để trống")
    private Long userId;
}
