package ecom.clothes.controller.request.User;

import ecom.clothes.common.Gender;
import ecom.clothes.common.UserStatus;
import ecom.clothes.common.UserType;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
public class UserUpdateRequest implements Serializable {
    @Min(value = 1, message = "Id phải lớn hơn hoặc bằng 1")
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
