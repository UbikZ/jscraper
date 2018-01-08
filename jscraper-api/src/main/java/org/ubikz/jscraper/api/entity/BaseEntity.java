package org.ubikz.jscraper.api.entity;

import org.ubikz.jscraper.api.dal.BaseDal;
import org.ubikz.jscraper.api.dal.model.filter.BaseDalFilter;
import org.ubikz.jscraper.api.dal.model.request.BaseDalRequest;
import org.ubikz.jscraper.api.dto.BaseDto;
import org.ubikz.jscraper.api.entity.helper.BaseEntityHelper;
import org.ubikz.jscraper.api.entity.model.filter.BaseEntityFilter;
import org.ubikz.jscraper.api.entity.model.request.BaseEntityRequest;
import org.ubikz.jscraper.reference.table.field.CommonReference;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public abstract class BaseEntity<V extends BaseDal, R extends BaseDalRequest, F extends BaseDalFilter, H extends BaseEntityHelper, D extends BaseDto> {
    protected V dal;
    protected R dalRequest;
    protected F dalFilter;
    protected H helper;

    @SuppressWarnings("unchecked")
    public <T extends BaseEntityFilter> List<D> getAll(T filter) {
        parseFilter(filter);
        List<D> result = helper.getDtoListFromDal(dal.getAll(dalFilter));

        if (!filter.isLazy()) {
            computeLoading(result);
        }

        return result;
    }

    @SuppressWarnings("unchecked")
    public <T extends BaseEntityFilter> int count(T filter) {
        parseFilter(filter);

        return dal.count(dalFilter);
    }

    @SuppressWarnings("unchecked")
    public <T extends BaseEntityFilter> Map<Object, D> getAllMappedBy(T filter, String attr) {
        parseFilter(filter);
        Map<Object, D> result = helper.getDtoMapFromDal(dal.getAll(dalFilter), attr);

        if (!filter.isLazy()) {
            computeLoading(result);
        }

        return result;
    }

    @SuppressWarnings("unchecked")
    public <T extends BaseEntityFilter> D get(T filter) {
        parseFilter(filter);
        D result = (D) helper.getDtoFromDal(dal.getOne(dalFilter));

        if (!filter.isLazy()) {
            computeLoading(Collections.singletonList(result));
        }

        return result;
    }

    public <T extends BaseEntityRequest> int create(T request) {
        parseRequest(request);

        return dal.create(dalRequest);
    }

    @SuppressWarnings("unchecked")
    public <T extends BaseEntityRequest> List<D> createAll(List<T> requestList) {
        return helper.getDtoListFromDal(dal.createAll(parseListRequest(requestList)), CommonReference.ID.get());
    }

    public <T extends BaseEntityRequest> int update(T request) {
        parseRequest(request);

        return dal.edit(dalRequest);
    }

    public <T extends BaseEntityFilter> int delete(T filter) {
        parseFilter(filter);

        return dal.delete(dalFilter);
    }

    protected abstract void computeLoading(List<D> dtoList);

    protected abstract void computeLoading(Map<Object, D> dtoList);

    protected <T extends BaseEntityRequest> List<R> parseListRequest(List<T> requestList) {
        return requestList.stream().map(this::parseRequestR).collect(Collectors.toList());
    }

    private <T extends BaseEntityRequest> R parseRequestR(T request) {
        parseRequest(request);

        return dalRequest;
    }

    protected <T extends BaseEntityRequest> void parseRequest(T request) {
        dalRequest.setId(request.getId());
        dalRequest.setLabel(request.getLabel());
        dalRequest.setEnabled(request.getEnabled());
    }

    protected <T extends BaseEntityFilter> void parseFilter(T filter) {
        dalFilter.setId(filter.getId());
        dalFilter.setIdList(filter.getIdList());
        dalFilter.setLabel(filter.getLabel());
        dalFilter.setSearch(filter.getSearch());
        dalFilter.setEnabled(filter.getEnabled());
        dalFilter.setStartDate(filter.getStartDate());
        dalFilter.setEndDate(filter.getEndDate());
        dalFilter.setLimit(filter.getLimit());
        dalFilter.setOffset(filter.getOffset());
    }
}
