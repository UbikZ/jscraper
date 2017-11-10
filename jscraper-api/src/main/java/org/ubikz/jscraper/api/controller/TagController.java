package org.ubikz.jscraper.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.ubikz.jscraper.api.controller.filter.TagFilterBody;
import org.ubikz.jscraper.api.controller.filter.TagProhibitedFilterBody;
import org.ubikz.jscraper.api.controller.request.TagProhibitedRequestBody;
import org.ubikz.jscraper.api.controller.request.TagRequestBody;
import org.ubikz.jscraper.api.core.app.context.TagContext;
import org.ubikz.jscraper.api.core.app.context.TagProhibitedContext;
import org.ubikz.jscraper.api.core.app.service.message.BaseMessage;

@RestController
public class TagController extends ApiController {
    private final String uriPath = "/tag";
    private final String uriProhibited = "/prohibited";
    private TagContext tagContext;
    private TagProhibitedContext tagProhibitedContext;

    /**
     * @param tagContext
     */
    @Autowired
    public TagController(TagContext tagContext, TagProhibitedContext tagProhibitedContext) {
        this.tagContext = tagContext;
        this.tagProhibitedContext = tagProhibitedContext;
    }

    /**
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = uriPath, method = RequestMethod.POST, produces = "application/json")
    public String create(@RequestBody final TagRequestBody request) throws Exception {
        return this.sendResponse(this.tagContext.create(request));
    }

    @RequestMapping(value = uriPath, method = RequestMethod.GET, produces = "application/json")
    public String get(final TagFilterBody filter) throws Exception {
        BaseMessage msg = this.tagContext.getAll(filter);
        msg.setTotal(this.tagContext.count(filter));

        return this.sendResponse(msg);
    }

    /**
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping(value = uriPath + "/{id}", method = RequestMethod.PUT, produces = "application/json")
    public String update(@PathVariable("id") final int id, @RequestBody final TagRequestBody request) throws Exception {
        return this.sendResponse(this.tagContext.update(id, request));
    }

    /**
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping(value = uriPath + "/{id}", method = RequestMethod.GET, produces = "application/json")
    public String getById(@PathVariable("id") final int id) throws Exception {
        return this.sendResponse(this.tagContext.getById(id));
    }

    /**
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping(value = uriPath + "/{id}", method = RequestMethod.DELETE, produces = "application/json")
    public String deleteById(@PathVariable("id") final int id) throws Exception {
        return this.sendResponse(this.tagContext.deleteById(id));
    }

    /**
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = uriPath + uriProhibited, method = RequestMethod.POST, produces = "application/json")
    public String createProhibited(@RequestBody final TagProhibitedRequestBody request) throws Exception {
        return this.sendResponse(this.tagProhibitedContext.create(request));
    }

    @RequestMapping(value = uriPath + uriProhibited, method = RequestMethod.GET, produces = "application/json")
    public String getProhibited(final TagProhibitedFilterBody filter) throws Exception {
        return this.sendResponse(this.tagProhibitedContext.getAll(filter));
    }

    /**
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping(value = uriPath + uriProhibited + "/{id}", method = RequestMethod.PUT, produces = "application/json")
    public String updateProhibited(@PathVariable("id") final int id, @RequestBody final TagProhibitedRequestBody request) throws Exception {
        return this.sendResponse(this.tagProhibitedContext.update(id, request));
    }

    /**
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping(value = uriPath + uriProhibited + "/{id}", method = RequestMethod.GET, produces = "application/json")
    public String getProhibitedById(@PathVariable("id") final int id) throws Exception {
        return this.sendResponse(this.tagProhibitedContext.getById(id));
    }

    /**
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping(value = uriPath + uriProhibited + "/{id}", method = RequestMethod.DELETE, produces = "application/json")
    public String deleteProhibitedById(@PathVariable("id") final int id) throws Exception {
        return this.sendResponse(this.tagProhibitedContext.deleteById(id));
    }
}