package com.ubikz.scraper.api.controller;

import com.ubikz.scraper.api.controller.filter.FeedTypeFilterBody;
import com.ubikz.scraper.api.controller.request.FeedTypeRequestBody;
import com.ubikz.scraper.core.app.context.FeedTypeContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class FeedTypeController extends ApiController {
    private final String uriPath = "/feed-type";
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
    @RequestMapping(value = uriPath, method = RequestMethod.POST, produces = "application/json")
    public String create(@RequestBody final FeedTypeRequestBody request) throws Exception {
        return this.sendResponse(this.feedTypeContext.create(request));
    }

    @RequestMapping(value = uriPath, method = RequestMethod.GET, produces = "application/json")
    public String get(final FeedTypeFilterBody filter) throws Exception {
        return this.sendResponse(this.feedTypeContext.getAll(filter));
    }

    /**
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping(value = uriPath + "/{id}", method = RequestMethod.PUT, produces = "application/json")
    public String update(@PathVariable("id") final int id, @RequestBody final FeedTypeRequestBody request) throws Exception {
        return this.sendResponse(this.feedTypeContext.update(id, request));
    }

    /**
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping(value = uriPath + "/{id}", method = RequestMethod.GET, produces = "application/json")
    public String getById(@PathVariable("id") final int id) throws Exception {
        return this.sendResponse(this.feedTypeContext.getById(id));
    }

    /**
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping(value = uriPath + "/{id}", method = RequestMethod.DELETE, produces = "application/json")
    public String deleteById(@PathVariable("id") final int id) throws Exception {
        return this.sendResponse(this.feedTypeContext.deleteById(id));
    }
}