package com.woowacourse.iwillreaditlater.service;

import com.woowacourse.iwillreaditlater.domain.Article;
import com.woowacourse.iwillreaditlater.domain.ArticleRepository;
import com.woowacourse.iwillreaditlater.dto.ArticleCreateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ArticleService {

    private final ArticleRepository articleRepository;

    public Long addArticle(ArticleCreateRequest request) {
        Article article = articleRepository.save(request.toArticle());
        return article.getId();
    }
}
