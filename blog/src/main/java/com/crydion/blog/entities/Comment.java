package com.crydion.blog.entities;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "COMMENTS")
public class Comment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private LocalDate publicationDate;

	private String author;

	@Column(length = 500)
	private String content;

	public Integer getId() {
		return id;
	}

	public Comment setId(Integer id) {
		this.id = id;
		return this;
	}

	public LocalDate getPublicationDate() {
		return publicationDate;
	}

	public Comment setPublicationDate(LocalDate publicationDate) {
		this.publicationDate = publicationDate;
		return this;
	}

	public String getAuthor() {
		return author;
	}

	public Comment setAuthor(String author) {
		this.author = author;
		return this;
	}

	public String getContent() {
		return content;
	}

	public Comment setContent(String content) {
		this.content = content;
		return this;
	}

}
