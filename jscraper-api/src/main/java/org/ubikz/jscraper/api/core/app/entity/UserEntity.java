package org.ubikz.jscraper.api.core.app.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.ubikz.jscraper.api.core.app.dal.UserDal;
import org.ubikz.jscraper.api.core.app.dal.filter.AbstractDalFilter;
import org.ubikz.jscraper.api.core.app.dal.filter.UserDalFilter;
import org.ubikz.jscraper.api.core.app.dal.request.AbstractDalRequest;
import org.ubikz.jscraper.api.core.app.dal.request.UserDalRequest;
import org.ubikz.jscraper.api.core.app.dto.AbstractDto;
import org.ubikz.jscraper.api.core.app.entity.filter.AbstractEntityFilter;
import org.ubikz.jscraper.api.core.app.entity.filter.UserEntityFilter;
import org.ubikz.jscraper.api.core.app.entity.helper.UserEntityHelper;
import org.ubikz.jscraper.api.core.app.entity.request.AbstractEntityRequest;
import org.ubikz.jscraper.api.core.app.entity.request.UserEntityRequest;

import java.util.List;
import java.util.Map;

@Component
public class UserEntity extends AbstractEntity {
    @Autowired
    public UserEntity(UserDal userDal) {
        this.dal = userDal;
        this.helper = new UserEntityHelper();
    }

    @Override
    protected void computeLoading(List<AbstractDto> dtoList) throws Exception {
    }

    @Override
    protected void computeLoading(Map<Object, AbstractDto> dtoList) throws Exception {
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