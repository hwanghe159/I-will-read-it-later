package com.woowacourse.iwillreaditlater.acceptance;

import com.woowacourse.iwillreaditlater.dto.ArticleResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ArticleAcceptanceTest {

    @DisplayName("게시글을 등록, 검색, 삭제할 수 있어야 한다.")
    @Test
    void articleAcceptanceTest() {
        Long articleId = addArticle("https://junodiary.tistory.com/110");
        List<ArticleResponse> searchedArticles = searchArticles("클래스");
        assertThat(searchedArticles).hasSize(1);
        deleteArticle(articleId);
        List<ArticleResponse> allArticles = readArticles();
        assertThat(allArticles).isEmpty();
    }

    private Long addArticle(String url) {
        return 1L;
    }

    private List<ArticleResponse> searchArticles(String searchWord) {
        return Arrays.asList(new ArticleResponse());
    }

    private void deleteArticle(Long articleId) {

    }

    private List<ArticleResponse> readArticles() {
        return Collections.emptyList();
    }
}