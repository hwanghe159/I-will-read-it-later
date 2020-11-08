package com.woowacourse.iwillreaditlater.controller;

import com.woowacourse.iwillreaditlater.dto.MemberCreateRequest;
import com.woowacourse.iwillreaditlater.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RequiredArgsConstructor
@RestController
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/members")
    public ResponseEntity<Long> addMember(@RequestBody MemberCreateRequest request) {
        Long memberId = memberService.addMember(request);

        return ResponseEntity
            .created(URI.create("/members/" + memberId))
            .body(memberId);
    }

    @GetMapping("/members/size")
    public ResponseEntity<Long> getMemberSize() {
        long memberSize = memberService.getMemberSize();

        return ResponseEntity.ok(memberSize);
    }
}
