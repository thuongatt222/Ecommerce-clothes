package ecom.clothes.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import ecom.clothes.controller.response.Categories.CategoriesResponse;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonBackReference;
import java.util.ArrayList;
import java.util.List;

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

    @Column(name = "sub_category_id")
    private Long subCategoryId;

}
