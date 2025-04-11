package ecom.clothes.service.impl;

import ecom.clothes.controller.request.Product.ProductCreateRequest;
import ecom.clothes.controller.request.Product.ProductUpdateRequest;
import ecom.clothes.controller.response.Product.ProductPageResponse;
import ecom.clothes.controller.response.Product.ProductResponse;
import ecom.clothes.exception.ResourceNotFoundException;
import ecom.clothes.model.BrandEntity;
import ecom.clothes.model.ProductEntity;
import ecom.clothes.model.CategoriesEntity;
import ecom.clothes.repositories.BrandRepository;
import ecom.clothes.repositories.ProductRepository;
import ecom.clothes.repositories.CategoriesRepository;
import ecom.clothes.service.ProductService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@AllArgsConstructor
@Slf4j(topic = "Product-Service-Impl")
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoriesRepository categoriesRepository;
    private final BrandRepository brandRepository;

    @Override
    public ProductPageResponse getAllProducts(String keyword, String sort, int page, int size) {
        Sort.Order order = new Sort.Order(Sort.Direction.ASC, "id");
        if (StringUtils.hasLength(sort)) {
            Pattern pattern = Pattern.compile("(\\w+?)(:)(.*)");
            Matcher matcher = pattern.matcher(sort);
            if (matcher.find()) {
                String columnName = matcher.group(1);
                if (matcher.group(3).equalsIgnoreCase("asc")) {
                    order = new Sort.Order(Sort.Direction.ASC, columnName);
                } else {
                    order = new Sort.Order(Sort.Direction.DESC, columnName);
                }
            }
        }

        int pageNo = 0;
        if (page > 0) {
            pageNo = page - 1;
        }

        Pageable pageable = PageRequest.of(pageNo, size, Sort.by(order));

        Page<ProductEntity> products = null;

        if (StringUtils.hasLength(keyword)) {
            keyword = "%" + keyword + "%";

            products = productRepository.findByTitle(keyword, pageable);
        } else {
            products = productRepository.findAll(pageable);
        }
        return getProductPageResponse(page, size, products);
    }

    @Override
    public ProductResponse getProduct(Long productId) {
        ProductEntity productEntity = getProductEntity(productId);
        return ProductResponse.builder()
                .productId(productEntity.getId())
                .title(productEntity.getTitle())
                .image(productEntity.getImage())
                .video(productEntity.getVideo())
                .description(productEntity.getDescription())
                .specification(productEntity.getSpecification())
                .brand(productEntity.getBrandId())
                .category(productEntity.getCategoryId())
                .shop(productEntity.getShopId())
                .build();
    }

    @Override
    public Long saveProduct(ProductCreateRequest request) {
        ProductEntity productEntity = new ProductEntity();

        CategoriesEntity category = categoriesRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Category not found"));

        BrandEntity brand = brandRepository.findById(request.getBrandId())
                .orElseThrow(() -> new ResourceNotFoundException("Brand not found"));

        productEntity.setTitle(request.getTitle());
        productEntity.setImage(request.getImage());
        productEntity.setVideo(request.getVideo());
        productEntity.setDescription(request.getDescription());
        productEntity.setSpecification(request.getSpecification());
        productEntity.setBuyturn(request.getBuyturn());
        productEntity.setCategoryId(category);
        productEntity.setBrandId(brand);
        if (request.getAttributeProduct() != null) {
            productEntity.setAttributeProduct(request.getAttributeProduct().toString());
        }

        productRepository.save(productEntity);
        return productEntity.getId();
    }

    @Override
    public void updateProduct(ProductUpdateRequest request) {
        ProductEntity productEntity = productRepository.findById(request.getProductId())
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));
        productEntity.setTitle(request.getTitle());
        productEntity.setImage(request.getImage());
        productEntity.setVideo(request.getVideo());
        productEntity.setDescription(request.getDescription());
        productEntity.setSpecification(request.getSpecification());
        productEntity.setBuyturn(request.getBuyturn());

        CategoriesEntity category = categoriesRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Category not found"));
        productEntity.setCategoryId(category);

        BrandEntity brand = brandRepository.findById(request.getBrandId())
                .orElseThrow(() -> new ResourceNotFoundException("Brand not found"));
        productEntity.setBrandId(brand);

        if (request.getAttributeProduct() != null) {
            productEntity.setAttributeProduct(request.getAttributeProduct().toString());
        }

        productRepository.save(productEntity);
    }


    @Override
    public void deleteProduct(Long productId) {
        productRepository.deleteById(productId);
    }

    private ProductPageResponse getProductPageResponse(int page, int size, Page<ProductEntity> products) {
        List<ProductResponse> productResponses = products.stream().map(productEntity -> ProductResponse.builder()
                        .productId(productEntity.getId())
                        .title(productEntity.getTitle())
                        .image(productEntity.getImage())
                        .video(productEntity.getVideo())
                        .description(productEntity.getDescription())
                        .specification(productEntity.getSpecification())
                        .brand(productEntity.getBrandId())
                        .category(productEntity.getCategoryId())
                        .build())
                .toList();

        ProductPageResponse productPageResponse = new ProductPageResponse();
        productPageResponse.setPage(page);
        productPageResponse.setSize(size);
        productPageResponse.setTotalElements(products.getTotalElements());
        productPageResponse.setTotalPages(products.getTotalPages());
        productPageResponse.setProducts(productResponses);

        return productPageResponse;
    }

    private ProductEntity getProductEntity(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product not found"));
    }
}
