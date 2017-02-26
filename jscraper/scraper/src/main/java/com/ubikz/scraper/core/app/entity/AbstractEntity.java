package com.ubikz.scraper.core.app.entity;

import com.ubikz.scraper.core.app.dal.filter.AbstractDalFilter;
import com.ubikz.scraper.core.app.dal.request.AbstractDalRequest;
import com.ubikz.scraper.core.app.entity.filter.AbstractEntityFilter;
import com.ubikz.scraper.core.app.entity.request.AbstractEntityRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

abstract class AbstractEntity {
    protected final Logger logger = LoggerFactory.getLogger(AbstractEntity.class);

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