package com.woowacourse.iwillreaditlater.dto;

import com.woowacourse.iwillreaditlater.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MemberCreateRequest {

    private String email;

    private String password;

    public Member toMember() {
        return new Member(email, password);
    }
}
