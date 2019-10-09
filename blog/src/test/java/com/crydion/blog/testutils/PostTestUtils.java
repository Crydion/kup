package com.crydion.blog.testutils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import com.crydion.blog.dtos.PostDTO;
import com.crydion.blog.entities.Post;

public class PostTestUtils {

	public static PostDTO generateRandomDTO() {
		Random random = new Random();
		return new PostDTO()
				.setId(random.nextInt(100))
				.setPublicationDate(LocalDate.now())
				.setAuthor(UUID.randomUUID().toString())
				.setTitle(UUID.randomUUID().toString())
				.setContent(UUID.randomUUID().toString())
				.setComments(CommentTestUtils.generateRandomDTOList(random.nextInt(9)+1));
	}

	public static Post generateRandomEntity() {
		Random random = new Random();
		return new Post()
				.setId(random.nextInt(100))
				.setPublicationDate(LocalDate.now())
				.setAuthor(UUID.randomUUID().toString())
				.setTitle(UUID.randomUUID().toString())
				.setContent(UUID.randomUUID().toString())
				.setComments(CommentTestUtils.generateRandomEntityList(random.nextInt(9)+1));
	}

	public static List<Post> generateRandomEntityList(int numElements) {
		List<Post> posts = new ArrayList<>(numElements);
		for(int i = 0; i<numElements; i++) {
			posts.add(generateRandomEntity());
		}
		return posts;
	}

}
