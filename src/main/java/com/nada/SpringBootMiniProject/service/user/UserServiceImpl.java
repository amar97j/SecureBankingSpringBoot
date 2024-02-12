package com.nada.SpringBootMiniProject.service.user;

import com.nada.SpringBootMiniProject.bo.user.CreateUserRequest;
//import com.nada.SpringBootMiniProject.bo.user.UpdateUserStatusRequest;
import com.nada.SpringBootMiniProject.bo.user.UpdateUserStatusRequest;
import com.nada.SpringBootMiniProject.entity.GuestEntity;
import com.nada.SpringBootMiniProject.enums.Status;
import com.nada.SpringBootMiniProject.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {


    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void saveUser(CreateUserRequest createUserRequest) {
        GuestEntity userEntity = new GuestEntity();
        userEntity.setName(createUserRequest.getName());
        userEntity.setEmail(createUserRequest.getEmail());
        userEntity.setPhoneNumber(createUserRequest.getPhone());
        userEntity.setStatus(Status.valueOf(createUserRequest.getStatus()));
        userEntity.setAddress(createUserRequest.getAddress());
        userEntity.setPassword(createUserRequest.getPassword());
        userEntity.setBalance(createUserRequest.getBalance());
        userRepository.save(userEntity);
    }

    @Override
    public void deleteUser(Long userId) {
        GuestEntity guestEntity=userRepository.findById(userId)
                        .orElseThrow();
        userRepository.delete(guestEntity);
    }

    @Override
    public void updateUserStatus(Long userId, UpdateUserStatusRequest updateUserStatusRequest) {
        GuestEntity userEntity = userRepository.findById(userId)
                .orElseThrow();
        if (!updateUserStatusRequest.getStatus().equals("ACTIVE") &&
                !updateUserStatusRequest.getStatus().equals("INACTIVE")) {
            throw new IllegalArgumentException("Invalid Status. Should be either Active or InActive");
        }
        userEntity.setStatus(Status.valueOf(updateUserStatusRequest.getStatus()));
        userRepository.save(userEntity);
    }


    public List<String> getAllUsersWithStrongPassword() {
        return userRepository.findAll()
                .stream().filter(e -> e.getPassword().length() > 8)
                .map(GuestEntity::getName)
                .collect(Collectors.toList());
    }
}
