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
    private Long categoryId;

    @Column(name = "category_name", nullable = false)
    private String categoryName;

    @Column(name = "category_image")
    private String categoryImage;

    @ManyToOne
    @JoinColumn(name = "sub_category_id", referencedColumnName = "category_id")
    private CategoriesEntity subCategoryId;

}
