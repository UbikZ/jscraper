package org.ubikz.jscraper.api.context.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.ubikz.jscraper.api.context.BaseContext;
import org.ubikz.jscraper.api.controller.model.filter.BaseFilterBody;
import org.ubikz.jscraper.api.controller.model.filter.impl.UserFilterBody;
import org.ubikz.jscraper.api.controller.model.request.BaseRequestBody;
import org.ubikz.jscraper.api.controller.model.request.impl.UserRequestBody;
import org.ubikz.jscraper.api.service.impl.UserService;
import org.ubikz.jscraper.api.service.model.filter.impl.UserServiceFilter;
import org.ubikz.jscraper.api.service.model.request.impl.UserServiceRequest;

@Component
public class UserContext extends BaseContext<UserService, UserServiceRequest, UserServiceFilter> {
    @Autowired
    public UserContext(UserService service) {
        this.service = service;
        this.serviceRequest = new UserServiceRequest();
        this.serviceFilter = new UserServiceFilter();
    }

    @Override
    protected <T extends BaseRequestBody> void parseRequest(T data) {
        super.parseRequest(data);
        UserRequestBody requestBody = (UserRequestBody) data;
        serviceRequest.setUsername(requestBody.getUsername());
        serviceRequest.setFirstname(requestBody.getFirstname());
        serviceRequest.setLastname(requestBody.getLastname());
        serviceRequest.setPassword(requestBody.getPassword());
        serviceRequest.setAdmin(requestBody.getAdmin());
    }

    @Override
    protected <T extends BaseFilterBody> void parseFilter(T data) {
        super.parseFilter(data);
        UserFilterBody filterBody = (UserFilterBody) data;

        serviceFilter.setUsername(filterBody.getUsername());
        serviceFilter.setEmail(filterBody.getEmail());
        serviceFilter.setPassword(filterBody.getPassword());
    }
}
