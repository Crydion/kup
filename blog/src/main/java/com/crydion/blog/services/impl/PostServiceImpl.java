package com.crydion.blog.services.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crydion.blog.clients.AnalyzerClient;
import com.crydion.blog.daos.PostDAO;
import com.crydion.blog.dtos.CommentDTO;
import com.crydion.blog.dtos.ContentDTO;
import com.crydion.blog.dtos.PostDTO;
import com.crydion.blog.entities.Post;
import com.crydion.blog.exceptions.PostNotFoundException;
import com.crydion.blog.exceptions.SwearwordException;
import com.crydion.blog.mappers.CommentMapper;
import com.crydion.blog.mappers.PostMapper;
import com.crydion.blog.services.PostService;

@Service
public class PostServiceImpl implements PostService {

	private PostDAO postDAO;

	private PostMapper postMapper;

	private CommentMapper commentMapper;

	private AnalyzerClient analyzerClient;

	@Autowired
	public PostServiceImpl(PostDAO postDAO, PostMapper postMapper, CommentMapper commentMapper, AnalyzerClient analyzerClient) {
		this.postDAO = postDAO;
		this.postMapper = postMapper;
		this.commentMapper = commentMapper;
		this.analyzerClient = analyzerClient;
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
		Post post = getEntity(postId);
		if(hasSwearwords(commentDTO)) {
			throw new SwearwordException("Swearwords are not allowed");
		}

		commentDTO.setPublicationDate(LocalDate.now());
		post.addComment(commentMapper.mapDTO(commentDTO.setId(null)));

		return postMapper.mapEntity(postDAO.save(post));
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

	private boolean hasSwearwords(CommentDTO commentDTO) {
		return analyzerClient.analyzeContent(new ContentDTO().setContent(commentDTO.getContent())).hasSwearwords();
	}

}
