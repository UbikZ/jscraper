package com.ubikz.scraper.core.app.service;

import com.ubikz.scraper.core.app.entity.UserEntity;
import com.ubikz.scraper.core.app.entity.filter.AbstractEntityFilter;
import com.ubikz.scraper.core.app.entity.filter.UserEntityFilter;
import com.ubikz.scraper.core.app.entity.request.AbstractEntityRequest;
import com.ubikz.scraper.core.app.entity.request.UserEntityRequest;
import com.ubikz.scraper.core.app.service.filter.AbstractServiceFilter;
import com.ubikz.scraper.core.app.service.filter.UserServiceFilter;
import com.ubikz.scraper.core.app.service.request.AbstractServiceRequest;
import com.ubikz.scraper.core.app.service.request.UserServiceRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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