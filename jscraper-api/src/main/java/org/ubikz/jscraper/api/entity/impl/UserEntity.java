package org.ubikz.jscraper.api.entity.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.ubikz.jscraper.api.dal.impl.UserDal;
import org.ubikz.jscraper.api.dal.model.filter.AbstractDalFilter;
import org.ubikz.jscraper.api.dal.model.filter.impl.UserDalFilter;
import org.ubikz.jscraper.api.dal.model.request.AbstractDalRequest;
import org.ubikz.jscraper.api.dal.model.request.impl.UserDalRequest;
import org.ubikz.jscraper.api.dto.BaseDto;
import org.ubikz.jscraper.api.entity.BaseEntity;
import org.ubikz.jscraper.api.entity.model.filter.AbstractEntityFilter;
import org.ubikz.jscraper.api.entity.model.filter.impl.UserEntityFilter;
import org.ubikz.jscraper.api.entity.helper.impl.UserEntityHelper;
import org.ubikz.jscraper.api.entity.model.request.AbstractEntityRequest;
import org.ubikz.jscraper.api.entity.model.request.impl.UserEntityRequest;

import java.util.List;
import java.util.Map;

@Component
public class UserEntity extends BaseEntity {
    @Autowired
    public UserEntity(UserDal userDal) {
        this.dal = userDal;
        this.helper = new UserEntityHelper();
    }

    @Override
    protected void computeLoading(List<BaseDto> dtoList) throws Exception {
    }

    @Override
    protected void computeLoading(Map<Object, BaseDto> dtoList) throws Exception {
    }

    /**
     * @param request
     * @return
     */
    @Override
    protected AbstractDalRequest parseEntityToDalRequest(AbstractEntityRequest request) {
        UserDalRequest userDalRequest = new UserDalRequest();
        UserEntityRequest userEntityRequest = (UserEntityRequest) request;

        userDalRequest = (UserDalRequest) this.parseBaseEntityToDalRequest(userEntityRequest, userDalRequest);
        userDalRequest.setUsername(userEntityRequest.getUsername());
        userDalRequest.setFirstname(userEntityRequest.getFirstname());
        userDalRequest.setLastname(userEntityRequest.getLastname());
        userDalRequest.setPassword(userEntityRequest.getPassword());
        userDalRequest.setAdmin(userEntityRequest.getAdmin());

        return userDalRequest;
    }

    /**
     * @param filter
     * @return
     */
    @Override
    protected AbstractDalFilter parseEntityToDalFilter(AbstractEntityFilter filter) {
        UserDalFilter userDalFilter = new UserDalFilter();
        UserEntityFilter userEntityFilter = (UserEntityFilter) filter;

        userDalFilter = (UserDalFilter) this.parseBaseEntityToDalFilter(userEntityFilter, userDalFilter);

        userDalFilter.setUsername(userEntityFilter.getUsername());
        userDalFilter.setPassword(userEntityFilter.getPassword());

        return userDalFilter;
    }
}