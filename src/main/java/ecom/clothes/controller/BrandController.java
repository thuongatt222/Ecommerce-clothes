package ecom.clothes.controller;

import ecom.clothes.controller.request.Brand.BrandCreateRequest;
import ecom.clothes.controller.request.Brand.BrandUpdateRequest;
import ecom.clothes.controller.response.ApiResponse;
import ecom.clothes.controller.response.Brand.BrandPageResponse;
import ecom.clothes.controller.response.Brand.BrandResponse;
import ecom.clothes.exception.ResourceNotFoundException;
import ecom.clothes.service.BrandService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/brands")
@Tag(name = "Brand Controller", description = "API for managing brands")
@Validated
@RequiredArgsConstructor
@Slf4j(topic = "Brand-Controller")
public class BrandController {

    private final BrandService brandService;

    // GET: Lấy danh sách brands với phân trang và tìm kiếm
    @GetMapping
    @Operation(summary = "Lấy danh sách thương hiệu", description = "API lấy danh sách thương hiệu với hỗ trợ tìm kiếm, sắp xếp và phân trang")
    public ResponseEntity<ApiResponse> getAllBrands(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String keyword,
            @RequestParam(defaultValue = "id:asc") String sort) {
        log.info("Fetching brands - page: {}, size: {}, keyword: {}, sort: {}", page, size, keyword, sort);
        BrandPageResponse brands = brandService.getAllBrands(keyword, sort, page, size);
        ApiResponse response = ApiResponse.builder()
                .status(HttpStatus.OK.value())
                .message("Danh sách thương hiệu")
                .data(brands)
                .build();
        return ResponseEntity.ok(response);
    }

    // GET: Lấy chi tiết một brand theo ID
    @GetMapping("/{id}")
    @Operation(summary = "Lấy chi tiết thương hiệu", description = "API lấy chi tiết một thương hiệu theo ID")
    public ResponseEntity<ApiResponse> getBrandById(@PathVariable("id") Long brandId) {
        log.info("Fetching brand with id: {}", brandId);
        BrandResponse brand = brandService.getBrand(brandId);
        ApiResponse response = ApiResponse.builder()
                .status(HttpStatus.OK.value())
                .message("Chi tiết thương hiệu")
                .data(brand)
                .build();
        return ResponseEntity.ok(response);
    }

    // POST: Thêm mới một brand
    @PostMapping
    @Operation(summary = "Thêm mới thương hiệu", description = "API thêm mới một thương hiệu")
    public ResponseEntity<ApiResponse> createBrand(@Valid @RequestBody BrandCreateRequest request) {
        log.info("Creating new brand with name: {}", request.getBrandName());
        Long brandId = brandService.saveBrand(request);
        ApiResponse response = ApiResponse.builder()
                .status(HttpStatus.CREATED.value())
                .message("Thương hiệu đã được tạo")
                .data(brandId)
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // PUT: Cập nhật thông tin brand
    @PutMapping
    @Operation(summary = "Cập nhật thương hiệu", description = "API cập nhật thông tin một thương hiệu")
    public ResponseEntity<ApiResponse> updateBrand(@Valid @RequestBody BrandUpdateRequest request) {
        log.info("Updating brand with name: {}", request.getBrandName());
        brandService.updateBrand(request);
        ApiResponse response = ApiResponse.builder()
                .status(HttpStatus.OK.value())
                .message("Thương hiệu đã được cập nhật")
                .data(null)
                .build();
        return ResponseEntity.ok(response);
    }

    // DELETE: Xóa một brand theo ID
    @DeleteMapping("/{id}")
    @Operation(summary = "Xóa thương hiệu", description = "API xóa một thương hiệu theo ID")
    public ResponseEntity<ApiResponse> deleteBrand(@PathVariable("id") Long brandId) {
        log.info("Deleting brand with id: {}", brandId);
        brandService.deleteBrand(brandId);
        ApiResponse response = ApiResponse.builder()
                .status(HttpStatus.NO_CONTENT.value())
                .message("Thương hiệu đã được xóa")
                .data(null)
                .build();
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
    }

    // Xử lý ngoại lệ
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse> handleResourceNotFound(ResourceNotFoundException ex) {
        log.error("Resource not found: {}", ex.getMessage());
        ApiResponse response = ApiResponse.builder()
                .status(HttpStatus.NOT_FOUND.value())
                .message(ex.getMessage())
                .data(null)
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse> handleGenericException(Exception ex) {
        log.error("Unexpected error: {}", ex.getMessage(), ex);
        ApiResponse response = ApiResponse.builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .message("Đã xảy ra lỗi không mong muốn")
                .data(null)
                .build();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
}