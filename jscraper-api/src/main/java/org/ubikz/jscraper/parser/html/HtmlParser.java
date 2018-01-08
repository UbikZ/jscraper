package org.ubikz.jscraper.parser.html;

import com.google.common.base.Strings;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.stream.Collectors;

public class HtmlParser {
    public static final String ATTR_HREF = "href";
    private URL url;
    private String content;
    private Document document;

    public HtmlParser(URL url) throws IOException {
        this.url = url;
        this.document = Jsoup.connect(url.toString()).get();
    }

    public HtmlParser(String content) {
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
                .filter(element -> {
                    String attr = element.attr(ATTR_HREF);

                    return !Strings.isNullOrEmpty(attr) && !attr.matches(regex);
                })
                .map(element -> element.attr(ATTR_HREF))
                .collect(Collectors.toList());
    }
}
