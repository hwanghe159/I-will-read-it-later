package com.woowacourse.iwillreaditlater.domain;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ArticleRepositoryTest {

    @Autowired
    private ArticleRepository articleRepository;

    @Test
    void saveTest() {
        articleRepository.save(new Article("", "", ""));
        List<Article> articles = articleRepository.findAll();
        assertAll(
            () -> assertThat(articles).hasSize(1),
            () -> assertThat(articles.get(0).getUrl()).isEqualTo("")
        );
    }
}