package ecom.clothes.service;

import ecom.clothes.controller.request.ProductDetails.ProductDetailsCreateRequest;
import ecom.clothes.controller.request.ProductDetails.ProductDetailsUpdateRequest;
import ecom.clothes.controller.response.ProductDetails.ProductDetailsResponse;

public interface ProductDetailsService {

    ProductDetailsResponse getProductDetails(Long productDetailId);

    Long saveProductDetails(ProductDetailsCreateRequest request);

    void updateProductDetails(ProductDetailsUpdateRequest request);

    void deleteProductDetails(Long productDetailId);
}
