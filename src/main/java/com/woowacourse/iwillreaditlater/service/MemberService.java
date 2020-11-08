package com.woowacourse.iwillreaditlater.service;

import com.woowacourse.iwillreaditlater.domain.Member;
import com.woowacourse.iwillreaditlater.domain.MemberRepository;
import com.woowacourse.iwillreaditlater.dto.MemberCreateRequest;
import com.woowacourse.iwillreaditlater.exception.DuplicateEmailException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public Long addMember(MemberCreateRequest request) {
        memberRepository.findByEmail(request.getEmail())
            .ifPresent(member -> {
                throw new DuplicateEmailException();
            });

        Member savedMember = memberRepository.save(request.toMember());
        return savedMember.getId();
    }

    public long getMemberSize() {
        return memberRepository.count();
    }
}
