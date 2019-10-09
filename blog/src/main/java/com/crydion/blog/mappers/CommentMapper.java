package com.crydion.blog.mappers;

import com.crydion.blog.dtos.CommentDTO;
import com.crydion.blog.entities.Comment;

public interface CommentMapper {

	CommentDTO mapEntity(Comment entity);

	Comment mapDTO(CommentDTO dto);

}
