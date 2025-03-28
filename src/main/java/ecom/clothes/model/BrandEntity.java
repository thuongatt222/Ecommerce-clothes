package ecom.clothes.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Table(name = "tbl_brands")
@Entity
@Getter
@Setter
public class BrandEntity {

    @Id
    @Column(name = "brand_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long brandId;

    @Column(name = "brand_name", nullable = false)
    private String brandName;

    @Column(name = "brand_image", columnDefinition = "TEXT")
    private String brandImage;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", referencedColumnName = "category_id")
    private CategoriesEntity categoryId;
}
