package ecom.clothes.service;

import ecom.clothes.controller.request.User.UserCreateRequest;
import ecom.clothes.controller.request.User.UserPasswordRequest;
import ecom.clothes.controller.request.User.UserUpdateRequest;
import ecom.clothes.controller.response.User.UserPageResponse;
import ecom.clothes.controller.response.User.UserResponse;

public interface UserService {

    UserPageResponse getUsers(String keyword, String sort, int page, int size);

    UserResponse getUser(Long id);

    void changePassword(UserPasswordRequest request);

    Long save(UserCreateRequest request);

    void update(UserUpdateRequest request);

    void delete(Long id);
}
