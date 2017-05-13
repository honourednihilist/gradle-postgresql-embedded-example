package com.github.honourednihilist.gradle.postgresql.embedded.example;

import org.springframework.data.repository.CrudRepository;

public interface BookmarkRepository extends CrudRepository<Bookmark, Integer> {
}
