package com.woowacourse.iwillreaditlater.service;

import com.woowacourse.iwillreaditlater.domain.Article;
import com.woowacourse.iwillreaditlater.domain.ArticleRepository;
import com.woowacourse.iwillreaditlater.dto.ArticleCreateRequest;
import com.woowacourse.iwillreaditlater.dto.ArticleResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static com.woowacourse.iwillreaditlater.ArticleFixture.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class ArticleServiceTest {

    private ArticleService articleService;

    @Mock
    private ArticleRepository articleRepository;

    private Article article1;

    private Article article2;

    @BeforeEach
    void setUp() {
        articleService = new ArticleService(articleRepository);
        article1 = new Article(ARTICLE_ID_1, ARTICLE_URL_1, ARTICLE_TITLE_1, ARTICLE_CONTENT_1);
        article2 = new Article(ARTICLE_ID_2, ARTICLE_URL_2, ARTICLE_TITLE_2, ARTICLE_CONTENT_2);
    }

    @DisplayName("존재하는 모든 게시글을 반환할 수 있어야 한다.")
    @Test
    void getArticlesTest() {
        given(articleRepository.findAll()).willReturn(Arrays.asList(article1, article2));

        List<ArticleResponse> articleResponses = articleService.getArticles();

        assertAll(
            () -> assertThat(articleResponses).hasSize(2),
            () -> assertThat(articleResponses.get(0).getId()).isEqualTo(ARTICLE_ID_1),
            () -> assertThat(articleResponses.get(1).getId()).isEqualTo(ARTICLE_ID_2)
        );
    }

    @DisplayName("게시글을 저장하고 저장된 게시글의 id를 반환할 수 있어야 한다.")
    @Test
    void addArticleTest() {
        given(articleRepository.save(any())).willReturn(article1);

        ArticleCreateRequest request = new ArticleCreateRequest(ARTICLE_URL_1, ARTICLE_TITLE_1, ARTICLE_CONTENT_1);
        Long articleId = articleService.addArticle(request);

        assertThat(articleId).isEqualTo(ARTICLE_ID_1);
    }
}