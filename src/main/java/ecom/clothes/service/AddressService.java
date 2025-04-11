package ecom.clothes.service;

import ecom.clothes.controller.request.Address.AddressCreateRequest;
import ecom.clothes.controller.request.Address.AddressUpdateRequest;
import ecom.clothes.controller.response.Address.AddressResponse;

import java.util.List;

public interface AddressService {

    AddressResponse getAddress(Long addressId);

    List<AddressResponse> getAllAddressesByUserId(Long userId);

    Long saveAddress(AddressCreateRequest request);

    void updateAddress(AddressUpdateRequest request);

    void deleteAddress(Long addressId);
}
