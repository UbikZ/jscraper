package org.ubikz.jscraper.api.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.ubikz.jscraper.api.controller.ApiController;
import org.ubikz.jscraper.api.controller.model.filter.impl.UserFilterBody;
import org.ubikz.jscraper.api.controller.model.request.impl.UserRequestBody;
import org.ubikz.jscraper.api.context.impl.UserContext;
import org.ubikz.jscraper.api.service.model.message.BaseMessage;

@RestController
public class UserController extends ApiController {
    private final String uriPath = "/user";
    private UserContext userContext;

    /**
     * @param userContext
     */
    @Autowired
    public UserController(UserContext userContext) {
        this.userContext = userContext;
    }

    /**
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = uriPath, method = RequestMethod.POST, produces = "application/json")
    public String create(@RequestBody final UserRequestBody request) throws Exception {
        return this.sendResponse(this.userContext.create(request));
    }

    /**
     * @param filter
     * @return
     * @throws Exception
     */
    @RequestMapping(value = uriPath, method = RequestMethod.GET, produces = "application/json")
    public String get(final UserFilterBody filter) throws Exception {
        BaseMessage msg = this.userContext.getAll(filter);
        msg.setTotal(this.userContext.count(filter));

        return this.sendResponse(msg);
    }

    /**
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping(value = uriPath + "/{id}", method = RequestMethod.PUT, produces = "application/json")
    public String update(@PathVariable("id") final int id, @RequestBody final UserRequestBody request) throws Exception {
        return this.sendResponse(this.userContext.update(id, request));
    }

    /**
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping(value = uriPath + "/{id}", method = RequestMethod.GET, produces = "application/json")
    public String getById(@PathVariable("id") final int id) throws Exception {
        return this.sendResponse(this.userContext.getById(id));
    }
}