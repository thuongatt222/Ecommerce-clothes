package ecom.clothes.controller;

import ecom.clothes.controller.request.Categories.CategoriesCreateRequest;
import ecom.clothes.controller.request.Categories.CategoriesUpdateRequest;
import ecom.clothes.controller.response.ApiResponse;
import ecom.clothes.exception.InvalidDataException;
import ecom.clothes.exception.ResourceNotFoundException;
import ecom.clothes.service.CategoriesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
@RequestMapping("/categories")
@Tag(name = "Categories Controller", description = "API for managing categories")
@Validated
@RequiredArgsConstructor
@Slf4j(topic = "Category-Controller")
public class CategoryController {

    private final CategoriesService categoryService;

    // GET: Lấy danh sách danh mục
    @GetMapping("/list")
    @Operation(summary = "Lấy danh sách danh mục", description = "API lấy danh sách danh mục với hỗ trợ tìm kiếm, sắp xếp và phân trang")
    public ApiResponse getAllCategories(
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) String sort,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        log.info("Fetching categories - page: {}, size: {}, keyword: {}, sort: {}", page, size, keyword, sort);

        return ApiResponse.builder()
                .status(HttpStatus.OK.value())
                .message("List categories")
                .data(categoryService.getCategories(keyword, sort, page, size))
                .build();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Lấy chi tiết danh mục", description = "API lấy chi tiết một danh mục theo ID")
    public ApiResponse getCategoryById(@PathVariable @Min(value = 1, message = "lon hon hoac bang 1") Long id) {
        log.info("Fetching category with id: {}", id);

        return ApiResponse.builder()
                .status(HttpStatus.OK.value())
                .message("Chi tiết danh mục")
                .data(categoryService.getCategory(id))
                .build();
    }

    // POST: Thêm mới danh mục
    @PostMapping("/add")
    @Operation(summary = "Thêm mới danh mục", description = "API thêm mới một danh mục")
    public ResponseEntity<ApiResponse> createNewCategory(@Valid @RequestBody CategoriesCreateRequest request) {
        log.info("Creating new category with name: {}", request.getCategoryName());
        Long categoryId = categoryService.addCategory(request);
        ApiResponse response = ApiResponse.builder()
                .status(HttpStatus.CREATED.value())
                .message("Danh mục đã được tạo")
                .data(categoryId)
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // PUT: Cập nhật danh mục
    @PutMapping("/update")
    @Operation(summary = "Cập nhật danh mục", description = "API cập nhật thông tin một danh mục")
    @ApiResponses(value = {

    })
    public ResponseEntity<ApiResponse> updateCategory(@Valid @RequestBody CategoriesUpdateRequest request) {
        log.info("Updating category with name: {}", request.getCategoryName());
        categoryService.updateCategory(request);
        ApiResponse response = ApiResponse.builder()
                .status(HttpStatus.OK.value())
                .message("Danh mục đã được cập nhật")
                .data(null)
                .build();
        return ResponseEntity.ok(response);
    }

    // DELETE: Xóa danh mục theo ID
    @DeleteMapping("/del")
    @Operation(summary = "Xóa danh mục", description = "API xóa một danh mục theo ID")
    @ApiResponses(value = {

    })
    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Long id) {
        log.info("Deleting category with id: {}", id);
        categoryService.deleteCategory(id);
        ApiResponse response = ApiResponse.builder()
                .status(HttpStatus.NO_CONTENT.value())
                .message("Danh mục đã được xóa")
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

    @ExceptionHandler(InvalidDataException.class)
    public ResponseEntity<ApiResponse> handleInvalidData(InvalidDataException ex) {
        log.error("Invalid data: {}", ex.getMessage());
        ApiResponse response = ApiResponse.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .message(ex.getMessage())
                .data(null)
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
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