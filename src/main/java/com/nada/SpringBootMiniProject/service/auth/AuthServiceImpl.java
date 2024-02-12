package com.nada.SpringBootMiniProject.service.auth;


import com.nada.SpringBootMiniProject.bo.user.CreateUpdateRequest;
import com.nada.SpringBootMiniProject.bo.auth.AuthenticationResponse;
import com.nada.SpringBootMiniProject.bo.auth.CreateLoginRequest;
import com.nada.SpringBootMiniProject.bo.auth.CreateSignupRequest;
import com.nada.SpringBootMiniProject.bo.auth.LogoutResponse;
import com.nada.SpringBootMiniProject.bo.customUserDetails.CustomUserDetails;
import com.nada.SpringBootMiniProject.config.JWTUtil;
import com.nada.SpringBootMiniProject.entity.RoleEntity;
import com.nada.SpringBootMiniProject.entity.GuestEntity;
import com.nada.SpringBootMiniProject.enums.Status;
import com.nada.SpringBootMiniProject.repository.RoleRepository;
import com.nada.SpringBootMiniProject.repository.UserRepository;
import com.nada.SpringBootMiniProject.util.enums.Roles;
import com.nada.SpringBootMiniProject.util.exception.CustomUserDetailService;
import com.nada.SpringBootMiniProject.util.exception.UserNotFoundException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
@Service
public class AuthServiceImpl implements AuthService{

    private final AuthenticationManager authenticationManager;

    private final CustomUserDetailService userDetailService;

    private final JWTUtil jwtUtil;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private final RoleRepository roleRepository;

    private final UserRepository userRepository;

    public AuthServiceImpl(AuthenticationManager authenticationManager, CustomUserDetailService userDetailService,
                           JWTUtil jwtUtil, BCryptPasswordEncoder bCryptPasswordEncoder, RoleRepository roleRepository,
                           UserRepository userRepository) {
        this.authenticationManager = authenticationManager;
        this.userDetailService = userDetailService;
        this.jwtUtil = jwtUtil;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
    }


    @Override
    public void signup(CreateSignupRequest createSignupRequest) {
        RoleEntity roleEntity = roleRepository.findRoleEntityByTitle(Roles.user.name())
                .orElseThrow(() -> new BodyGuardException("no Roles Found"));
        GuestEntity user = new GuestEntity();
        user.setName(createSignupRequest.getName());
        user.setPhoneNumber(createSignupRequest.getPhoneNumber());
        user.setEmail(createSignupRequest.getEmail());
        user.setStatus(Status.ACTIVE);
        user.setRoles(roleEntity);
        user.setAddress(createSignupRequest.getAddress());
        user.setUsername(createSignupRequest.getUsername());
        user.setPassword(bCryptPasswordEncoder.encode(createSignupRequest.getPassword()));
        userRepository.save(user);
    }

    @Override
    public void logout(LogoutResponse logoutResponse) {
        requiredNonNull(logoutResponse.getToken(),"token");
    }

    @Override
    public void updateProfile(CreateUpdateRequest createUpdateRequest) {
       GuestEntity guestEntity=new GuestEntity();

    }


    private void requiredNonNull(Object obj, String name) {
        if (obj == null || obj.toString().isEmpty()){
            throw new BodyGuardException(name + " can't be empty");
        }
    }

    @Override
    public AuthenticationResponse login(CreateLoginRequest createLoginRequest) {
        requiredNonNull(createLoginRequest.getUsername(), "username");
        requiredNonNull(createLoginRequest.getPassword(), "password");

        String username = createLoginRequest.getUsername().toLowerCase();
        String password = createLoginRequest.getPassword();
        authentication(username, password);

        CustomUserDetails userDetails = userDetailService.loadUserByUsername(username);
        String accessToken = jwtUtil.generateToken(userDetails);

        AuthenticationResponse response = new AuthenticationResponse();
        response.setId(userDetails.getId());
        response.setUsername(userDetails.getUsername());
        response.setRole(userDetails.getRole());
        response.setToken("Bearer " + accessToken);

        return response;
    }



    private void authentication(String username, String password){
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

        }catch (BodyGuardException e){
            throw new BodyGuardException("Incorrect password");
        }catch (AuthenticationServiceException e){
            throw new UserNotFoundException("Incorrect username");
        }
    }

}
