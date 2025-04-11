package ecom.clothes.controller.response.Address;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AddressResponse {
    private Long addressId;
    private String address;
    private Long userId;
    private Boolean isDefault;
}
