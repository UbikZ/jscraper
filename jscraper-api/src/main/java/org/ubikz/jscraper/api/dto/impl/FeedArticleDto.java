package org.ubikz.jscraper.api.dto.impl;

import org.ubikz.jscraper.api.dto.BaseDto;

import java.util.ArrayList;
import java.util.List;

public class FeedArticleDto extends BaseDto {
    private String url;
    private String author;
    private List<String> tagList = new ArrayList<>();
    private List<String> pictureList = new ArrayList<>();

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

    public List<String> getPictureList() {
        return pictureList;
    }

    public void setPictureList(List<String> pictureList) {
        this.pictureList = pictureList;
    }

    @Override
    public String toString() {
        return "FeedArticleDto{"
                + "url='" + url + '\''
                + ", author='" + author + '\''
                + ", tagList=" + tagList
                + ", pictureList=" + pictureList
                + "} " + super.toString();
    }
}