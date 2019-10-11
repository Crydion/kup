package com.crydion.blog.mappers;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.crydion.blog.dtos.UserDTO;
import com.crydion.blog.entities.User;
import com.crydion.blog.mappers.impl.UserMapperImpl;
import com.crydion.blog.testutils.UserTestUtils;

public class UserMapperTest {

	private UserMapper userMapper = new UserMapperImpl();

	@Test
	public void mapEntityTest() {
		User entity = UserTestUtils.generateRandomEntity();

		UserDTO dto = userMapper.mapEntity(entity);

		checkData(entity, dto);
	}

	@Test
	public void mapDTOTest() {
		UserDTO dto = UserTestUtils.generateRandomDTO();

		User entity = userMapper.mapDTO(dto);

		checkData(entity, dto);
	}

	private void checkData(User entity, UserDTO dto) {
		assertEquals(entity.getId(), dto.getId());
		assertEquals(entity.getUsername(), dto.getUsername());
		assertEquals(entity.getPassword(), dto.getPassword());
	}

}
