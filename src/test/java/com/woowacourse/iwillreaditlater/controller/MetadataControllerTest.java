package com.woowacourse.iwillreaditlater.controller;

import com.woowacourse.iwillreaditlater.dto.ErrorResponse;
import com.woowacourse.iwillreaditlater.dto.MetadataResponse;
import com.woowacourse.iwillreaditlater.service.MetadataService;
import org.jsoup.HttpStatusException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MvcResult;

import java.net.UnknownHostException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MetadataController.class)
class MetadataControllerTest extends ControllerTest {

    @MockBean
    private MetadataService metadataService;

    @DisplayName("url을 입력 받으면 해당 게시글의 제목과 내용을 응답할 수 있어야 한다.")
    @Test
    void getMetaDataTest() throws Exception {
        given(metadataService.getMetadata(any())).willReturn(
            new MetadataResponse("[객체지향의 사실과 오해] 2. 이상한 나라의 객체", "객체는 자율적인 존재다. 객체 개개인은 본인의 상태를 책임져야 한다. 다른 객체의 상태를 주무를 수 없다."));

        MvcResult mvcResult = mockMvc.perform(get("/metadata?url=https://junodiary.tistory.com/111"))
            .andExpect(status().isOk())
            .andReturn();

        String content = mvcResult.getResponse().getContentAsString();
        MetadataResponse response = objectMapper.readValue(content, MetadataResponse.class);

        assertAll(
            () -> assertThat(response.getTitle()).isEqualTo("[객체지향의 사실과 오해] 2. 이상한 나라의 객체"),
            () -> assertThat(response.getContent()).contains("객체는 자율적인 존재다.")
        );
    }

    @DisplayName("존재하지 않는 url을 입력 받으면 에러를 응답할 수 있어야 한다.")
    @Test
    void getMetaDataFailByNotExistUrlTest() throws Exception {
        given(metadataService.getMetadata(any())).willThrow(new UnknownHostException());

        MvcResult mvcResult = mockMvc.perform(get("/metadata?url=https://존재하지않는url.com/2"))
            .andExpect(status().isBadRequest())
            .andReturn();

        String content = mvcResult.getResponse().getContentAsString();
        ErrorResponse response = objectMapper.readValue(content, ErrorResponse.class);

        assertThat(response.getMessage()).isEqualTo("존재하지 않는 URL입니다. 다시 입력해주세요.");
    }

    @DisplayName("올바르지 않은 url을 입력 받으면 에러를 응답할 수 있어야 한다.")
    @Test
    void getMetaDataFailByInvalidUrlTest() throws Exception {
        given(metadataService.getMetadata(any())).willThrow(new HttpStatusException(null, 0, null));

        MvcResult mvcResult = mockMvc.perform(get("/metadata?url=https://jjjjunodiary.tistory.com/111"))
            .andExpect(status().isBadRequest())
            .andReturn();

        String content = mvcResult.getResponse().getContentAsString();
        ErrorResponse response = objectMapper.readValue(content, ErrorResponse.class);

        assertThat(response.getMessage()).isEqualTo("올바르지 않은 URL입니다. 다시 입력해주세요.");
    }
}