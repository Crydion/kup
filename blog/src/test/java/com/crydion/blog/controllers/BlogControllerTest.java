package com.crydion.blog.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.crydion.blog.dtos.CommentDTO;
import com.crydion.blog.dtos.PostDTO;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@AutoConfigureWireMock(port = 8081)
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
public class BlogControllerTest {

	@Autowired
	private MockMvc mockMvc;

	private ObjectMapper jsonObjectMapper = new ObjectMapper();

	@Test
	public void getPostsTest() throws Exception {
		ResultActions result = mockMvc.perform(get("/posts"));

		result.andExpect(status().isOk())
			.andExpect(jsonPath("$.length()").value(2))
			.andExpect(jsonPath("$[0].id").value(1))
			.andExpect(jsonPath("$[0].publicationDate").exists())
			.andExpect(jsonPath("$[0].lastModified").isEmpty())
			.andExpect(jsonPath("$[0].author").value("Crydion"))
			.andExpect(jsonPath("$[0].title").value("Lorem ipsum"))
			.andExpect(jsonPath("$[0].content").doesNotExist())
			.andExpect(jsonPath("$[0].comments").doesNotExist())
			.andExpect(jsonPath("$[1].id").value(2))
			.andExpect(jsonPath("$[1].publicationDate").exists())
			.andExpect(jsonPath("$[1].lastModified").isEmpty())
			.andExpect(jsonPath("$[1].author").value("Batar"))
			.andExpect(jsonPath("$[1].title").value("Dragons and other stuff"))
			.andExpect(jsonPath("$[1].content").doesNotExist())
			.andExpect(jsonPath("$[1].comments").doesNotExist());
	}

	@Test
	public void getPostTest() throws Exception {
		ResultActions result = mockMvc.perform(get("/posts/1"));

		result.andExpect(status().isOk())
			.andExpect(jsonPath("$.id").value(1))
			.andExpect(jsonPath("$.publicationDate").exists())
			.andExpect(jsonPath("$.lastModified").isEmpty())
			.andExpect(jsonPath("$.author").value("Crydion"))
			.andExpect(jsonPath("$.title").value("Lorem ipsum"))
			.andExpect(jsonPath("$.content").isNotEmpty())
			.andExpect(jsonPath("$.comments.length()").value(1))
			.andExpect(jsonPath("$.comments[0].id").value(1))
			.andExpect(jsonPath("$.comments[0].publicationDate").exists())
			.andExpect(jsonPath("$.comments[0].author").value("Sergy112"))
			.andExpect(jsonPath("$.comments[0].content").value("Im lovin it"));
	}

	@Test
	public void getPostDoesNotExistsTest() throws Exception {
		ResultActions result = mockMvc.perform(get("/posts/-1"));

		result.andExpect(status().isNotFound())
			.andExpect(jsonPath("$.status").value(404))
			.andExpect(jsonPath("$.message").value("Post not found: -1"))
			.andExpect(jsonPath("$.code").isNotEmpty());
	}

	@Test
	@WithMockUser("Crydion")
	public void savePostTest() throws Exception {
		PostDTO postDTO = new PostDTO()
			.setAuthor("Bea")
			.setTitle("Saviours")
			.setContent("...");
		ResultActions result = mockMvc.perform(post("/posts")
				.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE).content(jsonObjectMapper.writeValueAsBytes(postDTO)));

		result.andExpect(status().isCreated())
			.andExpect(jsonPath("$.id").value(3))
			.andExpect(jsonPath("$.publicationDate").isNotEmpty())
			.andExpect(jsonPath("$.lastModified").isEmpty())
			.andExpect(jsonPath("$.author").value("Bea"))
			.andExpect(jsonPath("$.title").value("Saviours"))
			.andExpect(jsonPath("$.content").value("..."))
			.andExpect(jsonPath("$.comments").doesNotExist());
	}

	@Test
	@WithMockUser("Crydion")
	public void updatePostTest() throws Exception {
		PostDTO postDTO = new PostDTO()
			.setAuthor("Crydion")
			.setTitle("Lorem ipsum!!!")
			.setContent("Hey!")
			.addComment(new CommentDTO()
				.setId(1)
				.setAuthor("Sergy112")
				.setContent("Im lovin it")
			);
		ResultActions result = mockMvc.perform(put("/posts/1")
				.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE).content(jsonObjectMapper.writeValueAsBytes(postDTO)));

		result.andExpect(status().isAccepted())
			.andExpect(jsonPath("$.id").value(1))
			.andExpect(jsonPath("$.publicationDate").isNotEmpty())
			.andExpect(jsonPath("$.lastModified").isNotEmpty())
			.andExpect(jsonPath("$.author").value("Crydion"))
			.andExpect(jsonPath("$.title").value("Lorem ipsum!!!"))
			.andExpect(jsonPath("$.content").value("Hey!"))
			.andExpect(jsonPath("$.comments.length()").value(1))
			.andExpect(jsonPath("$.comments[0].id").value(1))
			.andExpect(jsonPath("$.comments[0].author").value("Sergy112"))
			.andExpect(jsonPath("$.comments[0].content").value("Im lovin it"));
	}

	@Test
	@WithMockUser("Crydion")
	public void addCommentTest() throws Exception {
		CommentDTO commentDTO = new CommentDTO()
				.setAuthor("Yo mismo")
				.setContent("Buenas");
		ResultActions result = mockMvc.perform(post("/posts/2/comments")
				.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE).content(jsonObjectMapper.writeValueAsBytes(commentDTO)));

		result.andExpect(status().isCreated())
		.andExpect(jsonPath("$.id").value(2))
		.andExpect(jsonPath("$.publicationDate").exists())
		.andExpect(jsonPath("$.lastModified").isEmpty())
		.andExpect(jsonPath("$.author").value("Batar"))
		.andExpect(jsonPath("$.title").value("Dragons and other stuff"))
		.andExpect(jsonPath("$.content").isNotEmpty())
		.andExpect(jsonPath("$.comments.length()").value(1))
		.andExpect(jsonPath("$.comments[0].id").isNotEmpty())
		.andExpect(jsonPath("$.comments[0].publicationDate").isNotEmpty())
		.andExpect(jsonPath("$.comments[0].author").value(commentDTO.getAuthor()))
		.andExpect(jsonPath("$.comments[0].content").value(commentDTO.getContent()));
	}

	@Test
	@WithMockUser("Crydion")
	public void addCommentNotValidTest() throws Exception {
		CommentDTO commentDTO = new CommentDTO()
				.setAuthor("Yo mismo")
				.setContent("Gilipollas");
		ResultActions result = mockMvc.perform(post("/posts/2/comments")
				.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE).content(jsonObjectMapper.writeValueAsBytes(commentDTO)));

		result.andExpect(status().isBadRequest())
			.andExpect(jsonPath("$.status").value(400))
			.andExpect(jsonPath("$.message").value("Swearwords are not allowed"))
			.andExpect(jsonPath("$.code").isNotEmpty());
	}

	@Test
	@WithMockUser("Crydion")
	public void removePostTest() throws Exception {
		ResultActions resultDelete = mockMvc.perform(delete("/posts/1"));

		resultDelete.andExpect(status().isOk())
			.andExpect(jsonPath("$.id").value(1))
			.andExpect(jsonPath("$.publicationDate").exists())
			.andExpect(jsonPath("$.lastModified").isEmpty())
			.andExpect(jsonPath("$.author").value("Crydion"))
			.andExpect(jsonPath("$.title").value("Lorem ipsum"))
			.andExpect(jsonPath("$.content").isNotEmpty())
			.andExpect(jsonPath("$.comments.length()").value(1))
			.andExpect(jsonPath("$.comments[0].id").value(1))
			.andExpect(jsonPath("$.comments[0].publicationDate").exists())
			.andExpect(jsonPath("$.comments[0].author").value("Sergy112"))
			.andExpect(jsonPath("$.comments[0].content").value("Im lovin it"));

		ResultActions result = mockMvc.perform(get("/posts"));

		result.andExpect(status().isOk())
			.andExpect(jsonPath("$.length()").value(1))
			.andExpect(jsonPath("$[0].id").value(2))
			.andExpect(jsonPath("$[0].publicationDate").exists())
			.andExpect(jsonPath("$[0].lastModified").isEmpty())
			.andExpect(jsonPath("$[0].author").value("Batar"))
			.andExpect(jsonPath("$[0].title").value("Dragons and other stuff"))
			.andExpect(jsonPath("$[0].content").doesNotExist())
			.andExpect(jsonPath("$[0].comments").doesNotExist());
	}

}
