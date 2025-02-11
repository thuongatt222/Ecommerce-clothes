package ecom.clothes.model;

import jakarta.persistence.*;

@Entity
@Table(name = "tbl_address")
public class AddressEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id")
    private int addressId;
    private String address;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    private Boolean isDefault;
}
