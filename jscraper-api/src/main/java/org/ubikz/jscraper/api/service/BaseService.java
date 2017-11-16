package org.ubikz.jscraper.api.service;

import org.ubikz.jscraper.api.dto.BaseDto;
import org.ubikz.jscraper.api.entity.BaseEntity;
import org.ubikz.jscraper.api.entity.model.filter.AbstractEntityFilter;
import org.ubikz.jscraper.api.entity.model.request.AbstractEntityRequest;
import org.ubikz.jscraper.api.service.model.filter.BaseServiceFilter;
import org.ubikz.jscraper.api.service.model.request.BaseServiceRequest;

import java.util.List;

public abstract class BaseService {
    protected BaseEntity entity;

    /**
     * @param request
     * @return
     */
    protected abstract AbstractEntityRequest parseServiceToEntityRequest(BaseServiceRequest request);

    /**
     * @param filter
     * @return
     */
    protected abstract AbstractEntityFilter parseServiceToEntityFilter(BaseServiceFilter filter);

    /**
     * @param filter
     * @return
     * @throws Exception
     */
    public List<BaseDto> getAll(BaseServiceFilter filter) throws Exception {
        return this.entity.getAll(this.parseServiceToEntityFilter(filter));
    }

    /**
     * @param filter
     * @return
     * @throws Exception
     */
    public int count(BaseServiceFilter filter) throws Exception {
        return this.entity.count(this.parseServiceToEntityFilter(filter));
    }

    /**
     * @param filter
     * @return
     * @throws Exception
     */
    public BaseDto get(BaseServiceFilter filter) throws Exception {
        return this.entity.get(this.parseServiceToEntityFilter(filter));
    }

    /**
     * @param filter
     * @return
     * @throws Exception
     */
    public int delete(BaseServiceFilter filter) throws Exception {
        return this.entity.delete(this.parseServiceToEntityFilter(filter));
    }

    /**
     * @param request
     * @return
     * @throws Exception
     */
    public int create(BaseServiceRequest request) throws Exception {
        return this.entity.create(this.parseServiceToEntityRequest(request));
    }

    /**
     * @param request
     * @return
     * @throws Exception
     */
    public int update(BaseServiceRequest request) throws Exception {
        return this.entity.update(this.parseServiceToEntityRequest(request));
    }

    /**
     * @param sRequest
     * @param eRequest
     * @return
     */
    protected final void parseBaseServiceToEntityRequest(BaseServiceRequest sRequest, AbstractEntityRequest eRequest) {
        eRequest.setId(sRequest.getId());
        eRequest.setLabel(sRequest.getLabel());
        eRequest.setEnabled(sRequest.getEnabled());
    }

    /**
     * @param sFilter
     * @param eFilter
     * @return
     */
    protected final void parseBaseServiceToEntityFilter(BaseServiceFilter sFilter, AbstractEntityFilter eFilter) {
        eFilter.setId(sFilter.getId());
        eFilter.setLabel(sFilter.getLabel());
        eFilter.setSearch(sFilter.getSearch());
        eFilter.setEnabled(sFilter.getEnabled());
        eFilter.setLazy(sFilter.isLazy());
        eFilter.setStartDate(sFilter.getStartDate());
        eFilter.setOffset(sFilter.getOffset());
        eFilter.setLimit(sFilter.getLimit());
    }
}