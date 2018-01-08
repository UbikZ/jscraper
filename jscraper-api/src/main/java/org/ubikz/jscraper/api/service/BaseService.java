package org.ubikz.jscraper.api.service;

import org.ubikz.jscraper.api.dto.BaseDto;
import org.ubikz.jscraper.api.entity.BaseEntity;
import org.ubikz.jscraper.api.entity.model.filter.BaseEntityFilter;
import org.ubikz.jscraper.api.entity.model.request.BaseEntityRequest;
import org.ubikz.jscraper.api.service.model.filter.BaseServiceFilter;
import org.ubikz.jscraper.api.service.model.request.BaseServiceRequest;

import java.util.List;

public abstract class BaseService<E extends BaseEntity, R extends BaseEntityRequest, F extends BaseEntityFilter, D extends BaseDto> {
    protected E entity;
    protected R entityRequest;
    protected F entityFilter;

    public <T extends BaseServiceRequest> int create(T request) {
        parseRequest(request);

        return entity.create(entityRequest);
    }

    public <T extends BaseServiceRequest> int update(T request) {
        parseRequest(request);

        return entity.update(entityRequest);
    }

    @SuppressWarnings("unchecked")
    public <T extends BaseServiceFilter> D get(T filter) {
        parseFilter(filter);

        return (D) entity.get(entityFilter);
    }

    @SuppressWarnings("unchecked")
    public <T extends BaseServiceFilter> List<D> getAll(T filter) {
        parseFilter(filter);

        return entity.getAll(entityFilter);
    }

    public <T extends BaseServiceFilter> int count(T filter) {
        parseFilter(filter);

        return entity.count(entityFilter);
    }

    public <T extends BaseServiceFilter> int delete(T filter) {
        parseFilter(filter);

        return entity.delete(entityFilter);
    }

    protected <T extends BaseServiceRequest> void parseRequest(T request) {
        entityRequest.setId(request.getId());
        entityRequest.setLabel(request.getLabel());
        entityRequest.setEnabled(request.getEnabled());
    }

    protected <T extends BaseServiceFilter> void parseFilter(T filter) {
        entityFilter.setId(filter.getId());
        entityFilter.setLabel(filter.getLabel());
        entityFilter.setSearch(filter.getSearch());
        entityFilter.setEnabled(filter.getEnabled());
        entityFilter.setLazy(filter.isLazy());
        entityFilter.setStartDate(filter.getStartDate());
        entityFilter.setOffset(filter.getOffset());
        entityFilter.setLimit(filter.getLimit());
    }
}
