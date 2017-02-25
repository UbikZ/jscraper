package com.ubikz.scraper.api.controller;

import com.ubikz.scraper.core.app.context.FeedTypeContext;
import com.ubikz.scraper.core.app.dto.FeedTypeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class FeedTypeController extends AbstractController {
    private FeedTypeContext feedTypeContext;

    /**
     * @param feedTypeContext
     */
    @Autowired
    public FeedTypeController(FeedTypeContext feedTypeContext) {
        this.feedTypeContext = feedTypeContext;
    }

    /**
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/feed-type", method = RequestMethod.POST, produces = "application/json")
    public String create(@RequestBody final FeedTypeDto request) throws Exception {
        return this.sendResponse(this.feedTypeContext.createFeedType(request));
    }

    @RequestMapping(value = "/feed-type", method = RequestMethod.GET, produces = "application/json")
    public String get(@RequestParam(required = false) final Boolean enabled) throws Exception {
        return this.sendResponse(this.feedTypeContext.getAllFeedTypes(enabled));
    }

    /**
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/feed-type/{id}", method = RequestMethod.PUT, produces = "application/json")
    public String update(@PathVariable("id") final int id, @RequestBody final FeedTypeDto request) throws Exception {
        request.setId(id);
        return this.sendResponse(this.feedTypeContext.updateFeedType(request));
    }

    /**
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/feed-type/{id}", method = RequestMethod.GET, produces = "application/json")
    public String getById(@PathVariable("id") final int id) throws Exception {
        return this.sendResponse(this.feedTypeContext.getFeedTypeById(id));
    }

    /**
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/feed-type/{id}", method = RequestMethod.DELETE, produces = "application/json")
    public String deleteById(@PathVariable("id") final int id) throws Exception {
        return this.sendResponse(this.feedTypeContext.deleteFeedTypeById(id));
    }
}