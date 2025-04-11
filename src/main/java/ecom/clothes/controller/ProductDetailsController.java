package ecom.clothes.controller;

import ecom.clothes.controller.request.ProductDetails.ProductDetailsCreateRequest;
import ecom.clothes.controller.request.ProductDetails.ProductDetailsUpdateRequest;
import ecom.clothes.controller.response.ApiResponse;
import ecom.clothes.controller.response.ProductDetails.ProductDetailsResponse;
import ecom.clothes.service.ProductDetailsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/product-details")
@Tag(name = "Product Details Controller", description = "API for managing product details")
@RequiredArgsConstructor
public class ProductDetailsController {

    private final ProductDetailsService productDetailsService;

    // GET: Lấy chi tiết product details theo ID
    @GetMapping("/{id}")
    @Operation(summary = "Lấy chi tiết product details", description = "API lấy chi tiết product details theo ID")
    public ApiResponse getProductDetails(@PathVariable("id") Long productDetailId) {
        ProductDetailsResponse productDetails = productDetailsService.getProductDetails(productDetailId);
        return ApiResponse.builder()
                .status(HttpStatus.OK.value())
                .message("Chi tiết product details")
                .data(productDetails)
                .build();
    }

    // POST: Thêm mới product details
    @PostMapping
    @Operation(summary = "Thêm mới product details", description = "API thêm mới một product details")
    public ApiResponse createProductDetails(@Valid @RequestBody ProductDetailsCreateRequest request) {
        Long productDetailId = productDetailsService.saveProductDetails(request);
        return ApiResponse.builder()
                .status(HttpStatus.CREATED.value())
                .message("Product details đã được tạo")
                .data(productDetailId)
                .build();
    }

    // PUT: Cập nhật product details
    @PutMapping
    @Operation(summary = "Cập nhật product details", description = "API cập nhật thông tin một product details")
    public ApiResponse updateProductDetails(@Valid @RequestBody ProductDetailsUpdateRequest request) {
        productDetailsService.updateProductDetails(request);
        return ApiResponse.builder()
                .status(HttpStatus.OK.value())
                .message("Product details đã được cập nhật")
                .build();
    }

    // DELETE: Xóa product details theo ID
    @DeleteMapping("/{id}")
    @Operation(summary = "Xóa product details", description = "API xóa product details theo ID")
    public ApiResponse deleteProductDetails(@PathVariable @Min(value = 1, message = "Id phải lớn hơn hoặc bằng 1") Long id) {
        productDetailsService.deleteProductDetails(id);
        return ApiResponse.builder()
                .status(HttpStatus.NO_CONTENT.value())
                .message("Product details đã được xóa")
                .build();
    }
}
