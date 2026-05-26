package com.sdepro2026.SDEPro0_12026.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sdepro2026.SDEPro0_12026.domain.User;
import com.sdepro2026.SDEPro0_12026.repositories.UserRepository;


@Service
public class UserService implements UserDetailsService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

   @Override
public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found: "+ username));
}


    @Transactional
    public void registerUser(String username,String email,String rawPassword){
        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(rawPassword));
        userRepository.save(user);
    } 

    @Transactional
    public void updateProfile(User user,String newEmail,String newRawPassword){
        user.setEmail(newEmail);
        if(newRawPassword != null && !newRawPassword.isBlank()){
            user.setPassword(passwordEncoder.encode(newRawPassword));
        }
        userRepository.save(user);
    }

    public boolean usernameExists(String username){
        return userRepository.findByUsername(username).isPresent();
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
    }

}
