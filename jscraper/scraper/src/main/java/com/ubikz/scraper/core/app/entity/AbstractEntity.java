package com.ubikz.scraper.core.app.entity;

import com.ubikz.scraper.core.app.dal.AbstractDal;
import com.ubikz.scraper.core.app.dal.filter.AbstractDalFilter;
import com.ubikz.scraper.core.app.dal.request.AbstractDalRequest;
import com.ubikz.scraper.core.app.dto.AbstractDto;
import com.ubikz.scraper.core.app.entity.filter.AbstractEntityFilter;
import com.ubikz.scraper.core.app.entity.helper.AbstractEntityHelper;
import com.ubikz.scraper.core.app.entity.request.AbstractEntityRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

abstract public class AbstractEntity {
    protected final Logger logger = LoggerFactory.getLogger(AbstractEntity.class);
    protected AbstractDal dal;
    protected AbstractEntityHelper helper;

    /**
     * @param filter
     * @return
     */
    public List<AbstractDto> getAll(AbstractEntityFilter filter) {
        List<AbstractDto> result = this.helper.getDtoListFromDal(this.dal.getAll(this.parseEntityToDalFilter(filter)));

        if (filter.isLazy()) {
            this.computeLazyLoading(result);
        }

        return result;
    }

    /**
     * @param filter
     * @return
     */
    public AbstractDto get(AbstractEntityFilter filter) {
        AbstractDto result = this.helper.getDtoFromDal(
                this.dal.getOne(this.parseEntityToDalFilter(filter))
        );

        if (filter.isLazy()) {
            this.computeLazyLoading(Collections.singletonList(result));
        }

        return result;
    }

    /**
     * @param request
     * @return
     */
    public int create(AbstractEntityRequest request) {
        return this.dal.create(this.parseEntityToDalRequest(request));
    }

    /**
     * @param requestList
     * @return
     */
    public int createAll(List<AbstractEntityRequest> requestList) {
        return this.dal.createAll(this.parseListEntityToDalRequest(requestList));
    }

    /**
     * @param request
     * @return
     */
    public int update(AbstractEntityRequest request) {
        return this.dal.edit(this.parseEntityToDalRequest(request));
    }

    /**
     * @param filter
     * @return
     */
    public int delete(AbstractEntityFilter filter) {
        return this.dal.delete(this.parseEntityToDalFilter(filter));
    }

    /**
     * @param dtoList
     */
    abstract protected void computeLazyLoading(List<AbstractDto> dtoList);

    /**
     * @param request
     * @return
     */
    abstract protected AbstractDalRequest parseEntityToDalRequest(AbstractEntityRequest request);

    /**
     * @param filter
     * @return
     */
    abstract protected AbstractDalFilter parseEntityToDalFilter(AbstractEntityFilter filter);

    /**
     * @param requestList
     * @return
     */
    protected List<AbstractDalRequest> parseListEntityToDalRequest(List<AbstractEntityRequest> requestList) {
        List<AbstractDalRequest> dalRequestList = new ArrayList<>();

        for (AbstractEntityRequest entityRequest : requestList) {
            dalRequestList.add(this.parseEntityToDalRequest(entityRequest));
        }

        return dalRequestList;
    }

    /**
     * @param eRequest
     * @param dRequest
     * @return
     */
    final protected AbstractDalRequest parseBaseEntityToDalRequest(AbstractEntityRequest eRequest, AbstractDalRequest dRequest) {
        dRequest.setId(eRequest.getId());
        dRequest.setLabel(eRequest.getLabel());
        dRequest.setEnabled(eRequest.getEnabled());

        return dRequest;
    }

    /**
     * @param eFilter
     * @param dFilter
     * @return
     */
    final protected AbstractDalFilter parseBaseEntityToDalFilter(AbstractEntityFilter eFilter, AbstractDalFilter dFilter) {
        dFilter.setId(eFilter.getId());
        dFilter.setLabel(eFilter.getLabel());
        dFilter.setEnabled(eFilter.getEnabled());

        return dFilter;
    }
}