package com.woowacourse.iwillreaditlater.service;

import com.woowacourse.iwillreaditlater.dto.MetadataResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class MetadataServiceTest {

    private MetadataService metadataService = new MetadataService();

    @DisplayName("url로 메타데이터를 생성해야 한다.")
    @Test
    void getMetadataTest() throws IOException {
        MetadataResponse response = metadataService.getMetadata("https://junodiary.tistory.com/111");

        assertAll(
            () -> assertThat(response.getTitle()).isEqualTo("[객체지향의 사실과 오해] 2. 이상한 나라의 객체"),
            () -> assertThat(response.getContent()).contains("객체는 자율적인 존재다.")
        );
    }
}