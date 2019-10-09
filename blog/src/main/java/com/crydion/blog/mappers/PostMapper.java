package com.crydion.blog.mappers;

import com.crydion.blog.dtos.PostDTO;
import com.crydion.blog.entities.Post;

public interface PostMapper {

	PostDTO mapEntity(Post entity);

	Post mapDTO(PostDTO dto);

}
