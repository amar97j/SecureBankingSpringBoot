package com.nada.SpringBootMiniProject.service.user;

import com.nada.SpringBootMiniProject.bo.user.CreateUserRequest;
import com.nada.SpringBootMiniProject.bo.user.UpdateUserStatusRequest;
import com.nada.SpringBootMiniProject.repository.RoleRepository;
import com.nada.SpringBootMiniProject.repository.UserRepository;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public interface UserService {


    void saveUser(CreateUserRequest createUserRequest);

    void deleteUser(Long userId);

    //void updateStatus(Long userId, String newStatus);

    void updateUserStatus(Long userId, UpdateUserStatusRequest updateUserRequest);

    List<String> getAllUsersWithStrongPassword();


}


