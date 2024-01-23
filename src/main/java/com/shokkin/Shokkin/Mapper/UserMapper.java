package com.shokkin.Shokkin.Mapper;

import com.shokkin.Shokkin.DTO.UserDTO;
import com.shokkin.Shokkin.Entities.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    //map out methods for entity conversion
    public UserDTO toDTO(User user) {
        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setEmail(user.getEmail());
        dto.setPassword(user.getPassword());
        dto.setName(user.getName());
        dto.setSurname(user.getSurname());
        return dto;
    }

    public User toEntity(UserDTO dto) {
        User user = new User();
        user.setId(dto.getId());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setName(dto.getName());
        user.setSurname(dto.getSurname());
        return user;
    }
}
