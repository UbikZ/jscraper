package org.ubikz.jscraper.api.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.ubikz.jscraper.api.context.impl.TagProhibitedContext;
import org.ubikz.jscraper.api.controller.BaseController;
import org.ubikz.jscraper.api.controller.model.filter.impl.TagProhibitedFilterBody;
import org.ubikz.jscraper.api.controller.model.request.impl.TagProhibitedRequestBody;

@RestController
@RequestMapping(value = "/tag-prohibited")
public class TagProhibitedController extends BaseController<TagProhibitedContext, TagProhibitedRequestBody, TagProhibitedFilterBody> {
    @Autowired
    public TagProhibitedController(TagProhibitedContext tagContext) {
        this.context = tagContext;
    }
}