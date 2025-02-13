package ecom.clothes.service.impl;

import ecom.clothes.common.UserStatus;
import ecom.clothes.common.UserType;
import ecom.clothes.controller.request.UserCreateRequest;
import ecom.clothes.controller.request.UserPasswordRequest;
import ecom.clothes.controller.request.UserUpdateRequest;
import ecom.clothes.model.UserEntity;
import ecom.clothes.repositories.UserRepository;
import ecom.clothes.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j(topic = "USERSERVICEIMPL")
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

//    private final UserRepository userRepository;
//    private final PasswordEncoder passwordEncoder;

    @Override
    public List<UserEntity> getUsers() {
        return List.of();
    }

    @Override
    public UserEntity getUser(Long id) {
        return null;
    }

//    @Override
//    public void changePassword(UserPasswordRequest request) {
//        UserEntity user = userRepository.getReferenceById(request.getId());
//        if (request.getPassword().equals(request.getConfirmPassword())) {
//            user.setPassword(passwordEncoder.encode(request.getPassword()));
//            userRepository.save(user);
//        }
//    }
//
//    @Override
//    public Long save(UserCreateRequest request) {
//        UserEntity user = new UserEntity();
//        user.setUsername(request.getUsername());
//        user.setPassword(request.getPassword());
//        user.setStatus(UserStatus.ACTIVE);
//        user.setType(UserType.USER);
//        userRepository.save(user);
//        return user.getId();
//    }
//
//    @Override
//    public void update(UserUpdateRequest request) {
//        UserEntity user = userRepository.getReferenceById(request.getId());
//        user.setFirstName(request.getFirstName());
//        user.setLastName(request.getLastName());
//        user.setEmail(request.getEmail());
//        user.setPhone(request.getPhone());
//        user.setAvatar(request.getAvatar());
//        user.setBirthday(request.getBirthday());
//        user.setType(request.getUserType());
//        user.setStatus(request.getUserStatus());
//        user.setGender(request.getGender());
//        userRepository.save(user);
//    }
//
//    @Override
//    public void delete(Long id) {
//        UserEntity user = userRepository.getReferenceById(id);
//        user.setStatus(UserStatus.INACTIVE);
//        userRepository.save(user);
//    }
}
