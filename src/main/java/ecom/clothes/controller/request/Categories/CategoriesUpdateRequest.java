package ecom.clothes.controller.request.Categories;

import ecom.clothes.controller.response.Categories.CategoriesResponse;
import ecom.clothes.model.CategoriesEntity;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CategoriesUpdateRequest {
    @NotBlank(message = "Id khong duoc de trong")
    @Min(value = 1, message = "Id phải lớn hơn hoặc bằng 1")
    private Long categoryId;

    @NotBlank(message = "Ten the loai khong duoc de trong")
    private String categoryName;

    private String categoryImage;

    private Long subCategoriesId;

}
