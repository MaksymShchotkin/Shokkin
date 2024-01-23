package com.shokkin.Shokkin.Repository;

import com.shokkin.Shokkin.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email); // Example: Find user by username
}
