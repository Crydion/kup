package com.crydion.analyzer.services;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.crydion.analyzer.dtos.ContentDTO;
import com.crydion.analyzer.services.impl.AnalyzerServiceImpl;

public class AnalyzerServiceTest {

	private AnalyzerService analyzerService = new AnalyzerServiceImpl();

	@Test
	public void hasSwearwordsFalseTest() {
		ContentDTO contentDTO = new ContentDTO()
			.setContent("Buenos días");
		assertFalse(analyzerService.hasSwearwords(contentDTO).hasSwearwords());
	}

	@Test
	public void hasSwearwordsTrueTest() {
		ContentDTO contentDTO = new ContentDTO()
			.setContent("Gilipollas eres un completo idiota");
		assertTrue(analyzerService.hasSwearwords(contentDTO).hasSwearwords());
	}

}
