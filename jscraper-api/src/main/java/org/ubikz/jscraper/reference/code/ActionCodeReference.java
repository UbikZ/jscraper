package org.ubikz.jscraper.reference.code;

import org.ubikz.jscraper.reference.BaseReference;
import org.ubikz.jscraper.reference.IReference;

public enum ActionCodeReference implements IReference<ActionCodeReference> {
    FEED_CREATED("feed.created"),
    FEED_UPDATED("feed.updated"),
    FEED_DELETED("feed.deleted"),
    FEED_GET_ONE("feed.get.one"),
    FEED_GET_ALL("feed.get.all"),
    FEED_ITEM_CREATED("feedItem.created"),
    FEED_ITEM_UPDATED("feedItem.updated"),
    FEED_ITEM_DELETED("feedItem.deleted"),
    FEED_ITEM_GET_ONE("feedItem.get.one"),
    FEED_ITEM_GET_ALL("feedItem.get.all"),
    FEED_PROHIBITED_CREATED("feedProhibited.created"),
    FEED_PROHIBITED_UPDATED("feedProhibited.updated"),
    FEED_PROHIBITED_DELETED("feedProhibited.deleted"),
    FEED_PROHIBITED_GET_ONE("feedProhibited.get.one"),
    FEED_PROHIBITED_GET_ALL("feedProhibited.get.all"),
    FEED_TYPE_CREATED("feedType.created"),
    FEED_TYPE_UPDATED("feedType.updated"),
    FEED_TYPE_DELETED("feedType.deleted"),
    FEED_TYPE_GET_ONE("feedType.get.one"),
    FEED_TYPE_GET_ALL("feedType.get.all"),
    TAG_CREATED("tag.created"),
    TAG_UPDATED("tag.updated"),
    TAG_DELETED("tag.deleted"),
    TAG_GET_ONE("tag.get.one"),
    TAG_GET_ALL("tag.get.all"),
    TAG_PROHIBITED_CREATED("tagProhibited.created"),
    TAG_PROHIBITED_UPDATED("tagProhibited.updated"),
    TAG_PROHIBITED_DELETED("tagProhibited.deleted"),
    TAG_PROHIBITED_GET_ONE("tagProhibited.get.one"),
    TAG_PROHIBITED_GET_ALL("tagProhibited.get.all"),
    USER_CREATED("user.created"),
    USER_UPDATED("user.updated"),
    USER_DELETED("user.deleted"),
    USER_GET_ONE("user.get.one"),
    USER_GET_ALL("user.get.all");


    private String name;

    ActionCodeReference(String name) {
        this.name = name;
    }

    public String success() {
        return name + ".success";
    }

    public String fail() {
        return name + ".fail";
    }

    @Override
    public ActionCodeReference getFromValue(String value) {
        return new BaseReference<ActionCodeReference>().getFromValue(values(), value);
    }
}
