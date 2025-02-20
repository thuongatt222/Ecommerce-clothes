package ecom.clothes.service;

import ecom.clothes.controller.request.UserCreateRequest;
import ecom.clothes.controller.request.UserPasswordRequest;
import ecom.clothes.controller.request.UserUpdateRequest;
import ecom.clothes.controller.response.UserPageResponse;
import ecom.clothes.controller.response.UserResponse;
import ecom.clothes.model.UserEntity;

import java.util.List;

public interface UserService {

    UserPageResponse getUsers(String keyword, String sort, int page, int size);

    UserResponse getUser(Long id);

    void changePassword(UserPasswordRequest request);

    Long save(UserCreateRequest request);

    void update(UserUpdateRequest request);

    void delete(Long id);
}
