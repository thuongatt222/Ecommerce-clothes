package ecom.clothes.service.impl;

import ecom.clothes.controller.request.ProductDetails.ProductDetailsCreateRequest;
import ecom.clothes.controller.request.ProductDetails.ProductDetailsUpdateRequest;
import ecom.clothes.controller.response.Product.ProductResponse;
import ecom.clothes.controller.response.ProductDetails.ProductDetailsResponse;
import ecom.clothes.exception.ResourceNotFoundException;
import ecom.clothes.model.ProductDetailsEntity;
import ecom.clothes.repositories.ProductDetailsRepository;
import ecom.clothes.repositories.ProductRepository;
import ecom.clothes.service.ProductDetailsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductDetailsServiceImpl implements ProductDetailsService {

    private final ProductDetailsRepository productDetailsRepository;
    private final ProductRepository productRepository;



    @Override
    public ProductDetailsResponse getProductDetails(Long productDetailId) {
        ProductDetailsEntity productDetailsEntity = productDetailsRepository.findById(productDetailId)
                .orElseThrow(() -> new ResourceNotFoundException("Product details not found"));

        ProductResponse productResponse = new ProductResponse(
                productDetailsEntity.getProduct().getId(),
                productDetailsEntity.getProduct().getTitle()
        );

        return ProductDetailsResponse.builder()
                .productDetailId(productDetailsEntity.getProductDetailId())
                .product(productResponse)
                .variantAttributes(productDetailsEntity.getVariantAttributes())
                .price(productDetailsEntity.getPrice())
                .stock(productDetailsEntity.getStock())
                .build();
    }


    @Override
    public Long saveProductDetails(ProductDetailsCreateRequest request) {
        ProductDetailsEntity productDetailsEntity = new ProductDetailsEntity();

        // Find Product entity by productId
        productDetailsEntity.setProduct(productRepository.findById(request.getProductId())
                .orElseThrow(() -> new ResourceNotFoundException("Product not found")));
        productDetailsEntity.setVariantAttributes(request.getVariantAttributes());
        productDetailsEntity.setPrice(request.getPrice());
        productDetailsEntity.setStock(request.getStock());

        productDetailsRepository.save(productDetailsEntity);
        return productDetailsEntity.getProductDetailId();
    }

    @Override
    public void updateProductDetails(ProductDetailsUpdateRequest request) {
        ProductDetailsEntity productDetailsEntity = productDetailsRepository.findById(request.getProductDetailId())
                .orElseThrow(() -> new ResourceNotFoundException("Product details not found"));

        productDetailsEntity.setVariantAttributes(request.getVariantAttributes());
        productDetailsEntity.setPrice(request.getPrice());
        productDetailsEntity.setStock(request.getStock());

        productDetailsRepository.save(productDetailsEntity);
    }

    @Override
    public void deleteProductDetails(Long productDetailId) {
        productDetailsRepository.deleteById(productDetailId);
    }


}
