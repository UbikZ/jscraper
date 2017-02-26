package com.ubikz.scraper.api.controller;

import com.ubikz.scraper.api.controller.filter.FeedFilterBody;
import com.ubikz.scraper.api.controller.request.FeedRequestBody;
import com.ubikz.scraper.core.app.context.FeedContext;
import com.ubikz.scraper.core.app.dto.FeedDto;
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
    public String create(@RequestBody final FeedRequestBody request) throws Exception {
        return this.sendResponse(this.feedContext.createFeed(request));
    }

    @RequestMapping(value = "/feed", method = RequestMethod.GET, produces = "application/json")
    public String get(final FeedFilterBody filter) throws Exception {
        return this.sendResponse(this.feedContext.getAllFeeds(filter));
    }

    /**
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/feed/{id}", method = RequestMethod.PUT, produces = "application/json")
    public String update(@PathVariable("id") final int id, @RequestBody final FeedRequestBody request) throws Exception {
        return this.sendResponse(this.feedContext.updateFeed(id, request));
    }

    /**
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/feed/{id}", method = RequestMethod.GET, produces = "application/json")
    public String getById(@PathVariable("id") final int id) throws Exception {
        return this.sendResponse(this.feedContext.getFeedById(id));
    }

    /**
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/feed/{id}", method = RequestMethod.DELETE, produces = "application/json")
    public String deleteById(@PathVariable("id") final int id) throws Exception {
        return this.sendResponse(this.feedContext.deleteFeedById(id));
    }
}