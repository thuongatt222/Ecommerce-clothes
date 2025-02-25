package ecom.clothes.controller;

import ecom.clothes.controller.request.UserCreateRequest;
import ecom.clothes.controller.request.UserPasswordRequest;
import ecom.clothes.controller.request.UserUpdateRequest;
import ecom.clothes.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.Map;

@Controller
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Tag(name = "User Controller")
@Validated
public class UserController {

    private final UserService userService;

    @Operation(summary = "danh sách người dùng")
    @GetMapping("/list")
    public Map<String, Object> list(@RequestParam(required = false) String keyword,
                                    @RequestParam(required = false) String sort,
                                    @RequestParam(defaultValue = "0") int page,
                                    @RequestParam(defaultValue = "10") int size) {
        Map<String, Object> result = new LinkedHashMap<>();
        result.put("status", HttpStatus.OK.value());
        result.put("message", "List user");
        result.put("data", userService.getUsers(keyword, sort, page, size));
        return result;
    }

    @Operation(summary = "Thông tin người dùng")
    @GetMapping("/{userId}")
    public Map<String, Object> getUser(@PathVariable @Min(value = 1, message = "Id phải lớn hơn hoặc bằng 1") Long userId) {
        Map<String, Object> result = new LinkedHashMap<>();
        result.put("status", HttpStatus.OK.value());
        result.put("message", "User");
        result.put("data", userService.getUser(userId));
        return result;
    }

    @Operation(summary = "Thêm người dùng", description = "Nhập username và password để thêm người dùng mới")
    @PostMapping("/add")
    public ResponseEntity<Object> createUser(@Valid @RequestBody UserCreateRequest req) {
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("status", HttpStatus.CREATED.value());
        response.put("message", "Thêm người dùng mới thành công");
        response.put("data", userService.save(req));
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Operation(summary = "Sửa thông tin người dùng")
    @PutMapping("/upd")
    public ResponseEntity<Object> updateUser(@Valid @RequestBody UserUpdateRequest req) {
        Map<String, Object> response = new LinkedHashMap<>();
        userService.update(req);
        response.put("status", HttpStatus.ACCEPTED.value());
        response.put("message", "Sửa thông tin người dùng thành công");
        return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
    }

    @Operation(summary = "Đổi mật khẩu")
    @PatchMapping("/change-password")
    public ResponseEntity<Object> changePassword(@Valid @RequestBody UserPasswordRequest req) {
        Map<String, Object> response = new LinkedHashMap<>();
        userService.changePassword(req);
        response.put("status", HttpStatus.ACCEPTED.value());
        response.put("message", "Đổi mật khẩu thành công");
        return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
    }

    @Operation(summary = "Xóa thông tin người dùng")
    @DeleteMapping("/del")
    public  ResponseEntity<Object> deleteUser(@Min(value = 1, message = "Id phải lớn hơn hoặc bằng 1") @RequestBody Long id){
        Map<String, Object> response = new LinkedHashMap<>();
        userService.delete(id);
        response.put("status", HttpStatus.NO_CONTENT.value());
        response.put("message", "Xóa thông tin người dùng");
        return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
    }
}
