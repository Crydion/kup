package com.crydion.blog.services;

import java.util.List;

import com.crydion.blog.dtos.CommentDTO;
import com.crydion.blog.dtos.PostDTO;

public interface PostService {

	List<PostDTO> getPosts();

	PostDTO getPost(Integer id);

	PostDTO savePost(PostDTO postDTO);

	PostDTO updatePost(Integer id, PostDTO postDTO);

	PostDTO addComment(Integer postId, CommentDTO commentDTO);

	PostDTO removePost(Integer postId);

}
