package com.crydion.analyzer.services;

import com.crydion.analyzer.dtos.ContentDTO;

public interface AnalyzerService {

	boolean hasSwearwords(ContentDTO contentDTO);

}
