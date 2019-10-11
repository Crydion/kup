package com.crydion.blog.mappers.impl;

import org.springframework.stereotype.Component;

import com.crydion.blog.dtos.UserDTO;
import com.crydion.blog.entities.User;
import com.crydion.blog.mappers.UserMapper;

@Component
public class UserMapperImpl implements UserMapper {

	@Override
	public UserDTO mapEntity(User entity) {
		return new UserDTO()
			.setId(entity.getId())
			.setUsername(entity.getUsername())
			.setPassword(entity.getPassword());
	}

	@Override
	public User mapDTO(UserDTO dto) {
		return new User()
			.setId(dto.getId())
			.setUsername(dto.getUsername())
			.setPassword(dto.getPassword());
	}

}
