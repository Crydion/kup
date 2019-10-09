package com.crydion.blog.testutils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import com.crydion.blog.dtos.CommentDTO;
import com.crydion.blog.entities.Comment;

public class CommentTestUtils {

	public static CommentDTO generateRandomDTO() {
		Random random = new Random();
		return new CommentDTO()
				.setId(random.nextInt(100))
				.setPublicationDate(LocalDate.now())
				.setAuthor(UUID.randomUUID().toString())
				.setContent(UUID.randomUUID().toString());
	}

	public static List<CommentDTO> generateRandomDTOList(int numElements) {
		List<CommentDTO> comments = new ArrayList<>(numElements);
		for(int i = 0; i<numElements; i++) {
			comments.add(generateRandomDTO());
		}
		return comments;
	}

	public static Comment generateRandomEntity() {
		Random random = new Random();
		return new Comment()
				.setId(random.nextInt(100))
				.setPublicationDate(LocalDate.now())
				.setAuthor(UUID.randomUUID().toString())
				.setContent(UUID.randomUUID().toString());
	}

	public static List<Comment> generateRandomEntityList(int numElements) {
		List<Comment> comments = new ArrayList<>(numElements);
		for(int i = 0; i<numElements; i++) {
			comments.add(generateRandomEntity());
		}
		return comments;
	}

}
