package com.crydion.blog.daos;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit4.SpringRunner;

import com.crydion.blog.entities.User;

@SpringBootTest
@RunWith(SpringRunner.class)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
public class UserDAOTest {

	@Autowired
	private UserDAO userDAO;

	@Test
	public void getUserDoesNotExistsTest() {
		Integer id = -1;

		assertFalse(userDAO.findById(id).isPresent());
	}

	@Test
	public void getUserExistsTest() {
		Integer id = 1;

		assertTrue(userDAO.findById(id).isPresent());

	}

	@Test
	public void findUserByNameTest() {
		String name = "Crydion";

		User user = userDAO.findByUsername(name).get();

		assertEquals(Integer.valueOf(1), user.getId());
	}
}
