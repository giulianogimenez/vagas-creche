package br.edu.fatecsjc.creche.utils;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

public @Data class ApiResponse {
	private String status;
	private String msg;
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private LocalDateTime time;
	
}
