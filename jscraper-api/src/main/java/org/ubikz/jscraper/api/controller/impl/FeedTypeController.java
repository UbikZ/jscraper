package org.ubikz.jscraper.api.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.ubikz.jscraper.api.context.impl.FeedTypeContext;
import org.ubikz.jscraper.api.controller.BaseController;
import org.ubikz.jscraper.api.controller.model.filter.impl.FeedTypeFilterBody;
import org.ubikz.jscraper.api.controller.model.request.impl.FeedTypeRequestBody;

@RestController
@RequestMapping(value = "/feed-type")
public class FeedTypeController extends BaseController<FeedTypeContext, FeedTypeRequestBody, FeedTypeFilterBody> {

    @Autowired
    public FeedTypeController(FeedTypeContext feedTypeContext) {
        this.context = feedTypeContext;
    }
}