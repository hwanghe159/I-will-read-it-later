package com.woowacourse.iwillreaditlater.service;

import com.woowacourse.iwillreaditlater.dto.MetadataResponse;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;

@Service
public class MetadataService {

    public MetadataResponse getMetadata(String url) throws MalformedURLException {
        Document document;
        try {
            document = Jsoup.connect(url).get();
        } catch (Exception e) {
            throw new MalformedURLException();
        }

        String title = document.title();
        String author;
        String content = document.text();
        String imageSource;
        try {
            author = document.select("meta[name=by]").first().attr("content");
        } catch (Exception e) {
            author = "";
        }
        try {
            imageSource = document.select("meta[property=og:image]").first().attr("content");
        } catch (Exception e) {
            imageSource = "";
        }

        return new MetadataResponse(title, author, content, imageSource);
    }
}
