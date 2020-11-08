package com.woowacourse.iwillreaditlater.acceptance;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.woowacourse.iwillreaditlater.domain.Member;
import com.woowacourse.iwillreaditlater.dto.ErrorResponse;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static org.assertj.core.api.Assertions.assertThat;

class MemberAcceptanceTest extends AcceptanceTest {

    public static final String EXIST_EMAIL = "이미존재하는이메일";
    public static final String EXIST_PASSWORD = "이미존재하는비밀번호";

    /*
    시나리오

    given: 회원이 존재한다.
    when: 중복된 이메일로 회원가입을 시도한다.
    then: 회원가입에 실패한다.

    when: 중복되지 않은 이메일로 회원가입을 시도한다.
    then: 회원가입에 성공한다.

     */

    @DisplayName("회원을 관리한다.")
    @Test
    void manageMember() throws JsonProcessingException {
        // given: 회원이 존재한다.
        Member alreadyExistMember = new Member(EXIST_EMAIL, EXIST_PASSWORD);
        createMember(alreadyExistMember);

        // when: 중복된 이메일로 회원가입을 시도한다.
        ErrorResponse errorResponse = createInvalidMember();

        // then: 회원가입에 실패한다.
        assertThat(errorResponse.getMessage()).isEqualTo("중복된 이메일이 존재합니다.");

        // when: 중복되지 않은 이메일로 회원가입을 시도한다.
        Member newMember = new Member("hwanghe159@naver.com", "1234");
        createMember(newMember);

        // then: 회원가입에 성공한다.
        assertThat(getMemberSize()).isEqualTo(2);
    }

    void createMember(Member member) throws JsonProcessingException {
        String inputJson = objectMapper.writeValueAsString(member);

        //@formatter:off
        given()
            .body(inputJson)
            .contentType(ContentType.JSON)
            .accept(ContentType.JSON)
        .when()
            .post("/members")
        .then()
            .log().all()
            .statusCode(HttpStatus.CREATED.value());
        //@formatter:on
    }

    private ErrorResponse createInvalidMember() throws JsonProcessingException {
        Member invalidMember = new Member(EXIST_EMAIL, "1234");
        String inputJson = objectMapper.writeValueAsString(invalidMember);

        //@formatter:off
        return
            given()
                .body(inputJson)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
            .when()
                .post("/members")
            .then()
                .log().all()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .extract().as(ErrorResponse.class);
        //@formatter:on
    }

    private Long getMemberSize() {
        //@formatter:off
        return
            given()
                .accept(ContentType.JSON)
            .when()
                .get("/members/size")
            .then()
                .log().all()
                .extract()
                .as(Long.class);
        //@formatter:on
    }
}
