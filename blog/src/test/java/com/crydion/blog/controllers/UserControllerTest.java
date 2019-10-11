package com.crydion.blog.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.crydion.blog.dtos.UserDTO;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@DirtiesContext(classMode = ClassMode.AFTER_CLASS)
public class UserControllerTest {

	@Autowired
	private MockMvc mockMvc;

	private ObjectMapper jsonObjectMapper = new ObjectMapper();

	@Test
	public void saveUserTest() throws Exception {
		UserDTO userDTO = new UserDTO()
				.setUsername("yo")
				.setPassword("yoyo");

		ResultActions result = mockMvc.perform(post("/users")
				.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE).content(jsonObjectMapper.writeValueAsBytes(userDTO)));

		result.andExpect(status().isCreated())
			.andExpect(jsonPath("$.id").value(2))
			.andExpect(jsonPath("$.username").value("yo"))
			.andExpect(jsonPath("$.password").isNotEmpty());
	}

}
