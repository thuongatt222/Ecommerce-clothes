package ecom.clothes.service;

import ecom.clothes.controller.request.Brand.BrandCreateRequest;
import ecom.clothes.controller.request.Brand.BrandUpdateRequest;
import ecom.clothes.controller.response.Brand.BrandPageResponse;
import ecom.clothes.controller.response.Brand.BrandResponse;

public interface BrandService {
    BrandPageResponse getAllBrands(String keyword, String sort, int page, int size);

    BrandResponse getBrand(Long brandId);

    Long saveBrand(BrandCreateRequest request);

    void updateBrand(BrandUpdateRequest request);

    void deleteBrand(Long brandId);
}
