package ecom.clothes.model;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Entity
@Table(name = "tbl_products")
@Getter
@Setter
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Integer productId;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "image")
    private String image;

    @Column(name = "video")
    private String video;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "specification", columnDefinition = "TEXT")
    private String specification;

    @Column(name = "buyturn")
    private Integer buyTurn;

    @ManyToOne
    @JoinColumn(name = "brand_id", referencedColumnName = "brand_id")
    private BrandsEntity brandId;

    @Column(name = "attribute_product", columnDefinition = "TEXT")
    @JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.WRAPPER_ARRAY)
    private String attributeProduct;

    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "category_id")
    private CategoriesEntity categoryId;
}
