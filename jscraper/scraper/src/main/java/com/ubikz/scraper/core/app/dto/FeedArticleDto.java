package com.ubikz.scraper.core.app.dto;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class FeedArticleDto extends AbstractDto {
    private String url;
    private String author;
    private List<String> tagList = new ArrayList<>();
    private List<URL> imageList = new ArrayList<>();

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public List<String> getTagList() {
        return tagList;
    }

    public void setTagList(List<String> tagList) {
        this.tagList = tagList;
    }

    public List<URL> getImageList() {
        return imageList;
    }

    public void setImageList(List<URL> imageList) {
        this.imageList = imageList;
    }
}