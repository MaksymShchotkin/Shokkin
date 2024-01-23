package com.shokkin.Shokkin.Service;

import com.shokkin.Shokkin.DTO.UserDTO;
import com.shokkin.Shokkin.Entities.User;
import com.shokkin.Shokkin.Mapper.UserMapper;
import com.shokkin.Shokkin.Repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

@Service
@SpringBootApplication
@ComponentScan(basePackages = {"com.shokkin.Shokkin"})
public class UserService {
    private static final Logger logger = LogManager.getLogger(UserService.class);
    public final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, UserMapper userMapper){
        logger.info("Initialising User Mapper and Repository!");
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }
    //save or update existing user
    public UserDTO saveUser(UserDTO userDTO) {
        logger.info("Saving User DTO into DB!");
        User user = userMapper.toEntity(userDTO);
        user = userRepository.save(user);
        return userMapper.toDTO(user);
    }
    //check if user exists -> delete it
    public void deleteUser(Long userId) {
        logger.info("Checking for the user in DB and deleting it afterwards.");
        User existingUser = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        userRepository.delete(existingUser);
    }
    //check if user exists -> get it by id
    public UserDTO getUserById(Long userId) {
        logger.info("Checking if user exists and reading it.");
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        return userMapper.toDTO(user);
    }

    public boolean permitUserAccess(String email, String password){
        User user = userRepository.findByEmail(email);
        return user != null && user.getPassword().equals(password);
    }
}
