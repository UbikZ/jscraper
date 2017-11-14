package org.ubikz.jscraper.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.ubikz.jscraper.api.entity.UserEntity;
import org.ubikz.jscraper.api.entity.model.filter.AbstractEntityFilter;
import org.ubikz.jscraper.api.entity.model.filter.impl.UserEntityFilter;
import org.ubikz.jscraper.api.entity.model.request.AbstractEntityRequest;
import org.ubikz.jscraper.api.entity.model.request.impl.UserEntityRequest;
import org.ubikz.jscraper.api.service.AbstractService;
import org.ubikz.jscraper.api.service.model.filter.AbstractServiceFilter;
import org.ubikz.jscraper.api.service.model.filter.impl.UserServiceFilter;
import org.ubikz.jscraper.api.service.model.request.AbstractServiceRequest;
import org.ubikz.jscraper.api.service.model.request.impl.UserServiceRequest;

@Component
public class UserService extends AbstractService {
    @Autowired
    public UserService(UserEntity userEntity) {
        this.entity = userEntity;
    }

    /**
     * @param request
     * @return
     */
    @Override
    protected AbstractEntityRequest parseServiceToEntityRequest(AbstractServiceRequest request) {
        UserEntityRequest userEntityRequest = new UserEntityRequest();
        UserServiceRequest userServiceRequest = (UserServiceRequest) request;

        this.parseBaseServiceToEntityRequest(userServiceRequest, userEntityRequest);
        userEntityRequest.setUsername(userServiceRequest.getUsername());
        userEntityRequest.setFirstname(userServiceRequest.getFirstname());
        userEntityRequest.setLastname(userServiceRequest.getLastname());
        userEntityRequest.setPassword(userServiceRequest.getPassword());
        userEntityRequest.setAdmin(userServiceRequest.getAdmin());

        return userEntityRequest;
    }

    /**
     * @param filter
     * @return
     */
    @Override
    protected AbstractEntityFilter parseServiceToEntityFilter(AbstractServiceFilter filter) {
        UserEntityFilter userEntityFilter = new UserEntityFilter();
        UserServiceFilter userServiceFilter = (UserServiceFilter) filter;

        this.parseBaseServiceToEntityFilter(userServiceFilter, userEntityFilter);

        userEntityFilter.setUsername(userServiceFilter.getUsername());
        userEntityFilter.setPassword(userServiceFilter.getPassword());

        return userEntityFilter;
    }
}