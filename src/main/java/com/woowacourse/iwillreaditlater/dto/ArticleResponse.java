package com.woowacourse.iwillreaditlater.dto;

import com.woowacourse.iwillreaditlater.domain.Article;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class ArticleResponse {

    private Long id;

    private String url;

    private String title;

    private String content;

    private String author;

    private String imageSource;

    public ArticleResponse(Article article) {
        this.id = article.getId();
        this.url = article.getUrl();
        this.title = article.getTitle();
        this.content = article.getContent();
        this.author = article.getAuthor();
        this.imageSource = article.getImageSource();
    }
}
