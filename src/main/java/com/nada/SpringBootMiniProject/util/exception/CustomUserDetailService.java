package com.nada.SpringBootMiniProject.util.exception;


import com.nada.SpringBootMiniProject.bo.customUserDetails.CustomUserDetails;
import com.nada.SpringBootMiniProject.entity.GuestEntity;
import com.nada.SpringBootMiniProject.repository.UserRepository;
import javassist.NotFoundException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public CustomUserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        try {
            return buildCustomUserDetailsofUsername(s);
        } catch (NotFoundException e){
            throw new RuntimeException();
        }
    }

    private CustomUserDetails buildCustomUserDetailsofUsername(String username) throws NotFoundException{
        GuestEntity user = userRepository.findByUsername(username)
                .orElseThrow();
        if(user == null){
            throw new NotFoundException("User not found");
        }
        CustomUserDetails userDetails = new CustomUserDetails();
        userDetails.setId(user.getId());
        userDetails.setUserName(user.getUsername());
        userDetails.setPassword(user.getPassword());
        userDetails.setAddress(user.getAddress());
        userDetails.setBalnce(user.getBalance());

        return userDetails;
    }
}
