package com.crydion.blog.mappers;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.crydion.blog.dtos.CommentDTO;
import com.crydion.blog.entities.Comment;
import com.crydion.blog.mappers.impl.CommentMapperImpl;
import com.crydion.blog.testutils.CommentTestUtils;

public class CommentMapperTest {

	private CommentMapper commentMapper = new CommentMapperImpl();

	@Test
	public void mapEntityWithoutCommentsTest() {
		Comment entity = CommentTestUtils.generateRandomEntity();

		CommentDTO dto = commentMapper.mapEntity(entity);

		checkData(entity, dto);
	}

	@Test
	public void mapEntityWithCommentsTest() {
		Comment entity = CommentTestUtils.generateRandomEntity();

		CommentDTO dto = commentMapper.mapEntity(entity);

		checkData(entity, dto);
	}

	private void checkData(Comment entity, CommentDTO dto) {
		assertEquals(entity.getId(), dto.getId());
		assertEquals(entity.getPublicationDate(), dto.getPublicationDate());
		assertEquals(entity.getAuthor(), dto.getAuthor());
		assertEquals(entity.getContent(), dto.getContent());
	}

}
