package com.crydion.blog.mappers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.crydion.blog.dtos.PostDTO;
import com.crydion.blog.entities.Post;
import com.crydion.blog.mappers.impl.PostMapperImpl;
import com.crydion.blog.testutils.CommentTestUtils;
import com.crydion.blog.testutils.PostTestUtils;

@RunWith(MockitoJUnitRunner.class)
public class PostMapperTest {

	@Mock
	private CommentMapper commentMapper;

	private PostMapper postMapper;

	@Before
	public void setUp() {
		postMapper = new PostMapperImpl(commentMapper);
		when(commentMapper.mapEntity(any())).thenReturn(CommentTestUtils.generateRandomDTO());
	}

	@Test
	public void mapEntitySimplifiedTest() {
		Post entity = PostTestUtils.generateRandomEntity();
		entity.setContent(null);
		entity.setComments(null);

		PostDTO dto = postMapper.mapEntity(entity);

		checkData(entity, dto);
	}

	@Test
	public void mapEntityTest() {
		Post entity = PostTestUtils.generateRandomEntity();

		PostDTO dto = postMapper.mapEntity(entity);

		checkData(entity, dto);
	}

	@Test
	public void mapDTOTest() {
		PostDTO dto = PostTestUtils.generateRandomDTO();

		Post entity = postMapper.mapDTO(dto);

		checkData(entity, dto);
	}

	private void checkData(Post entity, PostDTO dto) {
		assertEquals(entity.getId(), dto.getId());
		assertEquals(entity.getPublicationDate(), dto.getPublicationDate());
		assertEquals(entity.getAuthor(), dto.getAuthor());
		assertEquals(entity.getTitle(), dto.getTitle());
		assertEquals(entity.getContent(), dto.getContent());
		if(entity.getComments()!=null) {
			assertEquals(entity.getComments().size(), dto.getComments().size());
		} else {
			assertNull(dto.getComments());
		}

	}

}
