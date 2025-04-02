package ecom.clothes.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Table(name = "tbl_categories")
@Getter
@Setter
@Entity
public class CategoriesEntity extends Timestamp implements Serializable {

    @Id
    @Column(name = "category_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "category_name", nullable = false)
    private String categoryName;

    @Column(name = "category_image", columnDefinition = "TEXT")
    private String categoryImage;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sub_category_id", referencedColumnName = "category_id", nullable = true)
    @com.fasterxml.jackson.annotation.JsonBackReference
    private CategoriesEntity subCategoryId;


}
