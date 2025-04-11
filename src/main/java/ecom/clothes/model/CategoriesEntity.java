package ecom.clothes.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Table(name = "tbl_categories")
@Getter
@Setter
@Entity
public class CategoriesEntity {

    @Id
    @Column(name = "category_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "category_name", nullable = false)
    private String categoryName;

    @Column(name = "category_image", columnDefinition = "TEXT")
    private String categoryImage;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sub_category_id", referencedColumnName = "category_id")
    private CategoriesEntity subCategoryId;

}
