package com.crydion.blog.dtos;

public class ContentDTO {

	private boolean swearwords;

	private String content;

	public boolean hasSwearwords() {
		return swearwords;
	}

	public ContentDTO setSwearwords(boolean swearwords) {
		this.swearwords = swearwords;
		return this;
	}

	public String getContent() {
		return content;
	}

	public ContentDTO setContent(String content) {
		this.content = content;
		return this;
	}

}
