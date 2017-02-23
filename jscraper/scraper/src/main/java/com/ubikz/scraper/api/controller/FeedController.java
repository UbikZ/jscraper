package com.ubikz.scraper.api.controller;

import com.ubikz.scraper.api.context.FeedContext;
import com.ubikz.scraper.api.dto.FeedDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class FeedController extends AbstractController {
    private FeedContext feedContext;

    /**
     * @param feedContext
     */
    @Autowired
    public FeedController(FeedContext feedContext) {
        this.feedContext = feedContext;
    }

    /**
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/feed", method = RequestMethod.POST, produces = "application/json")
    public String create(@RequestBody FeedDto request) throws Exception {
        return this.sendResponse(this.feedContext.createFeed(request));
    }

    /**
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/feed/{id}", method = RequestMethod.GET, produces = "application/json")
    public String create(@PathVariable("id") int id) throws Exception {
        return this.sendResponse(this.feedContext.getFeedById(id));
    }
}