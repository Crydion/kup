package com.crydion.blog.services.impl;

import static java.util.Collections.emptyList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.crydion.blog.daos.UserDAO;
import com.crydion.blog.dtos.UserDTO;
import com.crydion.blog.mappers.UserMapper;
import com.crydion.blog.services.UserService;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

	private UserDAO userDAO;

	private UserMapper userMapper;

	private BCryptPasswordEncoder bCryptPasswordEncoder;

	private static final Logger LOG = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	public UserServiceImpl(UserDAO userDAO, UserMapper userMapper, BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.userDAO = userDAO;
		this.userMapper = userMapper;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}

	@Override
	public UserDTO saveUser(UserDTO userDTO) {
		LOG.info("Saving user {}", userDTO.getUsername());
		userDTO.setPassword(bCryptPasswordEncoder.encode(userDTO.getPassword()));
		return userMapper.mapEntity(
				userDAO.save(userMapper.mapDTO(userDTO))
			);
	}

	@Override
	public UserDetails loadUserByUsername(String username) {
		return userDAO.findByUsername(username)
			.map(user -> new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), emptyList()))
			.orElseThrow(() -> new UsernameNotFoundException(username));
	}

}
