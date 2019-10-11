package com.crydion.blog.mappers;

import com.crydion.blog.dtos.UserDTO;
import com.crydion.blog.entities.User;

public interface UserMapper {

	UserDTO mapEntity(User entity);

	User mapDTO(UserDTO dto);

}
