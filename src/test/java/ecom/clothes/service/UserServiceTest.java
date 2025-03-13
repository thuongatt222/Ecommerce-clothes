package ecom.clothes.service;

import ecom.clothes.controller.response.User.UserPageResponse;
import ecom.clothes.model.UserEntity;
import ecom.clothes.repositories.UserRepository;
import ecom.clothes.service.impl.UserServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    private UserService userService;
    private @Mock UserRepository userRepository;
    private @Mock PasswordEncoder passwordEncoder;

    private static UserEntity user1;
    private static UserEntity user2;

    @BeforeAll
    static void setUpBefore(){
        user1 = new UserEntity();
        user1.setId(1L);
        user1.setUsername("User1");
        user1.setPassword("password");

        user2 = new UserEntity();
        user2.setId(2L);
        user2.setUsername("User2");
        user2.setPassword("password");
    }

    @BeforeEach
    void setUp() {
        userService = new UserServiceImpl(userRepository, passwordEncoder);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getUsers_done() {
        Page<UserEntity> users = new PageImpl<>(Arrays.asList(user1, user2));
        when(userRepository.findAll(any(Pageable.class))).thenReturn(users);

        UserPageResponse result = userService.getUsers(null, null, 0, 20);

        assertNotNull(result);
        assertEquals(2, result.getTotalElements());
    }

    @Test
    void getUser() {
    }

    @Test
    void changePassword() {
    }

    @Test
    void save() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }
}