package com.stefanj.simpleticketingapp.services.impl;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.access.AccessDeniedException;

import com.stefanj.simpleticketingapp.exceptions.NotFoundException;
import com.stefanj.simpleticketingapp.model.User;
import com.stefanj.simpleticketingapp.model.UserType;
import com.stefanj.simpleticketingapp.repositories.UserRepository;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {
	@Mock
    UserRepository userRepository;

    @InjectMocks
    UserServiceImpl userService;
    User admin = new User("admin", "password", "admin@app.com", UserType.ADMIN, "Admin Admin", true, null, null);


    @Test
    void getById_whenUserExists_thenReturnUser() {
        Mockito.when(userRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(admin));
        User fetchedUser = userService.getById(1L, "admin");
        Assertions.assertEquals(admin, fetchedUser);
    }

    @Test
    void getById_whenUserDoesNotExist_thenUserNotFoundException() {
        Mockito.when(userRepository.findById(Mockito.anyLong())).thenReturn(Optional.ofNullable(null));
        assertThrows(NotFoundException.class, () -> userService.getById(1L, "admin"));
    }

    @Test
    void getById_whenUserDoesNotFitToLoginUser_thenAccessDeniedException() {
        Mockito.when(userRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(admin));
        assertThrows(AccessDeniedException.class, () -> userService.getById(1L, "user"));
    }

    @Test
    void save_whenSavedUser_thenReturnUser() {
        Mockito.when(userRepository.save(Mockito.any())).thenReturn(admin);
        User fetchedUser = userService.save(admin);
        Assertions.assertEquals(admin, fetchedUser);
    }

    @Test
    void delete_whenUserExists_thenDeleteUser() {
        Mockito.when(userRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(admin));
        Assertions.assertDoesNotThrow(() -> userService.delete(1L));
    }

    @Test
    void delete_whenUserDoesNotExist_thenNotFoundException() {
        Mockito.when(userRepository.findById(Mockito.anyLong())).thenReturn(Optional.ofNullable(null));
        Assertions.assertThrows(NotFoundException.class, () -> userService.delete(1L));
    }

    @Test
    void update_whenUserExists_thenReturnUser() {
        Mockito.when(userRepository.existsById(Mockito.anyLong())).thenReturn(true);
        Mockito.when(userRepository.save(Mockito.any())).thenReturn(admin);
        User fetchedUser = userService.update(admin);
        Assertions.assertEquals(admin, fetchedUser);
    }

    @Test
    void update_whenUserDoesNotExist_thenNotFoundException() {
        Mockito.when(userRepository.existsById(Mockito.anyLong())).thenReturn(false);
        Assertions.assertThrows(NotFoundException.class, () -> userService.update(admin));
    }
}
