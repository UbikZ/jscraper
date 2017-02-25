package com.ubikz.scraper.api.controller;

import com.ubikz.scraper.core.app.context.TagContext;
import com.ubikz.scraper.core.app.dto.TagDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class TagController extends AbstractController {
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
    @RequestMapping(value = "/tag", method = RequestMethod.POST, produces = "application/json")
    public String create(@RequestBody final TagDto request) throws Exception {
        return this.sendResponse(this.tagContext.createTag(request));
    }

    @RequestMapping(value = "/tag", method = RequestMethod.GET, produces = "application/json")
    public String get(@RequestParam(required = false) final Boolean enabled) throws Exception {
        return this.sendResponse(this.tagContext.getAllTags(enabled));
    }

    /**
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/tag/{id}", method = RequestMethod.PUT, produces = "application/json")
    public String update(@PathVariable("id") final int id, @RequestBody final TagDto request) throws Exception {
        request.setId(id);
        return this.sendResponse(this.tagContext.updateTag(request));
    }

    /**
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/tag/{id}", method = RequestMethod.GET, produces = "application/json")
    public String getById(@PathVariable("id") final int id) throws Exception {
        return this.sendResponse(this.tagContext.getTagById(id));
    }

    /**
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/tag/{id}", method = RequestMethod.DELETE, produces = "application/json")
    public String deleteById(@PathVariable("id") final int id) throws Exception {
        return this.sendResponse(this.tagContext.deleteTagById(id));
    }
}