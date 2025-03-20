package ecom.clothes.controller.request.User;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class UserCreateRequest implements Serializable {

    @NotBlank(message = "Tên đăng nhập không được để trống")
    @Size(min = 6, max = 50, message = "Tên đăng nhập phải từ 6 đến 50 ký tự")
    private String username;

    @NotBlank(message = "Mật khẩu đăng nhập không được để trống")
    @Size(min = 8, max = 35, message = "Tên đăng nhập phải từ 8 đến 35 ký tự")
    private String password;
}
