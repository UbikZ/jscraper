package com.ubikz.scraper.api.controller;

import com.ubikz.scraper.api.dal.FeedDal;
import com.ubikz.scraper.api.dal.filter.FeedDalFilter;
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
        FeedDalFilter filter = new FeedDalFilter();
        filter.setUrl("test");
        filter.setEnabled(true);
        filter.setId(12345);
        System.out.println(" >>>>>> " + this.feedDal.getFeed(filter));

        return 1;
    }
}