package com.woowacourse.iwillreaditlater.controller;

import com.woowacourse.iwillreaditlater.domain.Article;
import com.woowacourse.iwillreaditlater.dto.ArticleCreateRequest;
import com.woowacourse.iwillreaditlater.dto.ArticleResponse;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import java.util.List;

import static com.woowacourse.iwillreaditlater.ArticleFixture.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class ArticleControllerTest extends ControllerTest {

    @BeforeEach
    void setUp() {
        addArticle(ARTICLE_1);
        addArticle(ARTICLE_2);
    }

    @DisplayName("게시글 전체 조회 테스트")
    @Test
    void getArticlesTest() {
        List<ArticleResponse> articleResponses = getAllArticles();

        assertAll(
            () -> assertThat(articleResponses).hasSize(2),
            () -> assertThat(articleResponses.get(0).getId()).isEqualTo(ARTICLE_ID_1),
            () -> assertThat(articleResponses.get(1).getId()).isEqualTo(ARTICLE_ID_2)
        );
    }

    @DisplayName("게시글 추가 테스트")
    @Test
    void addArticleTest() {
        List<ArticleResponse> articleResponses = getAllArticles();
        int existingArticlesSize = articleResponses.size();

        addArticle(ARTICLE_3);
        List<ArticleResponse> articleResponsesAfterSave = getAllArticles();

        assertAll(
            () -> assertThat(articleResponsesAfterSave).hasSize(existingArticlesSize + 1),
            () -> assertThat(articleResponsesAfterSave.get(0).getId()).isEqualTo(ARTICLE_ID_1),
            () -> assertThat(articleResponsesAfterSave.get(1).getId()).isEqualTo(ARTICLE_ID_2),
            () -> assertThat(articleResponsesAfterSave.get(2).getId()).isEqualTo(ARTICLE_ID_3)
        );
    }

    @DisplayName("게시글 검색 테스트")
    @Test
    void searchArticlesTest() {
        List<ArticleResponse> articleResponses = searchArticles("1");

        assertAll(
            () -> assertThat(articleResponses).hasSize(1),
            () -> assertThat(articleResponses.get(0).getId()).isEqualTo(ARTICLE_1.getId())
        );
    }

    private void addArticle(Article article) {
        ArticleCreateRequest request = toRequest(article);

        //@formatter:off
        given()
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .body(request)
        .when()
            .post("/articles");
        //@formatter:on
    }

    private List<ArticleResponse> getAllArticles() {
        //@formatter:off
        return
            given()
                .accept(ContentType.JSON)
            .when()
                .get("/articles")
            .then()
                .log().all()
                .extract()
                .jsonPath().getList(".", ArticleResponse.class);
        //@formatter:on
    }

    private List<ArticleResponse> searchArticles(String query) {
        //@formatter:off
        return
            given()
                .accept(ContentType.JSON)
            .when()
                .get("/articles?query=" + query)
            .then()
                .log().all()
                .extract()
                .jsonPath().getList(".", ArticleResponse.class);
        //@formatter:on
    }

    private ArticleCreateRequest toRequest(Article article) {
        String url = article.getUrl();
        String title = article.getTitle();
        String content = article.getContent();
        String author = article.getAuthor();
        String imageSource = article.getImageSource();

        return new ArticleCreateRequest(url, title, content, author, imageSource);
    }
}