package ecom.clothes.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "tbl_product_details")
@Getter
@Setter
public class ProductDetailsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_detail_id")
    private Long productDetailId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", referencedColumnName = "product_id")
    private ProductEntity product;

    @Column(name = "variant_attributes", columnDefinition = "jsonb")
    private String variantAttributes;

    @Column(name = "price", precision = 10, scale = 3)
    private BigDecimal price;

    @Column(name = "stock")
    private Integer stock;
}
