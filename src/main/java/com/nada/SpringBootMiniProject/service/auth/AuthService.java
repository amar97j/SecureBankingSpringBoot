package com.nada.SpringBootMiniProject.service.auth;

import com.nada.SpringBootMiniProject.bo.auth.AuthenticationResponse;
import com.nada.SpringBootMiniProject.bo.auth.CreateLoginRequest;
import com.nada.SpringBootMiniProject.bo.auth.CreateSignupRequest;
import com.nada.SpringBootMiniProject.bo.auth.LogoutResponse;
import com.nada.SpringBootMiniProject.bo.user.CreateUpdateRequest;
import com.nada.SpringBootMiniProject.bo.user.CreateUserRequest;

public interface AuthService {

    void signup(CreateSignupRequest createSignupRequest);

    AuthenticationResponse login(CreateLoginRequest authenticationRequest);

    void logout(LogoutResponse logoutResponse);

    void updateProfile(CreateUpdateRequest createUpdateRequest);
}