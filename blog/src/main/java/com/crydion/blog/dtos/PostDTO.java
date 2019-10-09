package com.crydion.blog.dtos;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class PostDTO {

	private Integer id;

	private LocalDate publicationDate;

	private LocalDate lastModified;

	private String author;

	private String title;

	@JsonInclude(value = Include.NON_NULL)
	private String content;

	@JsonInclude(value = Include.NON_NULL)
	private List<CommentDTO> comments;

	public Integer getId() {
		return id;
	}

	public PostDTO setId(Integer id) {
		this.id = id;
		return this;
	}

	public LocalDate getPublicationDate() {
		return publicationDate;
	}

	public PostDTO setPublicationDate(LocalDate publicationDate) {
		this.publicationDate = publicationDate;
		return this;
	}

	public LocalDate getLastModified() {
		return lastModified;
	}

	public PostDTO setLastModified(LocalDate lastModified) {
		this.lastModified = lastModified;
		return this;
	}

	public String getAuthor() {
		return author;
	}

	public PostDTO setAuthor(String author) {
		this.author = author;
		return this;
	}

	public String getTitle() {
		return title;
	}

	public PostDTO setTitle(String title) {
		this.title = title;
		return this;
	}

	public String getContent() {
		return content;
	}

	public PostDTO setContent(String content) {
		this.content = content;
		return this;
	}

	public List<CommentDTO> getComments() {
		return comments;
	}

	public PostDTO setComments(List<CommentDTO> comments) {
		this.comments = comments;
		return this;
	}

	public PostDTO addComment(CommentDTO commentDTO) {
		if(comments==null) {
			comments = new ArrayList<>();
		}
		comments.add(commentDTO);
		return this;
	}

}
