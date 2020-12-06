package com.woowacourse.iwillreaditlater.controller;

import com.woowacourse.iwillreaditlater.dto.ErrorResponse;
import com.woowacourse.iwillreaditlater.dto.MetadataResponse;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class MetadataControllerTest extends ControllerTest {

    @DisplayName("url을 입력 받으면 해당 게시글의 제목과 내용을 응답할 수 있어야 한다.")
    @Test
    void getMetaDataTest() {
        MetadataResponse metadataResponse = getMetaData("https://junodiary.tistory.com/111");

        assertAll(
            () -> assertThat(metadataResponse.getTitle()).isEqualTo("[객체지향의 사실과 오해] 2. 이상한 나라의 객체"),
            () -> assertThat(metadataResponse.getAuthor()).isEqualTo("사용자 junodiary"),
            () -> assertThat(metadataResponse.getContent()).contains("객체란 상태, 행동, 식별자를 가진 실체다"),
            () -> assertThat(metadataResponse.getImageSource()).isEqualTo("https://img1.daumcdn.net/thumb/R800x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2Fdu14W7%2FbtqCxQUM2Bj%2FJkV0FwXPA5UPzWI40r9wI1%2Fimg.jpg")
        );
    }

    @DisplayName("올바르지 않은 url을 입력 받으면 에러를 응답할 수 있어야 한다.")
    @ValueSource(strings = {"https://존재하지않는url.com/2", "https://jjjjunodiary.tistory.com/111"})
    @ParameterizedTest
    void getMetaDataFailByInvalidUrlTest(String input) {
        ErrorResponse errorResponse = getMetadataWithInvalidUrl(input);

        assertThat(errorResponse.getMessage()).isEqualTo("올바르지 않은 URL입니다. 다시 입력해주세요.");
    }

    private MetadataResponse getMetaData(String url) {
        //@formatter:off
        return
            given()
                .accept(ContentType.JSON)
            .when()
                .get("/metadata?url=" + url)
            .then()
                .log().all()
                .extract()
                .as(MetadataResponse.class);
        //@formatter:on
    }

    private ErrorResponse getMetadataWithInvalidUrl(String url) {
        //@formatter:off
        return
            given()
                .accept(ContentType.JSON)
           .when()
                .get("/metadata?url=" + url)
           .then()
                .log().all()
                .extract()
                .as(ErrorResponse.class);
        //@formatter:on
    }
}