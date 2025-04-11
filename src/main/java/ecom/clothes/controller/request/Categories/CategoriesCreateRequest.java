package ecom.clothes.controller.request.Categories;

import ecom.clothes.controller.response.Categories.CategoriesResponse;
import ecom.clothes.model.CategoriesEntity;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CategoriesCreateRequest {
    @NotBlank(message = "Ten the loai khong duoc de trong")
    private String categoryName;

    private String categoryImage;

    private Long subCategoriesId;
}
