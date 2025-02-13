package ecom.clothes.model;

import jakarta.persistence.*;

@Entity
@Table(name = "tbl_brands")
public class BrandsEntity extends Timestamp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "brand_id")
    private Long brandId;
    private String brandName;
    private String brandImage;
}
