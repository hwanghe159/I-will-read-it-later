package com.woowacourse.iwillreaditlater.dto;

import com.woowacourse.iwillreaditlater.domain.Article;
import lombok.Getter;

@Getter
public class ArticleResponse {

    private Long id;

    private String url;

    private String title;

    private String content;

    public ArticleResponse(Article article) {
        this.id = article.getId();
        this.url = article.getUrl();
        this.title = article.getTitle();
        this.content = article.getContent();
    }
}
