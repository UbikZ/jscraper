package org.ubikz.jscraper.api.core.provider.rss;

import com.rometools.rome.feed.synd.SyndCategory;
import com.rometools.rome.feed.synd.SyndContent;
import com.rometools.rome.feed.synd.SyndEntry;
import org.ubikz.jscraper.api.core.provider.html.HtmlParser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RssEntry {
    private final String pattern = "[a-z0-9]+";
    private SyndEntry entry;

    public RssEntry(SyndEntry entry) {
        this.entry = entry;
    }

    /**
     * @param prohibitedTags
     * @return
     */
    public List<String> buildTagList(List<String> prohibitedTags) {
        List<String> titleTagList = new ArrayList<>();
        List<String> categoryTagList = new ArrayList<>();

        // We build the tags from title
        if (entry.getTitle() != null) {
            List<String> splittedTitle = Arrays.asList(entry.getTitle().split(" "));
            titleTagList = splittedTitle
                    .stream()
                    .map(String::toLowerCase)
                    .filter(name -> !prohibitedTags.contains(name) && Pattern.matches(pattern, name))
                    .collect(Collectors.toList());
        }

        // We build the tags from categories
        if (entry.getCategories() != null) {
            categoryTagList = entry.getCategories()
                    .stream()
                    .map(SyndCategory::getName)
                    .map(String::toLowerCase)
                    .filter(name -> !prohibitedTags.contains(name) && Pattern.matches(pattern, name))
                    .collect(Collectors.toList());
        }

        return Stream.concat(titleTagList.stream(), categoryTagList.stream()).collect(Collectors.toList());
    }

    /**
     * @return
     * @throws Exception
     */
    public List<String> getPictureLinks(String regex) throws Exception {
        List<String> links = new ArrayList<>();
        for (SyndContent entryContent : this.entry.getContents()) {
            HtmlParser htmlParser = new HtmlParser(entryContent.getValue());
            links.addAll(htmlParser.searchPictures(regex));
        }

        return links;
    }

    public SyndEntry getEntry() {
        return entry;
    }
}