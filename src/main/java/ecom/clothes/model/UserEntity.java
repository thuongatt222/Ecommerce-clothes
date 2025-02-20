package ecom.clothes.model;

import ecom.clothes.common.Gender;
import ecom.clothes.common.UserStatus;
import ecom.clothes.common.UserType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.Date;

@Entity
@Setter
@Getter
@Table(name = "tbl_users")
public class UserEntity extends Timestamp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "username", unique = true, nullable = false, length = 50)
    private String username;

    @Column(name = "password", length = 50)
    private String password;

    @Column(name = "first_name", length = 50)
    private String firstName;

    @Column(name = "last_name", length = 50)
    private String lastName;

    @Column(name = "birthday")
    private Date birthday;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "phone", length = 15, unique = true)
    private String phone;

    @Column(name = "avatar")
    private String avatar;

    @Column(name = "last_login")
    private Date lastLogin;

    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private UserStatus status;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private UserType type;
}
