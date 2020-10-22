package com.woowacourse.iwillreaditlater.controller;

import com.woowacourse.iwillreaditlater.dto.ArticleCreateRequest;
import com.woowacourse.iwillreaditlater.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RequiredArgsConstructor
@RestController
public class ArticleController {

    private final ArticleService articleService;

    @PostMapping("/articles")
    public ResponseEntity<Long> addArticle(@RequestBody ArticleCreateRequest request) {
        Long articleId = articleService.addArticle(request);
        return ResponseEntity.
                created(URI.create("/articles/" + articleId)).
                build();
    }
}
