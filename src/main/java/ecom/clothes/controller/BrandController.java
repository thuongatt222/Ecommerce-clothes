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
import jakarta.validation.constraints.Min;
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
    public ApiResponse getAllBrands(
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) String sort,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        log.info("Fetching brands - page: {}, size: {}, keyword: {}, sort: {}", page, size, keyword, sort);

        return ApiResponse.builder()
                .status(HttpStatus.OK.value())
                .message("Danh sách thương hiệu")
                .data(brandService.getAllBrands(keyword, sort, page, size))
                .build();
    }

    // GET: Lấy chi tiết một brand theo ID
    @GetMapping("/{id}")
    @Operation(summary = "Lấy chi tiết thương hiệu", description = "API lấy chi tiết một thương hiệu theo ID")
    public ApiResponse getBrandById(@PathVariable("id") Long brandId) {
        log.info("Fetching brand with id: {}", brandId);
        BrandResponse brand = brandService.getBrand(brandId);
        return ApiResponse.builder()
                .status(HttpStatus.OK.value())
                .message("Chi tiết thương hiệu")
                .data(brand)
                .build();
    }

    // POST: Thêm mới một brand
    @PostMapping
    @Operation(summary = "Thêm mới thương hiệu", description = "API thêm mới một thương hiệu")
    public ApiResponse createBrand(@Valid @RequestBody BrandCreateRequest request) {
        log.info("Creating new brand with name: {}", request.getBrandName());
        Long brandId = brandService.saveBrand(request);
        return ApiResponse.builder()
                .status(HttpStatus.CREATED.value())
                .message("Thương hiệu đã được tạo")
                .data(brandId)
                .build();
    }

    // PUT: Cập nhật thông tin brand
    @PutMapping
    @Operation(summary = "Cập nhật thương hiệu", description = "API cập nhật thông tin một thương hiệu")
    public ApiResponse updateBrand(@Valid @RequestBody BrandUpdateRequest request) {
        log.info("Updating brand with name: {}", request.getBrandName());
        brandService.updateBrand(request);
        return ApiResponse.builder()
                .status(HttpStatus.OK.value())
                .message("Thương hiệu đã được cập nhật")
                .build();
    }

    // DELETE: Xóa một brand theo ID
    @DeleteMapping("/{id}")
    @Operation(summary = "Xóa thương hiệu", description = "API xóa một thương hiệu theo ID")
    public ApiResponse deleteBrand(@PathVariable @Min(value = 1, message = "Id phải lớn hơn hoặc bằng 1") Long id) {
        log.info("Deleting brand with id: {}", id);
        brandService.deleteBrand(id);
        return ApiResponse.builder()
                .status(HttpStatus.NO_CONTENT.value())
                .message("Thương hiệu đã được xóa")
                .build();
    }
}