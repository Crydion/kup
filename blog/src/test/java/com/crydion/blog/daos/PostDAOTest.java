package com.crydion.blog.daos;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit4.SpringRunner;

import com.crydion.blog.entities.Post;

@SpringBootTest
@RunWith(SpringRunner.class)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
public class PostDAOTest {

	@Autowired
	private PostDAO postDAO;

	@Test
	public void getPostDoesNotExistsTest() {
		Integer id = -1;

		assertFalse(postDAO.findById(id).isPresent());
	}

	@Test
	public void getPostExistsTest() {
		Integer id = 1;

		Optional<Post> postOpt = postDAO.findById(id);

		assertTrue(postOpt.isPresent());
		Post post = postOpt.get();
		assertEquals("Crydion", post.getAuthor());
		assertEquals(353, post.getContent().length());
		assertEquals(1, post.getComments().size());
		assertEquals("Im lovin it", post.getComments().get(0).getContent());
	}

	@Test
	public void getAllPostSimplifiedTest() {
		List<Post> posts = postDAO.getAllPostsSimplified();

		assertEquals(2, posts.size());
		assertNull(posts.get(0).getContent());
	}

}
