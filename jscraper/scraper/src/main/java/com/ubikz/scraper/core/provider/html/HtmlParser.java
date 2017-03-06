package com.ubikz.scraper.core.provider.html;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class HtmlParser {
    private final List<String> allowedExt = Arrays.asList("jpg", "png", "jpeg", "gif", "gifv");

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
    public List<String> searchPictures() {
        return this.document
                .select("a")
                .stream()
//                .filter(element -> this.checkLinkExtension(element.attr("href")))
                .map(element -> element.attr("href"))
                .collect(Collectors.toList());
    }

    /**
     * @param attribute
     * @return
     */
    private boolean checkLinkExtension(String attribute) {
        boolean valid = false;
        for (String extension : this.allowedExt) {
            if (attribute.toLowerCase().contains("." + extension)) {
                valid = true;
                break;
            }
        }

        return valid;
    }
}