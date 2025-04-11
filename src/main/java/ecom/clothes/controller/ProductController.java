package ecom.clothes.controller;

import ecom.clothes.controller.request.Product.ProductCreateRequest;
import ecom.clothes.controller.request.Product.ProductUpdateRequest;
import ecom.clothes.controller.response.ApiResponse;
import ecom.clothes.controller.response.Product.ProductPageResponse;
import ecom.clothes.controller.response.Product.ProductResponse;
import ecom.clothes.exception.ResourceNotFoundException;
import ecom.clothes.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/products")
@Tag(name = "Product Controller", description = "API for managing products")
@Validated
@RequiredArgsConstructor
@Slf4j(topic = "Product-Controller")
public class ProductController {

    private final ProductService productService;

    // GET: Lấy danh sách products với phân trang và tìm kiếm
    @GetMapping
    @Operation(summary = "Lấy danh sách sản phẩm", description = "API lấy danh sách sản phẩm với hỗ trợ tìm kiếm, sắp xếp và phân trang")
    public ApiResponse getAllProducts(
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) String sort,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        log.info("Fetching products - page: {}, size: {}, keyword: {}, sort: {}", page, size, keyword, sort);

        return ApiResponse.builder()
                .status(HttpStatus.OK.value())
                .message("Danh sách sản phẩm")
                .data(productService.getAllProducts(keyword, sort, page, size))
                .build();
    }

    // GET: Lấy chi tiết một product theo ID
    @GetMapping("/{id}")
    @Operation(summary = "Lấy chi tiết sản phẩm", description = "API lấy chi tiết một sản phẩm theo ID")
    public ApiResponse getProductById(@PathVariable("id") Long productId) {
        log.info("Fetching product with id: {}", productId);
        ProductResponse product = productService.getProduct(productId);
        return ApiResponse.builder()
                .status(HttpStatus.OK.value())
                .message("Chi tiết sản phẩm")
                .data(product)
                .build();
    }

    // POST: Thêm mới một product
    @PostMapping
    @Operation(summary = "Thêm mới sản phẩm", description = "API thêm mới một sản phẩm")
    public ApiResponse createProduct(@Valid @RequestBody ProductCreateRequest request) {
        log.info("Creating new product with name: {}", request.getTitle());
        Long productId = productService.saveProduct(request);
        return ApiResponse.builder()
                .status(HttpStatus.CREATED.value())
                .message("Sản phẩm đã được tạo")
                .data(productId)
                .build();
    }

    // PUT: Cập nhật thông tin product
    @PutMapping
    @Operation(summary = "Cập nhật sản phẩm", description = "API cập nhật thông tin một sản phẩm")
    public ApiResponse updateProduct(@Valid @RequestBody ProductUpdateRequest request) {
        log.info("Updating product with name: {}", request.getTitle());
        productService.updateProduct(request);
        return ApiResponse.builder()
                .status(HttpStatus.OK.value())
                .message("Sản phẩm đã được cập nhật")
                .build();
    }

    // DELETE: Xóa một product theo ID
    @DeleteMapping("/{id}")
    @Operation(summary = "Xóa sản phẩm", description = "API xóa một sản phẩm theo ID")
    public ApiResponse deleteProduct(@PathVariable @Min(value = 1, message = "Id phải lớn hơn hoặc bằng 1") Long id) {
        log.info("Deleting product with id: {}", id);
        productService.deleteProduct(id);
        return ApiResponse.builder()
                .status(HttpStatus.NO_CONTENT.value())
                .message("Sản phẩm đã được xóa")
                .build();
    }
}
