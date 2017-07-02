package com.ubikz.scraper.core.app.context;

import com.ubikz.scraper.api.controller.filter.AbstractFilterBody;
import com.ubikz.scraper.api.controller.filter.UserFilterBody;
import com.ubikz.scraper.api.controller.filter.UserFilterBody;
import com.ubikz.scraper.api.controller.request.AbstractRequestBody;
import com.ubikz.scraper.api.controller.request.UserRequestBody;
import com.ubikz.scraper.core.app.service.UserService;
import com.ubikz.scraper.core.app.service.filter.AbstractServiceFilter;
import com.ubikz.scraper.core.app.service.filter.UserServiceFilter;
import com.ubikz.scraper.core.app.service.filter.UserServiceFilter;
import com.ubikz.scraper.core.app.service.request.AbstractServiceRequest;
import com.ubikz.scraper.core.app.service.request.UserServiceRequest;
import com.ubikz.scraper.core.app.service.request.UserServiceRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class UserContext extends AbstractContext {
    @Autowired
    public UserContext(UserService userService) {
        this.service = userService;
        this.serviceRequest = new UserServiceRequest();
        this.serviceFilter = new UserServiceFilter();
        this.filterBody = new UserFilterBody();

        this.CREATED = 70;
        this.UPDATED = 71;
        this.GET_ONE = 72;
        this.GET_ALL = 73;
        this.DELETE = 74;
    }

    @Override
    protected AbstractServiceRequest parseRequest(AbstractRequestBody data, AbstractServiceRequest request) {
        UserRequestBody requestBody = (UserRequestBody) data;
        UserServiceRequest serviceRequest = (UserServiceRequest) this.parseBaseRequest(requestBody, request);

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
        UserServiceFilter serviceFilter = (UserServiceFilter) this.parseBaseFilter(filterBody, filter);

        serviceFilter.setUsername(filterBody.getUsername());
        serviceFilter.setEmail(filterBody.getEmail());
        serviceFilter.setPassword(filterBody.getPassword());

        return serviceFilter;
    }
}