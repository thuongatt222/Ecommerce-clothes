package ecom.clothes.model;

import jakarta.persistence.*;

@Entity
@Table(name = "tbl_address")
public class AddressEntity extends Timestamp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id")
    private Long addressId;

    @Column(name = "address")
    private String address;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @Column(name = "is_default")
    private Boolean isDefault;
}
