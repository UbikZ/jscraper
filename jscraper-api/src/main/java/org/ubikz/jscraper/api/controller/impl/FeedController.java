package org.ubikz.jscraper.api.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.ubikz.jscraper.api.context.impl.FeedContext;
import org.ubikz.jscraper.api.controller.BaseController;
import org.ubikz.jscraper.api.controller.model.filter.impl.FeedFilterBody;
import org.ubikz.jscraper.api.controller.model.request.impl.FeedRequestBody;

@RestController
@RequestMapping(value = "/feed")
public class FeedController extends BaseController<FeedContext, FeedRequestBody, FeedFilterBody> {
    @Autowired
    public FeedController(FeedContext feedContext) {
        this.context = feedContext;
    }
}