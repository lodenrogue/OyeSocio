package com.lodenrogue.oyesocio;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

public class Schema {
	private String schema;

	public Schema(String fileName) throws IOException {
		Resource appInfo = new ClassPathResource(fileName);
		schema = getSchema(new File(appInfo.getURI()));
	}

	private String getSchema(File file) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(file));
		String schema = "";
		String line = "";
		while ((line = reader.readLine()) != null) {
			schema += line;
		}
		reader.close();
		return schema;
	}

	public void setAttribute(String attribute, String value) {
		schema = schema.replace(attribute, value);
	}

	public String build() {
		return schema;
	}

}
