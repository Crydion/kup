package com.crydion.blog.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "POSTS")
public class Post {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private LocalDate publicationDate;

	private LocalDate lastModified;

	private String author;

	private String title;

	@Column(length = 4000)
	private String content;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name="POST_ID")
	private List<Comment> comments;

	public Post() {}

	public Post(Integer id, LocalDate publicationDate, LocalDate lastModified, String author, String title) {
		this.id = id;
		this.publicationDate = publicationDate;
		this.lastModified = lastModified;
		this.author = author;
		this.title = title;
	}

	public Integer getId() {
		return id;
	}

	public Post setId(Integer id) {
		this.id = id;
		return this;
	}

	public LocalDate getPublicationDate() {
		return publicationDate;
	}

	public Post setPublicationDate(LocalDate publicationDate) {
		this.publicationDate = publicationDate;
		return this;
	}

	public LocalDate getLastModified() {
		return lastModified;
	}

	public Post setLastModified(LocalDate lastModified) {
		this.lastModified = lastModified;
		return this;
	}

	public String getAuthor() {
		return author;
	}

	public Post setAuthor(String author) {
		this.author = author;
		return this;
	}

	public String getTitle() {
		return title;
	}

	public Post setTitle(String title) {
		this.title = title;
		return this;
	}

	public String getContent() {
		return content;
	}

	public Post setContent(String content) {
		this.content = content;
		return this;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public Post setComments(List<Comment> comments) {
		this.comments = comments;
		return this;
	}

	public Post addComment(Comment comment) {
		if(comments==null) {
			comments = new ArrayList<>();
		}
		comments.add(comment);
		return this;
	}

}
