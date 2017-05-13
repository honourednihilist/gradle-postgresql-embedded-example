package com.github.honourednihilist.gradle.postgresql.embedded.example;

import org.junit.Before;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.URI;

import io.restassured.RestAssured;

@Category(IntegrationTests.class)
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class}, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public abstract class BaseIntegrationTest {

	@Autowired
	private TestRestTemplate restTemplate;

	@Autowired
	protected BookmarkRepository bookmarkRepository;

	@Before
	public void setUp() throws Exception {
		URI uri = restTemplate.getRestTemplate().getUriTemplateHandler().expand("/");
		RestAssured.port = uri.getPort();
		RestAssured.basePath = uri.getPath();
	}
}
