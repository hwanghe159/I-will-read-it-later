package com.woowacourse.iwillreaditlater.dto;

import com.woowacourse.iwillreaditlater.domain.Article;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ArticleCreateRequest {

    private String url;

    private String title;

    private String content;

    private String author;

    private String imageSource;

    public Article toArticle() {
        return new Article(url, title, content, author, imageSource);
    }
}
