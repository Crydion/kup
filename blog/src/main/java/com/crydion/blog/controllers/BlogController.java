package com.crydion.blog.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.crydion.blog.dtos.CommentDTO;
import com.crydion.blog.dtos.PostDTO;
import com.crydion.blog.services.PostService;

@RestController
public class BlogController {

	private PostService postService;

	@Autowired
	public BlogController(PostService postService) {
		this.postService = postService;
	}

	@GetMapping("/posts")
	public List<PostDTO> getPosts() {
		return postService.getPosts();
	}

	@GetMapping("/posts/{id}")
	public PostDTO getPost(@PathVariable Integer id) {
		return postService.getPost(id);
	}

	@PostMapping("/posts")
	@ResponseStatus(HttpStatus.CREATED)
	public PostDTO savePost(@RequestBody PostDTO postDTO) {
		return postService.savePost(postDTO);
	}

	@PutMapping("/posts/{id}")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public PostDTO updatePost(@PathVariable Integer id, @RequestBody PostDTO postDTO) {
		return postService.updatePost(id, postDTO);
	}

	@DeleteMapping("/posts/{id}")
	public PostDTO removePost(@PathVariable Integer id) {
		return postService.removePost(id);
	}

	@PostMapping("/posts/{id}/comments")
	@ResponseStatus(HttpStatus.CREATED)
	public PostDTO saveComment(@PathVariable Integer id, @RequestBody CommentDTO commentDTO) {
		return postService.addComment(id, commentDTO);
	}

}
