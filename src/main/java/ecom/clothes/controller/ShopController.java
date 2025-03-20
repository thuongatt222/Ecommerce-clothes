package ecom.clothes.controller;

import ecom.clothes.controller.request.Shop.ShopCreateRequest;
import ecom.clothes.controller.request.Shop.ShopUpdateRequest;
import ecom.clothes.controller.response.ApiResponse;
import ecom.clothes.service.ShopService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(name = "Shop Controller")
@RequestMapping("/shop")
@Validated
@RequiredArgsConstructor
public class ShopController {
    private final ShopService shopService;

    @Operation(summary = "Danh sach shop")
    @GetMapping("/list")
    @PreAuthorize("hasAnyRole('manager', 'admin')")
    public ApiResponse getAllShops(@RequestParam(required = false) String keyword,
                                   @RequestParam(required = false) String sort,
                                   @RequestParam(defaultValue = "0") int page,
                                   @RequestParam(defaultValue = "10") int size) {
        return ApiResponse.builder()
                .status(HttpStatus.OK.value())
                .message("List of shops")
                .data(shopService.getShopPage(keyword, sort, page, size))
                .build();
    }

    @Operation(summary = "Shop chi tiet")
    @GetMapping("/{shopId}")
    @PreAuthorize("hasAnyRole('manager', 'admin')")
    public ApiResponse getShopById(@PathVariable @Min(value = 1, message = "Id phải lớn hơn hoặc bằng 1") Long shopId) {
        return ApiResponse.builder()
                .status(HttpStatus.OK.value())
                .message("Shop chi tiet")
                .data(shopService.getShop(shopId))
                .build();
    }

    @Operation(summary = "Tao moi shop")
    @PostMapping("/add")
    public ApiResponse createNewShop(@Valid @RequestBody ShopCreateRequest shopCreateRequest) {
        return ApiResponse.builder()
                .status(HttpStatus.CREATED.value())
                .message("Shop created")
                .data(shopService.save(shopCreateRequest))
                .build();
    }

    @Operation(summary = "Cap nhat shop")
    @PutMapping("/update")
    public ApiResponse updateShop(@Valid @RequestBody ShopUpdateRequest shopUpdateRequest) {
        shopService.update(shopUpdateRequest);
        return ApiResponse.builder()
                .status(HttpStatus.ACCEPTED.value())
                .message("Shop updated")
                .build();
    }
    
    @Operation(summary = "Xoa shop")
    @DeleteMapping("/delete")
    public ApiResponse deleteShop(@RequestParam @Min(value = 1, message = "Id phải lớn hơn hoặc bằng 1") Long shopId) {
        shopService.delete(shopId);
        return ApiResponse.builder()
                .status(HttpStatus.NO_CONTENT.value())
                .message("Shop deleted")
                .build();
    }
}
