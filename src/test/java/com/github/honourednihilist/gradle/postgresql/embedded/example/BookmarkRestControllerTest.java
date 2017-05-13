package com.github.honourednihilist.gradle.postgresql.embedded.example;

import org.junit.After;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.isEmptyString;

public class BookmarkRestControllerTest extends BaseIntegrationTest {

	private static final String BOOKMARKS_PATH = "/bookmarks";
	private static final String BOOKMARK_ID_PARAM = "bookmarkId";
	private static final String BOOKMARK_PATH = BOOKMARKS_PATH + "/{" + BOOKMARK_ID_PARAM + "}";

	@Test
	public void testBookmarkCrud() {
		// Options
		given().when()
				.options(BOOKMARKS_PATH)

				.then()
				.statusCode(HttpStatus.OK.value())
				.body(isEmptyString());

		// Post
		TestBookmark expectedBookmark = new TestBookmark("https://github.com", "how people build software");
		TestBookmark createdBookmark = given()
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.body(expectedBookmark)

				.when()
				.post(BOOKMARKS_PATH)

				.then()
				.statusCode(HttpStatus.OK.value())
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.extract().as(TestBookmark.class);

		assertThat(createdBookmark.getId(), is(greaterThan(0)));
		assertThat(createdBookmark.getUri(), is(expectedBookmark.getUri()));
		assertThat(createdBookmark.getDescription(), is(expectedBookmark.getDescription()));

		// Put
		expectedBookmark.setUri(expectedBookmark.getUri() + "/");
		expectedBookmark.setDescription("Github is how people build software");
		TestBookmark updatedBookmark = given()
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.body(expectedBookmark)

				.when()
				.put(BOOKMARK_PATH, createdBookmark.getId())

				.then()
				.statusCode(HttpStatus.OK.value())
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.extract().as(TestBookmark.class);

		assertThat(updatedBookmark.getId(), is(createdBookmark.getId()));
		assertThat(updatedBookmark.getUri(), is(expectedBookmark.getUri()));
		assertThat(updatedBookmark.getDescription(), is(expectedBookmark.getDescription()));

		// Get
		TestBookmark loadedBookmark = given()
				.when()
				.get(BOOKMARK_PATH, updatedBookmark.getId())

				.then()
				.statusCode(HttpStatus.OK.value())
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.extract().as(TestBookmark.class);
		assertThat(loadedBookmark, is(updatedBookmark));

		// Delete
		given().when()
				.delete(BOOKMARK_PATH, loadedBookmark.getId())

				.then()
				.statusCode(HttpStatus.OK.value());

		// Get all
		given().when()
				.get(BOOKMARKS_PATH)

				.then()
				.statusCode(HttpStatus.OK.value())
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.body(is("[]"));
	}

	@After
	public void tearDown() throws Exception {
		bookmarkRepository.deleteAll();
	}
}
