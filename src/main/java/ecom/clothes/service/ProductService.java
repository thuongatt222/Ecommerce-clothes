package ecom.clothes.service;

import ecom.clothes.controller.request.Product.ProductCreateRequest;
import ecom.clothes.controller.request.Product.ProductUpdateRequest;
import ecom.clothes.controller.response.Product.ProductPageResponse;
import ecom.clothes.controller.response.Product.ProductResponse;

public interface ProductService {
    ProductPageResponse getAllProducts(String keyword, String sort, int page, int size);

    ProductResponse getProduct(Long productId);

    Long saveProduct(ProductCreateRequest request);

    void updateProduct(ProductUpdateRequest request);

    void deleteProduct(Long productId);
}
