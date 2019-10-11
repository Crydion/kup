package com.crydion.analyzer.dtos;

public class ContentDTO {

	private Boolean swearwords;

	private String content;

	public Boolean hasSwearwords() {
		return swearwords;
	}

	public Boolean getSwearwords() {
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
