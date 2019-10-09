package com.crydion.analyzer.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.crydion.analyzer.dtos.ContentDTO;
import com.crydion.analyzer.services.AnalyzerService;

@RestController("/analyze")
public class AnalyzerController {

	private AnalyzerService analyzerService;

	@Autowired
	public AnalyzerController(AnalyzerService analyzerService) {
		super();
		this.analyzerService = analyzerService;
	}

	@PostMapping("/")
	public boolean analyzeContent(@RequestBody ContentDTO contentDTO) {
		return analyzerService.hasSwearwords(contentDTO);
	}

}
