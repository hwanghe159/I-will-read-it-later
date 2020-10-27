package com.woowacourse.iwillreaditlater.controller;

import com.woowacourse.iwillreaditlater.dto.ErrorResponse;
import org.jsoup.HttpStatusException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.MalformedURLException;
import java.net.UnknownHostException;

@RestControllerAdvice
public class GlobalExceptionAdvice {

    @ExceptionHandler(UnknownHostException.class)
    public ResponseEntity<ErrorResponse> unknownHostExceptionHandler(UnknownHostException e) {
        return ResponseEntity.
            badRequest().
            body(new ErrorResponse("존재하지 않는 URL입니다. 다시 입력해주세요."));
    }

    @ExceptionHandler({HttpStatusException.class, MalformedURLException.class, IllegalArgumentException.class})
    public ResponseEntity<ErrorResponse> httpStatusExceptionHandler(HttpStatusException e) {
        return ResponseEntity.
            badRequest().
            body(new ErrorResponse("올바르지 않은 URL입니다. 다시 입력해주세요."));
    }
}
