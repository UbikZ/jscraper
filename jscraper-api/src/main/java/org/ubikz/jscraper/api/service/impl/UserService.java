package org.ubikz.jscraper.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.ubikz.jscraper.api.dto.impl.UserDto;
import org.ubikz.jscraper.api.entity.impl.UserEntity;
import org.ubikz.jscraper.api.entity.model.filter.impl.UserEntityFilter;
import org.ubikz.jscraper.api.entity.model.request.impl.UserEntityRequest;
import org.ubikz.jscraper.api.service.BaseService;
import org.ubikz.jscraper.api.service.model.filter.BaseServiceFilter;
import org.ubikz.jscraper.api.service.model.filter.impl.UserServiceFilter;
import org.ubikz.jscraper.api.service.model.request.BaseServiceRequest;
import org.ubikz.jscraper.api.service.model.request.impl.UserServiceRequest;

@Component
public class UserService extends BaseService<UserEntity, UserEntityRequest, UserEntityFilter, UserDto> {
    @Autowired
    public UserService(UserEntity userEntity) {
        this.entity = userEntity;
    }

    protected <T extends BaseServiceRequest> void parseRequest(T data) {
        super.parseRequest(data);
        UserServiceRequest serviceRequest = (UserServiceRequest) data;

        entityRequest.setUsername(serviceRequest.getUsername());
        entityRequest.setFirstname(serviceRequest.getFirstname());
        entityRequest.setLastname(serviceRequest.getLastname());
        entityRequest.setPassword(serviceRequest.getPassword());
        entityRequest.setAdmin(serviceRequest.getAdmin());
    }

    protected <T extends BaseServiceFilter> void parseFilter(T data) {
        super.parseFilter(data);
        UserServiceFilter serviceFilter = (UserServiceFilter) data;

        entityFilter.setUsername(serviceFilter.getUsername());
        entityFilter.setPassword(serviceFilter.getPassword());
    }
}
