package com.crydion.blog.mappers.impl;

import org.springframework.stereotype.Component;

import com.crydion.blog.dtos.CommentDTO;
import com.crydion.blog.entities.Comment;
import com.crydion.blog.mappers.CommentMapper;

@Component
public class CommentMapperImpl implements CommentMapper{

	@Override
	public CommentDTO mapEntity(Comment entity) {
		return new CommentDTO()
				.setId(entity.getId())
				.setPublicationDate(entity.getPublicationDate())
				.setAuthor(entity.getAuthor())
				.setContent(entity.getContent());
	}

	@Override
	public Comment mapDTO(CommentDTO dto) {
		return new Comment()
				.setId(dto.getId())
				.setPublicationDate(dto.getPublicationDate())
				.setAuthor(dto.getAuthor())
				.setContent(dto.getContent());
	}

}
