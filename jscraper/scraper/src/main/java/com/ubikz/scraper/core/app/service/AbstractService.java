package com.ubikz.scraper.core.app.service;

import com.ubikz.scraper.core.app.dto.AbstractDto;
import com.ubikz.scraper.core.app.entity.AbstractEntity;
import com.ubikz.scraper.core.app.entity.filter.AbstractEntityFilter;
import com.ubikz.scraper.core.app.entity.request.AbstractEntityRequest;
import com.ubikz.scraper.core.app.service.filter.AbstractServiceFilter;
import com.ubikz.scraper.core.app.service.request.AbstractServiceRequest;

import java.util.List;

abstract public class AbstractService {
    protected AbstractEntity entity;

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
     * @param filter
     * @return
     * @throws Exception
     */
    public List<AbstractDto> getAll(AbstractServiceFilter filter) throws Exception {
        return this.entity.getAll(this.parseServiceToEntityFilter(filter));
    }

    /**
     * @param filter
     * @return
     * @throws Exception
     */
    public AbstractDto get(AbstractServiceFilter filter) throws Exception {
        return this.entity.get(this.parseServiceToEntityFilter(filter));
    }

    /**
     * @param filter
     * @return
     * @throws Exception
     */
    public int delete(AbstractServiceFilter filter) throws Exception {
        return this.entity.delete(this.parseServiceToEntityFilter(filter));
    }

    /**
     * @param request
     * @return
     * @throws Exception
     */
    public int create(AbstractServiceRequest request) throws Exception {
        return this.entity.create(this.parseServiceToEntityRequest(request));
    }

    /**
     * @param request
     * @return
     * @throws Exception
     */
    public int update(AbstractServiceRequest request) throws Exception {
        return this.entity.update(this.parseServiceToEntityRequest(request));
    }

    /**
     * @param sRequest
     * @param eRequest
     * @return
     */
    final protected void parseBaseServiceToEntityRequest(AbstractServiceRequest sRequest, AbstractEntityRequest eRequest) {
        eRequest.setId(sRequest.getId());
        eRequest.setLabel(sRequest.getLabel());
        eRequest.setEnabled(sRequest.getEnabled());
    }

    /**
     * @param sFilter
     * @param eFilter
     * @return
     */
    final protected void parseBaseServiceToEntityFilter(AbstractServiceFilter sFilter, AbstractEntityFilter eFilter) {
        eFilter.setId(sFilter.getId());
        eFilter.setLabel(sFilter.getLabel());
        eFilter.setEnabled(sFilter.getEnabled());
        eFilter.setLazy(sFilter.isLazy());
    }
}