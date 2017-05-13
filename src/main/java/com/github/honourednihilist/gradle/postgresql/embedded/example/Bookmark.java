package com.github.honourednihilist.gradle.postgresql.embedded.example;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Bookmark {
	private int id;
	private String uri;
	private String description;

	public Bookmark(String uri, String description) {
		this.uri = uri;
		this.description = description;
	}
}
