package com.woowacourse.iwillreaditlater.controller;

import com.woowacourse.iwillreaditlater.dto.MetadataResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class MetadataController {

    @GetMapping("/metadata")
    public ResponseEntity<MetadataResponse> getMetaData(@RequestParam String url) {
        return ResponseEntity.ok(new MetadataResponse("[객체지향의 사실과 오해] 2. 이상한 나라의 객체", "~~~~~객체는 자율적인 존재다.~~~~~~"));
    }
}
