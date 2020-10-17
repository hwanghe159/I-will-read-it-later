package com.woowacourse.iwillreaditlater.controller;

import com.woowacourse.iwillreaditlater.dto.ArticleCreateRequest;
import com.woowacourse.iwillreaditlater.service.ArticleService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ArticleControllerTest extends ControllerTest {

    @MockBean
    protected ArticleService articleService;

    @DisplayName("게시글 생성 테스트")
    @Test
    void addArticleTest() throws Exception {
        when(articleService.addArticle(any())).thenReturn(1L);

        ArticleCreateRequest request = new ArticleCreateRequest("https://junodiary.tistory.com/110");
        String inputJson = objectMapper.writeValueAsString(request);

        mockMvc.perform(post("/articles").
                content(inputJson).
                contentType(MediaType.APPLICATION_JSON)).
                andExpect(status().isCreated());
    }

    @DisplayName("예외 테스트: 존재하지 않는 url 등록 시 예외가 발생해야 한다.")
    @Test
    void addArticleTest2() {

    }
}