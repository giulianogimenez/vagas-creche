package br.edu.fatecsjc.creche.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class LocalDateTimeAttributeAdapter extends XmlAdapter<String, LocalDateTime> {

	public static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

	@Override
	public LocalDateTime unmarshal(String v) throws Exception {
		return LocalDateTime.parse(v, formatter);
	}

	@Override
	public String marshal(LocalDateTime v) throws Exception {
		return v.format(formatter);
	}
}
