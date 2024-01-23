package com.shokkin.Shokkin.Controller;

import com.shokkin.Shokkin.Service.EventService;
import com.shokkin.Shokkin.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
    @PostMapping("/login")
    public String login(@RequestBody String email, String password) {
        boolean isValidCredentials = userService.permitUserAccess(email, password);

        if (isValidCredentials) {
            return "Login successful!";
        } else {
            return "Invalid username or password!";
        }
    }

    // Define endpoints using HTTP methods to perform operations
}