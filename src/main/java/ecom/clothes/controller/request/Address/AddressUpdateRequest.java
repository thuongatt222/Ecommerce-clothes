package ecom.clothes.controller.request.Address;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressUpdateRequest {
    private Long addressId;
    private String address;
    private Boolean isDefault;
}
