package com.ubikz.scraper.api.controller;

import com.ubikz.scraper.core.app.context.FeedContext;
import com.ubikz.scraper.core.app.dto.FeedDto;
import com.ubikz.scraper.core.app.service.message.BaseMessage;
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
    public String create(@RequestBody final FeedDto request) throws Exception {
        return this.sendResponse(this.feedContext.createFeed(request));
    }

    @RequestMapping(value = "/feed", method = RequestMethod.GET, produces = "application/json")
    public String get(@RequestParam(required = false) final Boolean enabled) throws Exception {

        this.logger.debug("Enabled => " + enabled);
        BaseMessage message = this.feedContext.getAllFeeds(enabled);
        return this.sendResponse(message);
    }

    /**
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/feed/{id}", method = RequestMethod.PUT, produces = "application/json")
    public String update(@PathVariable("id") final int id, @RequestBody final FeedDto request) throws Exception {
        request.setId(id);
        return this.sendResponse(this.feedContext.updateFeed(request));
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
}