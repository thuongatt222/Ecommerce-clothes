package ecom.clothes.service;

import ecom.clothes.controller.request.UserCreateRequest;
import ecom.clothes.controller.request.UserPasswordRequest;
import ecom.clothes.controller.request.UserUpdateRequest;
import ecom.clothes.model.UserEntity;

import java.util.List;

public interface UserService {

    List<UserEntity> getUsers();

    UserEntity getUser(Long id);

    void changePassword(UserPasswordRequest request);

    Long save(UserCreateRequest request);

    void update(UserUpdateRequest request);

    void delete(Long id);
}
