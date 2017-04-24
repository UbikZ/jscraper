package com.ubikz.scraper.api.controller;

import com.ubikz.scraper.api.controller.filter.FeedItemFilterBody;
import com.ubikz.scraper.api.controller.request.FeedItemRequestBody;
import com.ubikz.scraper.core.app.context.FeedItemContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class FeedItemController extends ApiController {
    private final String uriPath = "/feed-item";
    private FeedItemContext feedItemContext;

    /**
     * @param feedItemContext
     */
    @Autowired
    public FeedItemController(FeedItemContext feedItemContext) {
        this.feedItemContext = feedItemContext;
    }

    /**
     * @return
     * @throws Exception
     */
    @RequestMapping(value = uriPath + "/generate", method = RequestMethod.GET, produces = "application/json")
    public String createItems() throws Exception {
        return this.sendResponse(this.feedItemContext.generate());
    }

    /**
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = uriPath, method = RequestMethod.POST, produces = "application/json")
    public String create(@RequestBody final FeedItemRequestBody request) throws Exception {
        return this.sendResponse(this.feedItemContext.create(request));
    }

    @RequestMapping(value = uriPath, method = RequestMethod.GET, produces = "application/json")
    public String get(final FeedItemFilterBody filter) throws Exception {
        return this.sendResponse(this.feedItemContext.getAll(filter));
    }

    /**
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping(value = uriPath + "/{id}", method = RequestMethod.PUT, produces = "application/json")
    public String update(@PathVariable("id") final int id, @RequestBody final FeedItemRequestBody request) throws Exception {
        return this.sendResponse(this.feedItemContext.update(id, request));
    }

    /**
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping(value = uriPath + "/{id}", method = RequestMethod.GET, produces = "application/json")
    public String getById(@PathVariable("id") final int id) throws Exception {
        return this.sendResponse(this.feedItemContext.getById(id));
    }

    /**
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping(value = uriPath + "/{id}", method = RequestMethod.DELETE, produces = "application/json")
    public String deleteById(@PathVariable("id") final int id) throws Exception {
        return this.sendResponse(this.feedItemContext.deleteById(id));
    }
}