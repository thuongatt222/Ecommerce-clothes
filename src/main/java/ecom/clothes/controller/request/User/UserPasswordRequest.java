package ecom.clothes.controller.request.User;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class UserPasswordRequest implements Serializable {

    @Min(value = 1, message = "Id phải lớn hơn hoặc bằng 1")
    private Long id;

    @NotBlank(message = "Mật khẩu đăng nhập không được để trống")
    @Size(min = 8, max = 35, message = "Tên đăng nhập phải từ 8 đến 35 ký tự")
    private String password;

    @NotBlank(message = "Nhập lại mật khẩu đăng nhập không được để trống")
    @Size(min = 8, max = 35, message = "Tên đăng nhập phải từ 8 đến 35 ký tự")
    private String confirmPassword;
}
