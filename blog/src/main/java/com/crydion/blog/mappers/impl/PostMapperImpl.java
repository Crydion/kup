package com.crydion.blog.mappers.impl;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.crydion.blog.dtos.PostDTO;
import com.crydion.blog.entities.Post;
import com.crydion.blog.mappers.CommentMapper;
import com.crydion.blog.mappers.PostMapper;

@Component
public class PostMapperImpl implements PostMapper {

	private CommentMapper commentMapper;

	@Autowired
	public PostMapperImpl(CommentMapper commentMapper) {
		super();
		this.commentMapper = commentMapper;
	}

	@Override
	public PostDTO mapEntity(Post entity) {
		PostDTO dto = new PostDTO()
				.setId(entity.getId())
				.setPublicationDate(entity.getPublicationDate())
				.setLastModified(entity.getLastModified())
				.setAuthor(entity.getAuthor())
				.setTitle(entity.getTitle())
				.setContent(entity.getContent());
		if(entity.getComments()!=null) {
			dto.setComments(
				entity.getComments().stream()
					.map(commentMapper::mapEntity)
					.collect(Collectors.toList())
			);
		}
		return dto;
	}

	@Override
	public Post mapDTO(PostDTO dto) {
		Post entity = new Post()
				.setId(dto.getId())
				.setPublicationDate(dto.getPublicationDate())
				.setLastModified(dto.getLastModified())
				.setAuthor(dto.getAuthor())
				.setTitle(dto.getTitle())
				.setContent(dto.getContent());
		if(dto.getComments()!=null && !dto.getComments().isEmpty()) {
			entity.setComments(
				dto.getComments().stream()
					.map(commentMapper::mapDTO)
					.collect(Collectors.toList())
			);
		}
		return entity;
	}

}
