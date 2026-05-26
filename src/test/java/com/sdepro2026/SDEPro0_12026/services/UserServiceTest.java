package com.sdepro2026.SDEPro0_12026.services;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.anyString;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.sdepro2026.SDEPro0_12026.domain.User;
import com.sdepro2026.SDEPro0_12026.repositories.UserRepository;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private BCryptPasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService userService;

    @Test
    public void testRegisterUser_savesEncodedPassword() {
        when(passwordEncoder.encode("rawpass")).thenReturn("encodedpass");

        userService.registerUser("john", "john@email.com", "rawpass");

        verify(userRepository, times(1)).save(Mockito.any(User.class));
    }

    @Test
    public void testUsernameExists_returnsTrue() {
        when(userRepository.findByUsername("john")).thenReturn(Optional.of(new User()));

        assertTrue(userService.usernameExists("john"));
    }

    @Test
    public void testUsernameExists_returnsFalse() {
        when(userRepository.findByUsername("ghost")).thenReturn(Optional.empty());

        assertFalse(userService.usernameExists("ghost"));
    }

    @Test
    public void testLoadUserByUsername_returnsUser() {
        User user = new User();
        user.setUsername("john");
        when(userRepository.findByUsername("john")).thenReturn(Optional.of(user));

        assertEquals("john", userService.loadUserByUsername("john").getUsername());
    }

    @Test
    void testUpdateProfile_encodesNewPassword() {
        
        User user = new User();
        user.setId(1L);
        user.setEmail("old@example.com");

        String newEmail = "new@example.com";
        String newRawPassword = "newPassword123";
        String encodedPassword = "encodedNewPassword";

        when(passwordEncoder.encode(newRawPassword)).thenReturn(encodedPassword);

        
        userService.updateProfile(user, newEmail, newRawPassword);

    
        assertEquals(newEmail, user.getEmail());
        assertEquals(encodedPassword, user.getPassword());
        verify(userRepository).save(user);
        verify(passwordEncoder).encode(newRawPassword);
    }

    @Test
    public void testUpdateProfile_skipsEncodingWhenPasswordBlank() {
        User user = new User();
        user.setId(1L);
        user.setPassword("oldencoded");

        userService.updateProfile(user, "new@email.com", "");

        assertEquals("oldencoded", user.getPassword());
        verify(passwordEncoder, never()).encode(anyString());
    }
}
