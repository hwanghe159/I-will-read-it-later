package com.woowacourse.iwillreaditlater.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static com.woowacourse.iwillreaditlater.ArticleFixture.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ArticleRepositoryTest {

    @Autowired
    private ArticleRepository articleRepository;

    Article article1;

    Article article2;

    @BeforeEach
    void setUp() {
        article1 = new Article(ARTICLE_URL_1, ARTICLE_TITLE_1, ARTICLE_CONTENT_1, ARTICLE_AUTHOR_1, ARTICLE_IMAGE_SOURCE_1);
        article2 = new Article(ARTICLE_URL_2, ARTICLE_TITLE_2, ARTICLE_CONTENT_2, ARTICLE_AUTHOR_2, ARTICLE_IMAGE_SOURCE_2);
    }

    @DisplayName("저장 테스트")
    @Test
    void saveTest() {
        articleRepository.save(article1);

        List<Article> articles = articleRepository.findAll();

        assertAll(
            () -> assertThat(articles).hasSize(1),
            () -> assertThat(articles.get(0).getUrl()).isEqualTo(ARTICLE_URL_1)
        );
    }

    @DisplayName("검색 테스트")
    @Test
    void findByQueryTest() {
        articleRepository.save(article1);
        articleRepository.save(article2);

        List<Article> articles = articleRepository.findByQuery("내용");

        assertThat(articles).hasSize(2);
    }
}