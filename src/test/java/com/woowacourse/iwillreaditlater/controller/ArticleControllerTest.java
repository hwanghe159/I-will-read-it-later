package com.woowacourse.iwillreaditlater.controller;

import com.woowacourse.iwillreaditlater.dto.ArticleCreateRequest;
import com.woowacourse.iwillreaditlater.service.ArticleService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;

import java.util.Collections;

import static com.woowacourse.iwillreaditlater.ArticleFixture.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ArticleController.class)
class ArticleControllerTest extends ControllerTest {

    @MockBean
    private ArticleService articleService;

    @DisplayName("게시글 전체 조회 테스트")
    @Test
    void getArticlesTest() throws Exception {
        given(articleService.getArticles()).willReturn(Collections.emptyList());

        this.mockMvc.perform(get("/articles"))
            .andExpect(status().isOk());
    }

    @DisplayName("게시글 추가 테스트")
    @Test
    void addArticleTest() throws Exception {
        given(articleService.addArticle(any())).willReturn(ARTICLE_ID_1);

        String inputJson =
            objectMapper.writeValueAsString(new ArticleCreateRequest(ARTICLE_URL_1, ARTICLE_TITLE_1, ARTICLE_CONTENT_1));

        this.mockMvc.perform(post("/articles")
            .content(inputJson)
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isCreated());
    }
}