package com.woowacourse.iwillreaditlater.service;

import com.woowacourse.iwillreaditlater.domain.Article;
import com.woowacourse.iwillreaditlater.domain.ArticleRepository;
import com.woowacourse.iwillreaditlater.dto.ArticleCreateRequest;
import com.woowacourse.iwillreaditlater.dto.ArticleResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ArticleService {

    private final ArticleRepository articleRepository;

    public List<ArticleResponse> getArticles() {
        List<Article> articles = articleRepository.findAll();

        return articles.stream()
            .map(ArticleResponse::new)
            .collect(Collectors.toList());
    }

    public Long addArticle(ArticleCreateRequest request) {
        Article article = articleRepository.save(request.toArticle());

        return article.getId();
    }
}
