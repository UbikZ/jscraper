package com.ubikz.scraper.api.controller;

import com.ubikz.scraper.api.dal.FeedDal;
import com.ubikz.scraper.api.dal.filter.FeedDalFilter;
import com.ubikz.scraper.api.dal.request.FeedDalRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController extends Throwable {
    private FeedDal feedDal;

    @Autowired
    public TestController(FeedDal feedDal) {
        this.feedDal = feedDal;
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET, produces = "application/json")
    public int test() {
        FeedDalRequest feedDalRequest = new FeedDalRequest();
        feedDalRequest.setUrl("http://test.com");
        feedDalRequest.setEnabled(false);
        feedDalRequest.setLabel("<3");

        System.out.println(" >>>>>> " + this.feedDal.createFeed(feedDalRequest));

        FeedDalFilter feedDalFilter = new FeedDalFilter();
        feedDalFilter.setId(1);

        System.out.println(" >>>>>> " + this.feedDal.getFeed(feedDalFilter));

        feedDalFilter.setUrl("http://test.com");

        System.out.println(" >>>>>> " + this.feedDal.getFeed(feedDalFilter));

        feedDalFilter.setEnabled(true);

        System.out.println(" >>>>>> " + this.feedDal.getFeed(feedDalFilter));

        return 1;
    }
}