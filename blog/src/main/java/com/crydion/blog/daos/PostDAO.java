package com.crydion.blog.daos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.crydion.blog.entities.Post;

@Repository
public interface PostDAO extends JpaRepository<Post, Integer>{

	@Query("SELECT new Post(id, publicationDate, lastModified, author, title) FROM Post")
	List<Post> getAllPostsSimplified();

}
