package ecom.clothes.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "tbl_shops")
@Getter
@Setter
public class ShopEntity extends Timestamp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "shop_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)
    private UserEntity user;

    @Column(name = "shop_name", nullable = false, length = 255)
    private String shopName;

    @Column(name = "shop_banner", columnDefinition = "TEXT")
    private String shopBanner;

    @Column(name = "shop_description", columnDefinition = "TEXT")
    private String shopDescription;

    @Column(name = "shop_rating", precision = 3, scale = 2)
    private BigDecimal shopRating;

    @OneToMany(mappedBy = "shop", fetch = FetchType.LAZY)
    private List<ProductEntity> products;
}
