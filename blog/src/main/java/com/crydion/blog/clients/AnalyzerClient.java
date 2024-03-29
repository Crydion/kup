package com.crydion.blog.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

import com.crydion.blog.dtos.ContentDTO;

@FeignClient("analyzer")
public interface AnalyzerClient {

	@PostMapping(path = "/analyze", consumes = "application/json")
	ContentDTO analyzeContent(ContentDTO contentDTO);

}
