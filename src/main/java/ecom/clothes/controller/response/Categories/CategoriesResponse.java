package ecom.clothes.controller.response.Categories;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CategoriesResponse {

    private Long categoryId;
    private String categoryName;
    private String categoryImage;
    private Long subCategoryId;
}
