package org.ubikz.jscraper.api.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.ubikz.jscraper.api.context.impl.FeedItemContext;
import org.ubikz.jscraper.api.controller.BaseController;
import org.ubikz.jscraper.api.controller.model.filter.impl.FeedItemFilterBody;
import org.ubikz.jscraper.api.controller.model.request.impl.FeedItemRequestBody;

@RestController
@RequestMapping(value = "/feed-item")
public class FeedItemController extends BaseController<FeedItemContext, FeedItemRequestBody, FeedItemFilterBody> {
    @Autowired
    public FeedItemController(FeedItemContext feedItemContext) {
        this.context = feedItemContext;
    }

    /**
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/generate", method = RequestMethod.GET, produces = "application/json")
    public String createItems() throws Exception {
        return this.sendResponse(this.context.generate());
    }
}