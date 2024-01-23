package com.shokkin.Shokkin;

import com.shokkin.Shokkin.DTO.UserDTO;
import com.shokkin.Shokkin.Service.UserService;
import jakarta.transaction.Transactional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional
public class TestUserSignInLogIn {

    @Autowired
    private UserService userService;
    private static final Logger logger = LogManager.getLogger(TestUserSignInLogIn.class);

    // Autowire other dependencies like UserRepository if needed...

    @DisplayName("Testing creation of user DTO, saving it and reading it from DB.")
    @Test
    public void userWithSteps() {
        logger.info("Beginning of testing User DTO.");
        UserDTO userToSave = createUser();
        UserDTO savedUser = saveUserToDB(userToSave);
        UserDTO retrievedUser = getUserById(savedUser.getId());
        checkLogIn(retrievedUser.getEmail(), retrievedUser.getPassword());
        assertMatchingUsers(retrievedUser);

    }
    @DisplayName("Create a DTO user")
    public UserDTO createUser() {
        logger.info("Testing creating a new DTO User.");
        UserDTO userToSave = new UserDTO();
        userToSave.setEmail("testUser@gmail.com");
        userToSave.setPassword("testpassword2023");
        userToSave.setName("Billy");
        userToSave.setSurname("Johnson");
        return userToSave;
    }

    @DisplayName("Save the user")
    public UserDTO saveUserToDB(UserDTO userToSave){
        logger.info("STesting saving created DTO to DB.");
        return userService.saveUser(userToSave);
    }

    @DisplayName("Get the user by ID from the DB.")
    public UserDTO getUserById(Long userId){
        logger.info("Testing reading User from the DB by the ID");
        return userService.getUserById(userId);
    }
    @DisplayName("Check the login system via email and password.")
    public void checkLogIn(String email, String password){
        logger.info("Checking for username and verifying password.");
        assertEquals(true, userService.permitUserAccess(email, password));
    }
    @DisplayName("Display that the saved user has correct parameters.")
    public void assertMatchingUsers(UserDTO retrievedUser){
        logger.info("Testing assertion that right parameters were saved to DB.");
        // Assert that the retrieved user matches the saved user
        assertEquals("testUser@gmail.com", retrievedUser.getEmail());
        assertEquals("testpassword2023", retrievedUser.getPassword());
        assertEquals("Billy", retrievedUser.getName());
        assertEquals("Johnson", retrievedUser.getSurname());
    }
}