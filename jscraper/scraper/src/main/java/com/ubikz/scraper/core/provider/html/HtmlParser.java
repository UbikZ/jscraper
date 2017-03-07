package com.ubikz.scraper.core.provider.html;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.stream.Collectors;

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

    /**
     * @return
     */
    public List<String> searchPictures(String regex) {
        return this.document
                .select("a")
                .stream()
                .filter(element -> !element.attr("href").matches(regex))
                .map(element -> element.attr("href"))
                .collect(Collectors.toList());
    }
}