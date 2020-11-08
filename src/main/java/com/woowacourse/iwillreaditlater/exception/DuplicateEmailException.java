package com.woowacourse.iwillreaditlater.exception;

public class DuplicateEmailException extends RuntimeException {
    public DuplicateEmailException() {
        super("중복된 이메일이 존재합니다.");
    }
}
