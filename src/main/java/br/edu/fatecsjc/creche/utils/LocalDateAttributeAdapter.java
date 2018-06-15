package br.edu.fatecsjc.creche.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class LocalDateAttributeAdapter extends XmlAdapter<String, LocalDate> {

	public static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	@Override
	public LocalDate unmarshal(String v) throws Exception {
		return LocalDate.parse(v, formatter);
	}

	@Override
	public String marshal(LocalDate v) throws Exception {
		return v.format(formatter);
	}
}
