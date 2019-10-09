package com.crydion.blog.services;

import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.crydion.blog.daos.PostDAO;
import com.crydion.blog.dtos.CommentDTO;
import com.crydion.blog.dtos.PostDTO;
import com.crydion.blog.exceptions.PostNotFoundException;
import com.crydion.blog.mappers.CommentMapper;
import com.crydion.blog.mappers.PostMapper;
import com.crydion.blog.services.impl.PostServiceImpl;
import com.crydion.blog.testutils.CommentTestUtils;
import com.crydion.blog.testutils.PostTestUtils;

@RunWith(MockitoJUnitRunner.class)
public class PostServiceTest {

	@Mock
	private PostMapper postMapper;

	@Mock
	private PostDAO postDAO;

	@Mock
	private CommentMapper commentMapper;

	private PostService postService;

	@Before
	public void setUp() {
		postService = new PostServiceImpl(postDAO, postMapper, commentMapper);

		when(commentMapper.mapDTO(any())).thenReturn(CommentTestUtils.generateRandomEntity());
		when(postMapper.mapEntity(any())).thenReturn(PostTestUtils.generateRandomDTO());
		when(postMapper.mapDTO(any())).thenReturn(PostTestUtils.generateRandomEntity());
		when(postDAO.save(any())).thenReturn(PostTestUtils.generateRandomEntity());
		when(postDAO.findById(-1)).thenReturn(Optional.empty());
	}

	@Test
	public void getPostsTest() {
		when(postDAO.getAllPostsSimplified()).thenReturn(PostTestUtils.generateRandomEntityList(3));

		assertNotNull(postService.getPosts());
	}

	@Test(expected = PostNotFoundException.class)
	public void getPostDoesNotExistsTest() {
		Integer id = -1;

		postService.getPost(id);
	}

	@Test
	public void getPostExistsTest() {
		Integer id = 1;

		when(postDAO.findById(id)).thenReturn(Optional.of(PostTestUtils.generateRandomEntity()));

		assertNotNull(postService.getPost(id));
	}

	@Test
	public void savePostTest() {
		PostDTO dto = PostTestUtils.generateRandomDTO();
		dto.setId(null);

		assertNotNull(postService.savePost(dto));
	}

	@Test(expected = PostNotFoundException.class)
	public void updatePostDoesNotExistsTest() {
		PostDTO dto = PostTestUtils.generateRandomDTO();
		dto.setId(null);
		Integer id = -1;

		postService.updatePost(id, dto);
	}

	@Test
	public void updatePostExistTest() {
		PostDTO dto = PostTestUtils.generateRandomDTO();
		dto.setId(null);
		Integer id = 1;

		when(postDAO.findById(id)).thenReturn(Optional.of(PostTestUtils.generateRandomEntity()));

		assertNotNull(postService.updatePost(id, dto));
	}

	@Test(expected = PostNotFoundException.class)
	public void addCommentOnPostThatNotExistsTest() {
		Integer id = -1;
		CommentDTO dto = CommentTestUtils.generateRandomDTO();

		postService.addComment(id, dto);
	}

	@Test
	public void addCommentTest() {
		Integer id = 1;
		CommentDTO dto = CommentTestUtils.generateRandomDTO();

		when(postDAO.findById(id)).thenReturn(Optional.of(PostTestUtils.generateRandomEntity()));

		assertNotNull(postService.addComment(id, dto));
	}

}
