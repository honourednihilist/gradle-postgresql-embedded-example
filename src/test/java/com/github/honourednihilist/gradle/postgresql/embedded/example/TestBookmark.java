package com.github.honourednihilist.gradle.postgresql.embedded.example;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TestBookmark {
	private int id;
	private String uri;
	private String description;

	public TestBookmark(String uri, String description) {
		this.uri = uri;
		this.description = description;
	}
}
