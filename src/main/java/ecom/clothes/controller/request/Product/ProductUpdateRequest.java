package ecom.clothes.controller.request.Product;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.util.List;

@Getter
public class ProductUpdateRequest {

    @NotNull(message = "Phải có productId")
    @Min(value = 1, message = "Giá trị productId phải lớn hơn hoặc bằng 1")
    private Long productId;

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
