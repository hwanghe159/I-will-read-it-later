package com.woowacourse.iwillreaditlater.controller;

import com.woowacourse.iwillreaditlater.dto.MetadataResponse;
import com.woowacourse.iwillreaditlater.service.MetadataService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RequiredArgsConstructor
@RestController
public class MetadataController {

    private final MetadataService metadataService;

    @GetMapping("/metadata")
    public ResponseEntity<MetadataResponse> getMetaData(@RequestParam String url) throws IOException {
        MetadataResponse response = metadataService.getMetadata(url);
        return ResponseEntity.ok(response);
    }
}
