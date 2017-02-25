package com.ubikz.scraper.core.app.service;

import com.ubikz.scraper.core.app.entity.filter.AbstractEntityFilter;
import com.ubikz.scraper.core.app.entity.request.AbstractEntityRequest;
import com.ubikz.scraper.core.app.service.filter.AbstractServiceFilter;
import com.ubikz.scraper.core.app.service.request.AbstractServiceRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

abstract class AbstractService {
    protected final Logger logger = LoggerFactory.getLogger(AbstractService.class);

    /**
     * @param request
     * @return
     */
    abstract protected AbstractEntityRequest parseServiceToEntityRequest(AbstractServiceRequest request);

    /**
     * @param filter
     * @return
     */
    abstract protected AbstractEntityFilter parseServiceToEntityFilter(AbstractServiceFilter filter);

    /**
     * @param sRequest
     * @param eRequest
     * @return
     */
    final protected void parseBaseServiceToEntityRequest(AbstractServiceRequest sRequest, AbstractEntityRequest eRequest) {
        eRequest.setId(sRequest.getId());
        eRequest.setLabel(sRequest.getLabel());
        eRequest.setEnabled(sRequest.isEnabled());
    }

    /**
     * @param sFilter
     * @param eFilter
     * @return
     */
    final protected void parseBaseServiceToEntityFilter(AbstractServiceFilter sFilter, AbstractEntityFilter eFilter) {
        eFilter.setId(sFilter.getId());
        eFilter.setLabel(sFilter.getLabel());
        eFilter.setEnabled(sFilter.isEnabled());
    }
}