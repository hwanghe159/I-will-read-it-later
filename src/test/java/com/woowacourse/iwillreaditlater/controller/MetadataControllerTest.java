package com.woowacourse.iwillreaditlater.controller;

import com.woowacourse.iwillreaditlater.dto.MetadataResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MvcResult;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class MetadataControllerTest extends ControllerTest {

    @DisplayName("url을 입력 받으면 해당 게시글의 제목과 내용을 응답할 수 있어야 한다.")
    @Test
    void getMetaDataTest() throws Exception {
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
}