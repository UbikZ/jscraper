package com.ubikz.scraper.api.controller;

import com.ubikz.scraper.api.controller.filter.FeedProhibitedFilterBody;
import com.ubikz.scraper.api.controller.request.FeedProhibitedRequestBody;
import com.ubikz.scraper.core.app.context.FeedProhibitedContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class FeedController extends AbstractController {
    private final String uriPath = "/feed";
    private final String uriProhibited = "/prohibited";
    private FeedProhibitedContext feedContext;
    private FeedProhibitedContext feedProhibitedContext;

    /**
     * @param feedContext
     */
    @Autowired
    public FeedController(FeedProhibitedContext feedContext, FeedProhibitedContext feedProhibitedContext) {
        this.feedContext = feedContext;
        this.feedProhibitedContext = feedProhibitedContext;
    }

    /**
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = uriPath, method = RequestMethod.POST, produces = "application/json")
    public String create(@RequestBody final FeedProhibitedRequestBody request) throws Exception {
        return this.sendResponse(this.feedContext.createFeedProhibited(request));
    }

    @RequestMapping(value = uriPath, method = RequestMethod.GET, produces = "application/json")
    public String get(final FeedProhibitedFilterBody filter) throws Exception {
        return this.sendResponse(this.feedContext.getAllFeedProhibiteds(filter));
    }

    /**
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping(value = uriPath + "/{id}", method = RequestMethod.PUT, produces = "application/json")
    public String update(@PathVariable("id") final int id, @RequestBody final FeedProhibitedRequestBody request) throws Exception {
        return this.sendResponse(this.feedContext.updateFeedProhibited(id, request));
    }

    /**
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping(value = uriPath + "/{id}", method = RequestMethod.GET, produces = "application/json")
    public String getById(@PathVariable("id") final int id) throws Exception {
        return this.sendResponse(this.feedContext.getFeedProhibitedById(id));
    }

    /**
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping(value = uriPath + "/{id}", method = RequestMethod.DELETE, produces = "application/json")
    public String deleteById(@PathVariable("id") final int id) throws Exception {
        return this.sendResponse(this.feedContext.deleteFeedProhibitedById(id));
    }

    /**
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = uriPath + uriProhibited, method = RequestMethod.POST, produces = "application/json")
    public String createProhibited(@RequestBody final FeedProhibitedRequestBody request) throws Exception {
        return this.sendResponse(this.feedProhibitedContext.createFeedProhibited(request));
    }

    @RequestMapping(value = uriPath + uriProhibited, method = RequestMethod.GET, produces = "application/json")
    public String getProhibited(final FeedProhibitedFilterBody filter) throws Exception {
        return this.sendResponse(this.feedProhibitedContext.getAllFeedProhibiteds(filter));
    }

    /**
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping(value = uriPath + uriProhibited + "/{id}", method = RequestMethod.PUT, produces = "application/json")
    public String updateProhibited(@PathVariable("id") final int id, @RequestBody final FeedProhibitedRequestBody request) throws Exception {
        return this.sendResponse(this.feedProhibitedContext.updateFeedProhibited(id, request));
    }

    /**
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping(value = uriPath + uriProhibited + "/{id}", method = RequestMethod.GET, produces = "application/json")
    public String getProhibitedById(@PathVariable("id") final int id) throws Exception {
        return this.sendResponse(this.feedProhibitedContext.getFeedProhibitedById(id));
    }

    /**
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping(value = uriPath + uriProhibited + "/{id}", method = RequestMethod.DELETE, produces = "application/json")
    public String deleteProhibitedById(@PathVariable("id") final int id) throws Exception {
        return this.sendResponse(this.feedProhibitedContext.deleteFeedProhibitedById(id));
    }
}