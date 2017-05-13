package com.github.honourednihilist.gradle.postgresql.embedded.example;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface BookmarkMapper {

	@Options(useGeneratedKeys = true)
	@Insert("insert into bookmarks(uri, description) values(#{uri}, #{description})")
	void insert(Bookmark bookmark);

	@Update("update bookmarks set uri = #{uri}, description = #{description} where id = #{id}")
	void update(Bookmark bookmark);

	@Select("select * from bookmarks where id = #{id}")
	Bookmark findOne(Integer id);

	@Select("select * from bookmarks")
	List<Bookmark> findAll();

	@Select("select count(id) from bookmarks")
	int count();

	@Delete("delete from bookmarks where id = #{id}")
	void delete(Integer id);

	@Delete("delete from bookmarks")
	void deleteAll();
}
