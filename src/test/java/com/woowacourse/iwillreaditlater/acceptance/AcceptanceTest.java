package com.woowacourse.iwillreaditlater.acceptance;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
abstract class AcceptanceTest {

    @LocalServerPort
    int port;

    protected ObjectMapper objectMapper = new ObjectMapper();

    public static RequestSpecification given() {
        return RestAssured.given().log().all();
    }

    @BeforeEach
    public void setUpAcceptanceTest() {
        RestAssured.port = port;
    }
}
