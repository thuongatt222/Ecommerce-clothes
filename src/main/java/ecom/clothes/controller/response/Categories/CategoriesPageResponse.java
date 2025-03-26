package ecom.clothes.controller.response.Categories;

import ecom.clothes.controller.response.GlobalResponse;
import lombok.*;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CategoriesPageResponse extends GlobalResponse {

    private List<CategoriesResponse> categories;
}
