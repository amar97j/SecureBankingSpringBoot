package com.nada.SpringBootMiniProject.controller;

import com.nada.SpringBootMiniProject.bo.user.Contact;
import com.nada.SpringBootMiniProject.bo.user.CreateUserRequest;
import com.nada.SpringBootMiniProject.bo.user.Fields;
import com.nada.SpringBootMiniProject.bo.user.UpdateUserStatusRequest;
import com.nada.SpringBootMiniProject.service.user.UserService;
import org.apache.catalina.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    private final UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;
    }


    @PutMapping("/updateYourStatus")
    public ResponseEntity<String> updateUserStatus(@RequestParam Long userId, @RequestBody UpdateUserStatusRequest updateUserStatusRequest){
        try {
            userService.updateUserStatus(userId, updateUserStatusRequest);
        }catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok("Status updated successfully");
    }


    //UPDATE USER
    @PutMapping("/update-profile")
    public ResponseEntity<String>updateProfile(@RequestBody User user){
        return ResponseEntity.ok("Profile Updated Successfully !");
    }

}

