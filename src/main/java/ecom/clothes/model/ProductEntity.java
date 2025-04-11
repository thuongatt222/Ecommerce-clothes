package ecom.clothes.model;

import com.fasterxml.jackson.databind.JsonNode;
import ecom.clothes.common.JsonNodeConverter;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Table(name = "tbl_products")
@Getter
@Setter
@Entity
public class ProductEntity extends Timestamp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id;

    private String title;
    private String image;
    private String video;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(columnDefinition = "TEXT")
    private String specification;

    private Integer buyturn;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "brand_id", referencedColumnName = "brand_id")
    private BrandEntity brandId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", referencedColumnName = "category_id")
    private CategoriesEntity categoryId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shop_id", referencedColumnName = "shop_id")
    private ShopEntity shopId;

    @Column(columnDefinition = "jsonb", name = "attribute")
    private String attributeProduct;
}
