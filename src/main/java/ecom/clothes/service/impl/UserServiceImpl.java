package ecom.clothes.service.impl;

import ecom.clothes.common.UserStatus;
import ecom.clothes.common.UserType;
import ecom.clothes.controller.request.UserCreateRequest;
import ecom.clothes.controller.request.UserPasswordRequest;
import ecom.clothes.controller.request.UserUpdateRequest;
import ecom.clothes.controller.response.UserPageResponse;
import ecom.clothes.controller.response.UserResponse;
import ecom.clothes.exception.ResourceNotFoundException;
import ecom.clothes.model.UserEntity;
import ecom.clothes.repositories.UserRepository;
import ecom.clothes.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@Slf4j(topic = "USERSERVICEIMPL")
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserPageResponse getUsers(String keyword, String sort, int page, int size) {
        Sort.Order order = new Sort.Order(Sort.Direction.ASC, "id");
        if (StringUtils.hasLength(sort)){
            Pattern pattern = Pattern.compile("(\\w+?)(:)(.*)");
            Matcher matcher = pattern.matcher(sort);
            if (matcher.find()){
                String columnName = matcher.group(1);
                if (matcher.group(3).equalsIgnoreCase("asc")){
                    order = new Sort.Order(Sort.Direction.ASC, columnName);
                }else {
                    order = new Sort.Order(Sort.Direction.DESC, columnName);
                }
            }
        }

        int pageNo = 0;
        if (page > 0){
            pageNo = page - 1;
        }

        Pageable pageable = PageRequest.of(pageNo, size, Sort.by(order));

        Page<UserEntity> userSearchList = null;

        if(StringUtils.hasLength(keyword)){
            keyword = "%" + keyword + "%";
            userSearchList = userRepository.searchByKeyword(keyword, pageable);
        }else {
            userSearchList = userRepository.findAll(pageable);
        }

        return getUserPageResponse(page, size, userSearchList);
    }

    private UserPageResponse getUserPageResponse(int page, int size, Page<UserEntity> userSearchList) {
        List<UserResponse> userResponseList = userSearchList.stream().map(userEntity -> UserResponse.builder()
                .id(userEntity.getId())
                .firstName(userEntity.getFirstName())
                .lastName(userEntity.getLastName())
                .email(userEntity.getEmail())
                .phone(userEntity.getPhone())
                .avatar(userEntity.getAvatar())
                .gender(userEntity.getGender())
                .userType(userEntity.getType())
                .userStatus(userEntity.getStatus())
                .birthday(userEntity.getBirthday())
                .build()
        ).toList();

        UserPageResponse userPageResponse = new UserPageResponse();
        userPageResponse.setPage(page);
        userPageResponse.setTotalElements(userSearchList.getTotalElements());
        userPageResponse.setTotalPages(userSearchList.getTotalPages());
        userPageResponse.setSize(size);
        userPageResponse.setUsers(userResponseList);
        return userPageResponse;
    }

    @Override
    public UserResponse getUser(Long id) {

        UserEntity userEntity = findUserEntity(id);

        return UserResponse.builder()
                .id(userEntity.getId())
                .firstName(userEntity.getFirstName())
                .lastName(userEntity.getLastName())
                .email(userEntity.getEmail())
                .phone(userEntity.getPhone())
                .avatar(userEntity.getAvatar())
                .gender(userEntity.getGender())
                .userType(userEntity.getType())
                .userStatus(userEntity.getStatus())
                .birthday(userEntity.getBirthday())
                .build();
    }

    @Override
    public void changePassword(UserPasswordRequest request) {
        UserEntity user = userRepository.getReferenceById(request.getId());
        if (request.getPassword().equals(request.getConfirmPassword())) {
            user.setPassword(passwordEncoder.encode(request.getPassword()));
            userRepository.save(user);
        }
    }

    @Override
    public Long save(UserCreateRequest request) {
        UserEntity user = new UserEntity();
        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());
        user.setStatus(UserStatus.ACTIVE);
        user.setType(UserType.USER);
        userRepository.save(user);
        return user.getId();
    }

    @Override
    public void update(UserUpdateRequest request) {
        UserEntity user = userRepository.getReferenceById(request.getId());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setPhone(request.getPhone());
        user.setAvatar(request.getAvatar());
        user.setBirthday(request.getBirthday());
        user.setType(request.getUserType());
        user.setStatus(request.getUserStatus());
        user.setGender(request.getGender());
        userRepository.save(user);
    }

    @Override
    public void delete(Long id) {
        UserEntity user = userRepository.getReferenceById(id);
        user.setStatus(UserStatus.INACTIVE);
        userRepository.save(user);
    }

    private UserEntity findUserEntity(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }
}
