package org.ubikz.jscraper.api.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.ubikz.jscraper.api.controller.ApiController;
import org.ubikz.jscraper.api.controller.model.filter.impl.FeedFilterBody;
import org.ubikz.jscraper.api.controller.model.filter.impl.FeedProhibitedFilterBody;
import org.ubikz.jscraper.api.controller.model.request.impl.FeedProhibitedRequestBody;
import org.ubikz.jscraper.api.controller.model.request.impl.FeedRequestBody;
import org.ubikz.jscraper.api.context.impl.FeedContext;
import org.ubikz.jscraper.api.context.impl.FeedProhibitedContext;

@RestController
public class FeedController extends ApiController {
    private final String uriPath = "/feed";
    private final String uriProhibited = "/prohibited";
    private FeedContext feedContext;
    private FeedProhibitedContext feedProhibitedContext;

    /**
     * @param feedContext
     */
    @Autowired
    public FeedController(FeedContext feedContext, FeedProhibitedContext feedProhibitedContext) {
        this.feedContext = feedContext;
        this.feedProhibitedContext = feedProhibitedContext;
    }

    /**
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = uriPath, method = RequestMethod.POST, produces = "application/json")
    public String create(@RequestBody final FeedRequestBody request) throws Exception {
        return this.sendResponse(this.feedContext.create(request));
    }

    @RequestMapping(value = uriPath, method = RequestMethod.GET, produces = "application/json")
    public String get(final FeedFilterBody filter) throws Exception {
        return this.sendResponse(this.feedContext.getAll(filter));
    }

    /**
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping(value = uriPath + "/{id}", method = RequestMethod.PUT, produces = "application/json")
    public String update(@PathVariable("id") final int id, @RequestBody final FeedRequestBody request) throws Exception {
        return this.sendResponse(this.feedContext.update(id, request));
    }

    /**
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping(value = uriPath + "/{id}", method = RequestMethod.GET, produces = "application/json")
    public String getById(@PathVariable("id") final int id) throws Exception {
        return this.sendResponse(this.feedContext.getById(id));
    }

    /**
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping(value = uriPath + "/{id}", method = RequestMethod.DELETE, produces = "application/json")
    public String deleteById(@PathVariable("id") final int id) throws Exception {
        return this.sendResponse(this.feedContext.deleteById(id));
    }

    /**
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = uriPath + uriProhibited, method = RequestMethod.POST, produces = "application/json")
    public String createProhibited(@RequestBody final FeedProhibitedRequestBody request) throws Exception {
        return this.sendResponse(this.feedProhibitedContext.create(request));
    }

    @RequestMapping(value = uriPath + uriProhibited, method = RequestMethod.GET, produces = "application/json")
    public String getProhibited(final FeedProhibitedFilterBody filter) throws Exception {
        return this.sendResponse(this.feedProhibitedContext.getAll(filter));
    }

    /**
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping(value = uriPath + uriProhibited + "/{id}", method = RequestMethod.PUT, produces = "application/json")
    public String updateProhibited(@PathVariable("id") final int id, @RequestBody final FeedProhibitedRequestBody request) throws Exception {
        return this.sendResponse(this.feedProhibitedContext.update(id, request));
    }

    /**
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping(value = uriPath + uriProhibited + "/{id}", method = RequestMethod.GET, produces = "application/json")
    public String getProhibitedById(@PathVariable("id") final int id) throws Exception {
        return this.sendResponse(this.feedProhibitedContext.getById(id));
    }

    /**
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping(value = uriPath + uriProhibited + "/{id}", method = RequestMethod.DELETE, produces = "application/json")
    public String deleteProhibitedById(@PathVariable("id") final int id) throws Exception {
        return this.sendResponse(this.feedProhibitedContext.deleteById(id));
    }
}