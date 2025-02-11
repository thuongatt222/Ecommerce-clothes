package ecom.clothes.model;

import jakarta.persistence.*;

@Entity
@Table(name = "tbl_categories")
public class CategoriesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private int categoryId;
    private String categoryNameLevel1;
    private String categoryNameLevel2;
    private String categoryNameLevel3;
    private String categoryNameLevel4;
    private String categoryNameLevel5;
    private String categoryImage;
}
