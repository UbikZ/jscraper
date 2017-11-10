package org.ubikz.jscraper.api.core.app.context;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.ubikz.jscraper.api.controller.filter.AbstractFilterBody;
import org.ubikz.jscraper.api.controller.filter.UserFilterBody;
import org.ubikz.jscraper.api.controller.request.AbstractRequestBody;
import org.ubikz.jscraper.api.controller.request.UserRequestBody;
import org.ubikz.jscraper.api.core.app.service.UserService;
import org.ubikz.jscraper.api.core.app.service.filter.AbstractServiceFilter;
import org.ubikz.jscraper.api.core.app.service.filter.UserServiceFilter;
import org.ubikz.jscraper.api.core.app.service.request.AbstractServiceRequest;
import org.ubikz.jscraper.api.core.app.service.request.UserServiceRequest;

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