package org.ubikz.jscraper.api.entity.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.ubikz.jscraper.api.dal.impl.UserDal;
import org.ubikz.jscraper.api.dal.model.filter.impl.UserDalFilter;
import org.ubikz.jscraper.api.dal.model.request.impl.UserDalRequest;
import org.ubikz.jscraper.api.dto.impl.UserDto;
import org.ubikz.jscraper.api.entity.BaseEntity;
import org.ubikz.jscraper.api.entity.helper.impl.UserEntityHelper;
import org.ubikz.jscraper.api.entity.model.filter.BaseEntityFilter;
import org.ubikz.jscraper.api.entity.model.filter.impl.UserEntityFilter;
import org.ubikz.jscraper.api.entity.model.request.BaseEntityRequest;
import org.ubikz.jscraper.api.entity.model.request.impl.UserEntityRequest;

import java.util.List;
import java.util.Map;

@Component
public class UserEntity extends BaseEntity<UserDal, UserDalRequest, UserDalFilter, UserEntityHelper, UserDto> {
    @Autowired
    public UserEntity(UserDal userDal) {
        this.dal = userDal;
        this.helper = new UserEntityHelper();
        this.dalRequest = new UserDalRequest();
        this.dalFilter = new UserDalFilter();
    }

    @Override
    protected void computeLoading(List<UserDto> dtoList) {
    }

    @Override
    protected void computeLoading(Map<Object, UserDto> dtoList) {
    }

    @Override
    protected <T extends BaseEntityRequest> void parseRequest(T request) {
        super.parseRequest(request);
        UserEntityRequest userEntityRequest = (UserEntityRequest) request;

        dalRequest.setUsername(userEntityRequest.getUsername());
        dalRequest.setFirstname(userEntityRequest.getFirstname());
        dalRequest.setLastname(userEntityRequest.getLastname());
        dalRequest.setPassword(userEntityRequest.getPassword());
        dalRequest.setAdmin(userEntityRequest.getAdmin());
    }

    @Override
    protected <T extends BaseEntityFilter> void parseFilter(T filter) {
        super.parseFilter(filter);
        UserEntityFilter userEntityFilter = (UserEntityFilter) filter;

        dalFilter.setUsername(userEntityFilter.getUsername());
        dalFilter.setPassword(userEntityFilter.getPassword());
    }
}
