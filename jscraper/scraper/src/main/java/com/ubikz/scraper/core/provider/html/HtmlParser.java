package com.ubikz.scraper.core.provider.html;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.net.URL;

public class HtmlParser {
    private URL url;
    private String content;
    private Document document;

    public HtmlParser(URL url) throws IOException {
        this.url = url;
        this.document = Jsoup.connect(url.toString()).get();
    }

    public HtmlParser(String content) throws IOException {
        this.content = content;
        this.document = Jsoup.parse(content);
    }

    public Document getDocument() {
        return document;
    }
}