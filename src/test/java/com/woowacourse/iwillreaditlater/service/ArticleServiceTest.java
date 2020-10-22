package com.woowacourse.iwillreaditlater.service;

import com.woowacourse.iwillreaditlater.domain.Article;
import com.woowacourse.iwillreaditlater.domain.ArticleRepository;
import com.woowacourse.iwillreaditlater.dto.ArticleCreateRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class ArticleServiceTest {

    private ArticleService articleService;

    @Mock
    private ArticleRepository articleRepository;

    @BeforeEach
    void setUp() {
        articleService = new ArticleService(articleRepository);
    }

    @DisplayName("게시글을 저장하고 저장된 게시글의 id를 반환할 수 있어야 한다.")
    @Test
    void addArticleTest() {
        given(articleRepository.save(any())).willReturn(new Article(1L, "", "", ""));

        ArticleCreateRequest request = new ArticleCreateRequest("", "", "");
        Long articleId = articleService.addArticle(request);

        assertThat(articleId).isEqualTo(1L);
    }
}