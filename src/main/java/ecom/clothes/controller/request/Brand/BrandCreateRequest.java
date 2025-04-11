package ecom.clothes.controller.request.Brand;

import ecom.clothes.model.CategoriesEntity;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class BrandCreateRequest {
    @NotBlank(message = "Bat buoc phai co ten thuong hieu")
    private String brandName;
    private String brandImage;
    private Long categoryId;
}
