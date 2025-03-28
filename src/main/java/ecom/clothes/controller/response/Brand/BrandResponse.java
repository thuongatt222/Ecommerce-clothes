package ecom.clothes.controller.response.Brand;

import ecom.clothes.model.CategoriesEntity;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BrandResponse {
    private Long brandId;
    private String brandName;
    private String brandImage;
    private CategoriesEntity categoriesEntity;
}
