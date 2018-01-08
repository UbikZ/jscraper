package org.ubikz.jscraper.api.context;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.ubikz.jscraper.api.controller.model.filter.BaseFilterBody;
import org.ubikz.jscraper.api.controller.model.request.BaseRequestBody;
import org.ubikz.jscraper.api.service.BaseService;
import org.ubikz.jscraper.api.service.model.filter.BaseServiceFilter;
import org.ubikz.jscraper.api.service.model.message.impl.ListResultMessage;
import org.ubikz.jscraper.api.service.model.message.impl.SingleResultMessage;
import org.ubikz.jscraper.api.service.model.request.BaseServiceRequest;
import org.ubikz.jscraper.exception.ApplicativeException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.function.Function;

@Component
public abstract class BaseContext<S extends BaseService, R extends BaseServiceRequest, F extends BaseServiceFilter> {
    protected S service;
    protected R serviceRequest;
    protected F serviceFilter;

    public <T extends BaseRequestBody> SingleResultMessage create(T request) {
        parseRequest(request);

        return toRequestResult(service::create, HttpStatus.CREATED);
    }

    public <T extends BaseRequestBody> SingleResultMessage update(int id, T request) {
        parseRequest(request);
        serviceRequest.setId(id);

        return toRequestResult(service::update);
    }

    public SingleResultMessage getById(int id) {
        serviceFilter.setId(id);

        return toFilterResult(service::get);
    }

    public SingleResultMessage deleteById(int id) {
        serviceFilter.setId(id);

        return toFilterResult(service::delete);
    }

    public <T extends BaseFilterBody> ListResultMessage getAll(T filter) {
        parseFilter(filter);

        return toFilterListResult(service::getAll, count(filter));
    }

    public <T extends BaseFilterBody> int count(T filter) {
        parseFilter(filter);

        return service.count(serviceFilter);
    }

    protected <T extends BaseRequestBody> void parseRequest(T data) {
        serviceRequest.setLabel(data.getLabel());
        serviceRequest.setEnabled(data.getEnabled());
    }

    protected <T extends BaseFilterBody> void parseFilter(T data) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        serviceFilter.setId(data.getId());
        serviceFilter.setLabel(data.getLabel());
        serviceFilter.setSearch(data.getSearch());
        serviceFilter.setEnabled(data.getEnabled());
        serviceFilter.setLazy(data.isLazy());
        serviceFilter.setLimit(data.getLimit());
        serviceFilter.setOffset(data.getOffset());

        try {
            serviceFilter.setStartDate(data.getStartDate() == null ? null : formatter.parse(data.getStartDate()));
            serviceFilter.setEndDate(data.getEndDate() == null ? null : formatter.parse(data.getEndDate()));
        } catch (ParseException e) {
            throw new ApplicativeException(e);
        }
    }

    protected final SingleResultMessage toFilterResult(Function<F, Object> function) {
        return singleResult(function.apply(serviceFilter), HttpStatus.OK);
    }

    protected final SingleResultMessage toFilterResult(Function<F, Object> function, HttpStatus status) {
        return singleResult(function.apply(serviceFilter), status);
    }

    protected final ListResultMessage toFilterListResult(Function<F, List<Object>> function, int total) {
        return listResult(function.apply(serviceFilter), total, HttpStatus.OK);
    }

    protected final ListResultMessage toFilterListResult(Function<F, List<Object>> function, int total, HttpStatus status) {
        return listResult(function.apply(serviceFilter), total, status);
    }

    protected final SingleResultMessage toRequestResult(Function<R, Object> function) {
        return singleResult(function.apply(serviceRequest), HttpStatus.OK);
    }

    protected final SingleResultMessage toRequestResult(Function<R, Object> function, HttpStatus status) {
        return singleResult(function.apply(serviceRequest), status);
    }

    protected ListResultMessage listResult(List<Object> dataList, int total, HttpStatus status) {
        return new ListResultMessage()
                .setData(dataList)
                .setTotal(total)
                .setStatus(status.value())
                .setSize(dataList.size());
    }

    protected SingleResultMessage singleResult(Object data, HttpStatus status) {
        return new SingleResultMessage()
                .setData(data)
                .setStatus(status.value())
                .setType(data.getClass().getName());
    }
}
