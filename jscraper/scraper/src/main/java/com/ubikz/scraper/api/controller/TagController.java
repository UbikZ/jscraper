package com.ubikz.scraper.api.controller;

import com.ubikz.scraper.api.controller.filter.TagFilterBody;
import com.ubikz.scraper.api.controller.request.TagRequestBody;
import com.ubikz.scraper.core.app.context.TagContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class TagController extends AbstractController {
    private final String uriPath = "/tag";
    private TagContext tagContext;

    /**
     * @param tagContext
     */
    @Autowired
    public TagController(TagContext tagContext) {
        this.tagContext = tagContext;
    }

    /**
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = uriPath, method = RequestMethod.POST, produces = "application/json")
    public String create(@RequestBody final TagRequestBody request) throws Exception {
        return this.sendResponse(this.tagContext.createTag(request));
    }

    @RequestMapping(value = uriPath, method = RequestMethod.GET, produces = "application/json")
    public String get(final TagFilterBody filter) throws Exception {
        return this.sendResponse(this.tagContext.getAllTags(filter));
    }

    /**
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping(value = uriPath + "/{id}", method = RequestMethod.PUT, produces = "application/json")
    public String update(@PathVariable("id") final int id, @RequestBody final TagRequestBody request) throws Exception {
        return this.sendResponse(this.tagContext.updateTag(id, request));
    }

    /**
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping(value = uriPath + "/{id}", method = RequestMethod.GET, produces = "application/json")
    public String getById(@PathVariable("id") final int id) throws Exception {
        return this.sendResponse(this.tagContext.getTagById(id));
    }

    /**
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping(value = uriPath + "/{id}", method = RequestMethod.DELETE, produces = "application/json")
    public String deleteById(@PathVariable("id") final int id) throws Exception {
        return this.sendResponse(this.tagContext.deleteTagById(id));
    }
}