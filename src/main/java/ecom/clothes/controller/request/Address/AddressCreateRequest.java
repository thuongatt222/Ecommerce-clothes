package ecom.clothes.controller.request.Address;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressCreateRequest {
    private Long userId;
    private String address;
    private Boolean isDefault;
}
