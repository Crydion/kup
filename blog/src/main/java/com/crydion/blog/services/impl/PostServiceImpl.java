package com.crydion.blog.services.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crydion.blog.daos.PostDAO;
import com.crydion.blog.dtos.CommentDTO;
import com.crydion.blog.dtos.PostDTO;
import com.crydion.blog.entities.Post;
import com.crydion.blog.exceptions.PostNotFoundException;
import com.crydion.blog.mappers.CommentMapper;
import com.crydion.blog.mappers.PostMapper;
import com.crydion.blog.services.PostService;

@Service
public class PostServiceImpl implements PostService {

	private PostDAO postDAO;

	private PostMapper postMapper;

	private CommentMapper commentMapper;

	@Autowired
	public PostServiceImpl(PostDAO postDAO, PostMapper postMapper, CommentMapper commentMapper) {
		super();
		this.postDAO = postDAO;
		this.postMapper = postMapper;
		this.commentMapper = commentMapper;
	}

	@Override
	public List<PostDTO> getPosts() {
		return postDAO.getAllPostsSimplified().stream()
				.map(postMapper::mapEntity)
				.collect(Collectors.toList());
	}

	@Override
	public PostDTO getPost(Integer id) {
		return postDAO.findById(id)
				.map(postMapper::mapEntity)
				.orElseThrow(() -> new PostNotFoundException("Post not found: "+id));
	}

	@Override
	public PostDTO savePost(PostDTO postDTO) {
		postDTO.setId(null);
		postDTO.setPublicationDate(LocalDate.now());

		return postMapper.mapEntity(postDAO.save(postMapper.mapDTO(postDTO)));
	}

	@Override
	public PostDTO updatePost(Integer id, PostDTO postDTO) {
		Post post = getEntity(id);

		postDTO.setId(id)
			.setPublicationDate(post.getPublicationDate())
			.setLastModified(LocalDate.now());
		return postMapper.mapEntity(postDAO.save(postMapper.mapDTO(postDTO)));
	}

	@Override
	public PostDTO addComment(Integer postId, CommentDTO commentDTO) {
		commentDTO.setPublicationDate(LocalDate.now());
		return postMapper.mapEntity(
			postDAO.save(
				getEntity(postId)
					.addComment(commentMapper.mapDTO(commentDTO.setId(null)))
			)
		);
	}

	@Override
	public PostDTO removePost(Integer id) {
		Post post = getEntity(id);

		postDAO.delete(post);

		return postMapper.mapEntity(post);
	}

	private Post getEntity(Integer id) {
		return postDAO.findById(id)
			.orElseThrow(() -> new PostNotFoundException("Post not found: "+id));
	}

}
