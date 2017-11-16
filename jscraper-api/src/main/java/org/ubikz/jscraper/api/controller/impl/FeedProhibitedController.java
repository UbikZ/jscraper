package org.ubikz.jscraper.api.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.ubikz.jscraper.api.context.impl.FeedProhibitedContext;
import org.ubikz.jscraper.api.controller.BaseController;
import org.ubikz.jscraper.api.controller.model.filter.impl.FeedProhibitedFilterBody;
import org.ubikz.jscraper.api.controller.model.request.impl.FeedProhibitedRequestBody;

@RestController
@RequestMapping(value = "/feed-prohibited")
public class FeedProhibitedController extends BaseController<FeedProhibitedContext, FeedProhibitedRequestBody, FeedProhibitedFilterBody> {

    @Autowired
    public FeedProhibitedController(FeedProhibitedContext feedProhibitedContext) {
        this.context = feedProhibitedContext;
    }
}