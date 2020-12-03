package com.woowacourse.iwillreaditlater.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, Long> {

    @Query("select a from Article a where " +
        "a.url like %?1% or " +
        "a.title like %?1% or " +
        "a.content like %?1% or " +
        "a.author like %?1%")
    List<Article> findByQuery(String query);
}
