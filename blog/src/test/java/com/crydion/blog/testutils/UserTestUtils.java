package com.crydion.blog.testutils;

import java.util.Random;
import java.util.UUID;

import com.crydion.blog.dtos.UserDTO;
import com.crydion.blog.entities.User;

public class UserTestUtils {

	public static User generateRandomEntity() {
		Random random = new Random();
		return new User()
			.setId(random.nextInt(100))
			.setUsername(UUID.randomUUID().toString())
			.setPassword(UUID.randomUUID().toString());
	}

	public static UserDTO generateRandomDTO() {
		Random random = new Random();
		return new UserDTO()
			.setId(random.nextInt(100))
			.setUsername(UUID.randomUUID().toString())
			.setPassword(UUID.randomUUID().toString());
	}

}
