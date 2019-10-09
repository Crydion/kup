package com.crydion.analyzer.services.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.crydion.analyzer.dtos.ContentDTO;
import com.crydion.analyzer.services.AnalyzerService;

@Service
public class AnalyzerServiceImpl implements AnalyzerService {

	private static List<String> SWEARWORDS;

	public AnalyzerServiceImpl() {
		super();
		SWEARWORDS = Arrays.asList("joder", "gilipollas", "hijo de puta", "hija de puta",
				"cabron", "imbecil", "pudre colchones", "cierra bares", "idiota");
	}

	@Override
	public boolean hasSwearwords(ContentDTO contentDTO) {
		String content = contentDTO.getContent().toLowerCase();

		return SWEARWORDS.stream()
			.anyMatch(content::contains);
	}

}
