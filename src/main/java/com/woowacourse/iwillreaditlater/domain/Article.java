package com.woowacourse.iwillreaditlater.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Article extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String url;

    private String title;

    @Lob
    private String content;

    private String author;

    private String imageSource;

    public Article(String url, String title, String content, String author, String imageSource) {
        this.url = url;
        this.title = title;
        this.content = content;
        this.author = author;
        this.imageSource = imageSource;
    }
}
