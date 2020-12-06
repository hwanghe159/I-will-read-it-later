package com.woowacourse.iwillreaditlater;

import com.woowacourse.iwillreaditlater.domain.Article;

public class ArticleFixture {

    public static final Long ARTICLE_ID_1 = 1L;
    public static final Long ARTICLE_ID_2 = 2L;
    public static final Long ARTICLE_ID_3 = 3L;
    public static final String ARTICLE_URL_1 = "URL1";
    public static final String ARTICLE_URL_2 = "URL2";
    public static final String ARTICLE_URL_3 = "URL3";
    public static final String ARTICLE_TITLE_1 = "제목1";
    public static final String ARTICLE_TITLE_2 = "제목2";
    public static final String ARTICLE_TITLE_3 = "제목3";
    public static final String ARTICLE_CONTENT_1 = "내용1";
    public static final String ARTICLE_CONTENT_2 = "내용2";
    public static final String ARTICLE_CONTENT_3 = "내용3";
    public static final String ARTICLE_AUTHOR_1 = "작성자1";
    public static final String ARTICLE_AUTHOR_2 = "작성자2";
    public static final String ARTICLE_AUTHOR_3 = "작성자3";
    public static final String ARTICLE_IMAGE_SOURCE_1 = "이미지URL1";
    public static final String ARTICLE_IMAGE_SOURCE_2 = "이미지URL2";
    public static final String ARTICLE_IMAGE_SOURCE_3 = "이미지URL3";
    public static final Article ARTICLE_1 = new Article(ARTICLE_ID_1, ARTICLE_URL_1, ARTICLE_TITLE_1, ARTICLE_CONTENT_1, ARTICLE_AUTHOR_1, ARTICLE_IMAGE_SOURCE_1);
    public static final Article ARTICLE_2 = new Article(ARTICLE_ID_2, ARTICLE_URL_2, ARTICLE_TITLE_2, ARTICLE_CONTENT_2, ARTICLE_AUTHOR_2, ARTICLE_IMAGE_SOURCE_2);
    public static final Article ARTICLE_3 = new Article(ARTICLE_ID_3, ARTICLE_URL_3, ARTICLE_TITLE_3, ARTICLE_CONTENT_3, ARTICLE_AUTHOR_3, ARTICLE_IMAGE_SOURCE_3);
}
