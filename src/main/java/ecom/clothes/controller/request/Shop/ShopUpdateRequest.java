package ecom.clothes.controller.request.Shop;


import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShopUpdateRequest {

    @Min(value = 1, message = "Id phải lớn hơn hoặc bằng 1")
    private Long Id;

    private String shopName;

    private String shopBanner;

    private String shopDescription;

}
