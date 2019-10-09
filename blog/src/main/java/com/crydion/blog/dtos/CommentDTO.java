package com.crydion.blog.dtos;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("comment")
public class CommentDTO {

	private Integer id;

	private LocalDate publicationDate;

	private String author;

	private String content;

	public Integer getId() {
		return id;
	}

	public CommentDTO setId(Integer id) {
		this.id = id;
		return this;
	}

	public LocalDate getPublicationDate() {
		return publicationDate;
	}

	public CommentDTO setPublicationDate(LocalDate publicationDate) {
		this.publicationDate = publicationDate;
		return this;
	}

	public String getAuthor() {
		return author;
	}

	public CommentDTO setAuthor(String author) {
		this.author = author;
		return this;
	}

	public String getContent() {
		return content;
	}

	public CommentDTO setContent(String content) {
		this.content = content;
		return this;
	}

}
