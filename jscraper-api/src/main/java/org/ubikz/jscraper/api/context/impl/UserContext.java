package org.ubikz.jscraper.api.context.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.ubikz.jscraper.api.context.AbstractContext;
import org.ubikz.jscraper.api.controller.model.filter.AbstractFilterBody;
import org.ubikz.jscraper.api.controller.model.filter.impl.UserFilterBody;
import org.ubikz.jscraper.api.controller.model.request.AbstractRequestBody;
import org.ubikz.jscraper.api.controller.model.request.impl.UserRequestBody;
import org.ubikz.jscraper.api.service.impl.UserService;
import org.ubikz.jscraper.api.service.model.filter.AbstractServiceFilter;
import org.ubikz.jscraper.api.service.model.filter.impl.UserServiceFilter;
import org.ubikz.jscraper.api.service.model.request.AbstractServiceRequest;
import org.ubikz.jscraper.api.service.model.request.impl.UserServiceRequest;

@Component
public class UserContext extends AbstractContext {
    @Autowired
    public UserContext(UserService userService) {
        this.service = userService;
        this.serviceRequest = new UserServiceRequest();
        this.serviceFilter = new UserServiceFilter();
        this.filterBody = new UserFilterBody();

        CREATED = 70;
        UPDATED = 71;
        GET_ONE = 72;
        GET_ALL = 73;
        DELETE = 74;
    }

    @Override
    protected AbstractServiceRequest parseRequest(AbstractRequestBody data, AbstractServiceRequest request) {
        UserRequestBody requestBody = (UserRequestBody) data;
        UserServiceRequest serviceRequest = (UserServiceRequest) parseBaseRequest(requestBody, request);

        serviceRequest.setUsername(requestBody.getUsername());
        serviceRequest.setFirstname(requestBody.getFirstname());
        serviceRequest.setLastname(requestBody.getLastname());
        serviceRequest.setPassword(requestBody.getPassword());
        serviceRequest.setAdmin(requestBody.getAdmin());

        return serviceRequest;
    }

    @Override
    protected AbstractServiceFilter parseFilter(AbstractFilterBody data, AbstractServiceFilter filter) throws Exception {
        UserFilterBody filterBody = (UserFilterBody) data;
        UserServiceFilter serviceFilter = (UserServiceFilter) parseBaseFilter(filterBody, filter);

        serviceFilter.setUsername(filterBody.getUsername());
        serviceFilter.setEmail(filterBody.getEmail());
        serviceFilter.setPassword(filterBody.getPassword());

        return serviceFilter;
    }
}