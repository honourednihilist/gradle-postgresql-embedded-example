package com.github.honourednihilist.gradle.postgresql.embedded.example;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bookmarks")
public class BookmarkRestController {

	private final BookmarkRepository bookmarkRepository;

	public BookmarkRestController(BookmarkRepository bookmarkRepository) {
		this.bookmarkRepository = bookmarkRepository;
	}

	@GetMapping
	public Iterable<Bookmark> getBookmarks() {
		return bookmarkRepository.findAll();
	}

	@GetMapping("/{bookmarkId}")
	public Bookmark getBookmark(@PathVariable Integer bookmarkId) {
		return bookmarkRepository.findOne(bookmarkId);
	}

	@PostMapping
	public Bookmark createBookmark(@RequestBody Bookmark input) {
		return bookmarkRepository.save(new Bookmark(input.getUri(), input.getDescription()));
	}

	@PutMapping("/{bookmarkId}")
	public Bookmark updateBookmark(@PathVariable Integer bookmarkId, @RequestBody Bookmark input) {
		Bookmark bookmark = bookmarkRepository.findOne(bookmarkId);
		bookmark.setUri(input.getUri());
		bookmark.setDescription(input.getDescription());
		return bookmarkRepository.save(bookmark);
	}

	@DeleteMapping("/{bookmarkId}")
	public void deleteBookmark(@PathVariable Integer bookmarkId) {
		bookmarkRepository.delete(bookmarkId);
	}
}
