package ecom.clothes.controller.response;

import ecom.clothes.common.Gender;
import ecom.clothes.common.UserStatus;
import ecom.clothes.common.UserType;
import lombok.*;

import java.util.Date;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String avatar;
    private Date birthday;
    private Gender gender;
    private UserType userType;
    private UserStatus userStatus;
}
