package com.woowacourse.iwillreaditlater.controller;

import com.woowacourse.iwillreaditlater.dto.ErrorResponse;
import com.woowacourse.iwillreaditlater.exception.DuplicateEmailException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.MalformedURLException;

@RestControllerAdvice
public class GlobalExceptionAdvice {

    @ExceptionHandler(MalformedURLException.class)
    public ResponseEntity<ErrorResponse> malformedURLExceptionHandler(MalformedURLException e) {
        return ResponseEntity
            .badRequest()
            .body(new ErrorResponse("올바르지 않은 URL입니다. 다시 입력해주세요."));
    }

    @ExceptionHandler(DuplicateEmailException.class)
    public ResponseEntity<ErrorResponse> duplicateEmailExceptionHandler(DuplicateEmailException e) {
        return ResponseEntity
            .badRequest()
            .body(new ErrorResponse(e.getMessage()));
    }
}
