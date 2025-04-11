package ecom.clothes.service.impl;

import ecom.clothes.controller.request.Address.AddressCreateRequest;
import ecom.clothes.controller.request.Address.AddressUpdateRequest;
import ecom.clothes.controller.response.Address.AddressResponse;
import ecom.clothes.exception.ResourceNotFoundException;
import ecom.clothes.model.AddressEntity;
import ecom.clothes.model.UserEntity;
import ecom.clothes.repositories.AddressRepository;
import ecom.clothes.repositories.UserRepository;
import ecom.clothes.service.AddressService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;
    private final UserRepository userRepository;

    @Override
    public AddressResponse getAddress(Long addressId) {
        AddressEntity addressEntity = addressRepository.findById(addressId)
                .orElseThrow(() -> new ResourceNotFoundException("Address not found"));

        return AddressResponse.builder()
                .addressId(addressEntity.getId())
                .address(addressEntity.getAddress())
                .userId(addressEntity.getUser().getId())
                .isDefault(addressEntity.getIsDefault())
                .build();
    }

    @Override
    public List<AddressResponse> getAllAddressesByUserId(Long userId) {
        List<AddressEntity> addressEntities = addressRepository.findByUser_Id(userId);

        return addressEntities.stream().map(addressEntity -> AddressResponse.builder()
                        .addressId(addressEntity.getId())
                        .address(addressEntity.getAddress())
                        .userId(addressEntity.getUser().getId())
                        .isDefault(addressEntity.getIsDefault())
                        .build())
                .collect(Collectors.toList());
    }


    @Override
    @Transactional
    public Long saveAddress(AddressCreateRequest request) {
        AddressEntity addressEntity = new AddressEntity();

        // Lấy user từ DB
        UserEntity user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        // Gán UserEntity vào AddressEntity
        addressEntity.setUser(user); // CHÚ Ý: đây là setUser, không phải getUserId

        addressEntity.setAddress(request.getAddress());
        addressEntity.setIsDefault(request.getIsDefault());

        addressRepository.save(addressEntity);
        return addressEntity.getId();
    }



    @Override
    public void updateAddress(AddressUpdateRequest request) {
        AddressEntity addressEntity = addressRepository.findById(request.getAddressId())
                .orElseThrow(() -> new ResourceNotFoundException("Address not found"));

        addressEntity.setAddress(request.getAddress());
        addressEntity.setIsDefault(request.getIsDefault());

        addressRepository.save(addressEntity);
    }

    @Override
    public void deleteAddress(Long addressId) {
        addressRepository.deleteById(addressId);
    }
}
