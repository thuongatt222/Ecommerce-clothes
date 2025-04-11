package ecom.clothes.controller.request.Brand;

import ecom.clothes.model.CategoriesEntity;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class BrandUpdateRequest {
    @NotBlank
    @Min(value = 1, message = "gia tri id phai lon hon hoac bang 1")
    private Long brandId;
    @NotBlank(message = "Bat buoc phai co ten thuong hieu")
    private String brandName;
    private String brandImage;
    private Long categoryId;
}
