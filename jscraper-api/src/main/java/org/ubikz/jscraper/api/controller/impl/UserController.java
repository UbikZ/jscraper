package org.ubikz.jscraper.api.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.ubikz.jscraper.api.context.impl.UserContext;
import org.ubikz.jscraper.api.controller.BaseController;
import org.ubikz.jscraper.api.controller.model.filter.impl.UserFilterBody;
import org.ubikz.jscraper.api.controller.model.request.impl.UserRequestBody;

@RestController
@RequestMapping(value = "/user")
public class UserController extends BaseController<UserContext, UserRequestBody, UserFilterBody> {
    @Autowired
    public UserController(UserContext userContext) {
        this.context = userContext;
    }
}