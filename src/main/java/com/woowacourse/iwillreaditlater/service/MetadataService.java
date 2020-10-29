package com.woowacourse.iwillreaditlater.service;

import com.woowacourse.iwillreaditlater.dto.MetadataResponse;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;

@Service
public class MetadataService {

    public MetadataResponse getMetadata(String url) throws MalformedURLException {
        try {
            Document document = Jsoup.connect(url).get();
            return new MetadataResponse(document.title(), document.text());
        } catch (Exception e) {
            throw new MalformedURLException();
        }
    }
}
