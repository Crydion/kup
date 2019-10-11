package com.crydion.blog.services;

import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.UUID;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.crydion.blog.daos.UserDAO;
import com.crydion.blog.dtos.UserDTO;
import com.crydion.blog.mappers.UserMapper;
import com.crydion.blog.services.impl.UserServiceImpl;
import com.crydion.blog.testutils.UserTestUtils;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

	@Mock
	private UserDAO userDAO;

	@Mock
	private UserMapper userMapper;

	@Mock
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	private UserService userService;

	@Before
	public void setUp() {
		userService = new UserServiceImpl(userDAO, userMapper, bCryptPasswordEncoder);
	}

	@Test
	public void saveUserTest() {
		UserDTO userDTO = Mockito.mock(UserDTO.class);

		when(bCryptPasswordEncoder.encode(any())).thenReturn(UUID.randomUUID().toString());
		when(userMapper.mapDTO(any())).thenReturn(UserTestUtils.generateRandomEntity());
		when(userDAO.save(any())).thenReturn(UserTestUtils.generateRandomEntity());
		when(userMapper.mapEntity(any())).thenReturn(UserTestUtils.generateRandomDTO());

		assertNotNull(userService.saveUser(userDTO));

		verify(userDTO, times(1)).getPassword();
		verify(userDTO, times(1)).setPassword(any());
	}

}
