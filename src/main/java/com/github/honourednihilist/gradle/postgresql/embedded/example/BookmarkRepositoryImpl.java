package com.github.honourednihilist.gradle.postgresql.embedded.example;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import one.util.streamex.StreamEx;

@Service
@Transactional
public class BookmarkRepositoryImpl implements BookmarkRepository {

	private final BookmarkMapper bookmarkMapper;

	public BookmarkRepositoryImpl(BookmarkMapper bookmarkMapper) {
		this.bookmarkMapper = bookmarkMapper;
	}

	@Override
	public <S extends Bookmark> S save(S bookmark) {
		if (bookmark.getId() > 0) {
			bookmarkMapper.update(bookmark);
		} else {
			bookmarkMapper.insert(bookmark);
		}

		return bookmark;
	}

	@Override
	public <S extends Bookmark> Iterable<S> save(Iterable<S> bookmarks) {
		bookmarks.forEach(this::save);
		return bookmarks;
	}

	@Override
	public Bookmark findOne(Integer id) {
		return bookmarkMapper.findOne(id);
	}

	@Override
	public boolean exists(Integer id) {
		return bookmarkMapper.findOne(id) != null;
	}

	@Override
	public Iterable<Bookmark> findAll() {
		return bookmarkMapper.findAll();
	}

	@Override
	public Iterable<Bookmark> findAll(Iterable<Integer> ids) {
		return StreamEx.of(ids.iterator()).map(this::findOne).toList();
	}

	@Override
	public long count() {
		return bookmarkMapper.count();
	}

	@Override
	public void delete(Integer id) {
		bookmarkMapper.delete(id);
	}

	@Override
	public void delete(Bookmark bookmark) {
		delete(bookmark.getId());
	}

	@Override
	public void delete(Iterable<? extends Bookmark> bookmarks) {
		bookmarks.forEach(this::delete);
	}

	@Override
	public void deleteAll() {
		bookmarkMapper.deleteAll();
	}
}
