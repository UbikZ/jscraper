package org.ubikz.jscraper.api.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.ubikz.jscraper.api.context.impl.TagContext;
import org.ubikz.jscraper.api.controller.BaseController;
import org.ubikz.jscraper.api.controller.model.filter.impl.TagFilterBody;
import org.ubikz.jscraper.api.controller.model.request.impl.TagRequestBody;

@RestController
@RequestMapping(value = "/tag")
public class TagController extends BaseController<TagContext, TagRequestBody, TagFilterBody> {
    @Autowired
    public TagController(TagContext tagContext) {
        this.context = tagContext;
    }
}