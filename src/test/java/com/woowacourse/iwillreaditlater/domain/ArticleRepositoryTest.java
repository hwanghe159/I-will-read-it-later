package com.woowacourse.iwillreaditlater.domain;

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

    @Test
    void saveTest() {
        articleRepository.save(new Article(ARTICLE_URL_1, ARTICLE_TITLE_1, ARTICLE_CONTENT_1, ARTICLE_AUTHOR_1, ARTICLE_IMAGE_SOURCE_1));
        List<Article> articles = articleRepository.findAll();
        assertAll(
            () -> assertThat(articles).hasSize(1),
            () -> assertThat(articles.get(0).getUrl()).isEqualTo(ARTICLE_URL_1)
        );
    }
}