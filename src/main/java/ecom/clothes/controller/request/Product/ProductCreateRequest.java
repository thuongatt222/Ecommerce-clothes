package ecom.clothes.controller.request.Product;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.util.List;

@Getter
public class ProductCreateRequest {

    @NotBlank(message = "Tên sản phẩm không được để trống")
    private String title;

    private String image;

    private String video;

    private String description;

    private String specification;

    private Integer buyturn;

    @NotNull(message = "Phải có brandId")
    private Long brandId;

    @NotNull(message = "Phải có categoryId")
    private Long categoryId;

    private List<String> attributeProduct;
}
