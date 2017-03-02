package com.ubikz.scraper.core.provider.rss;

import com.rometools.rome.feed.synd.SyndCategory;
import com.rometools.rome.feed.synd.SyndEntry;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RssEntry {
    private final String[] tagList = new String[]{""};
    private final HashSet<String> prohibitedTags = new HashSet<>(Arrays.asList(tagList));
    private SyndEntry entry;

    public RssEntry(SyndEntry entry) {
        this.entry = entry;
    }

    public List<String> buildTagList() {
        List<String> titleTagList = new ArrayList<>();
        List<String> categoryTagList = new ArrayList<>();

        // We build the tags from title
        if (entry.getTitle() != null) {
            List<String> splittedTitle = Arrays.asList(entry.getTitle().split(" "));
            titleTagList = splittedTitle
                    .stream()
                    .filter(name -> !prohibitedTags.contains(name))
                    .collect(Collectors.toList());
        }

        // We build the tags from categories
        if (entry.getCategories() != null) {
            categoryTagList = entry.getCategories()
                    .stream()
                    .map(SyndCategory::getName)
                    .filter(name -> !prohibitedTags.contains(name))
                    .collect(Collectors.toList());
        }

        return Stream.concat(titleTagList.stream(), categoryTagList.stream()).collect(Collectors.toList());
    }

    public SyndEntry getEntry() {
        return entry;
    }
}