package org.ubikz.jscraper.api.entity;

import org.ubikz.jscraper.api.dal.AbstractDal;
import org.ubikz.jscraper.api.dal.model.filter.AbstractDalFilter;
import org.ubikz.jscraper.api.dal.model.request.AbstractDalRequest;
import org.ubikz.jscraper.api.dto.AbstractDto;
import org.ubikz.jscraper.api.entity.model.filter.AbstractEntityFilter;
import org.ubikz.jscraper.api.entity.helper.AbstractEntityHelper;
import org.ubikz.jscraper.api.entity.model.request.AbstractEntityRequest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public abstract class AbstractEntity {
    protected AbstractDal dal;
    protected AbstractEntityHelper helper;

    /**
     * @param filter
     * @return
     */
    public List<AbstractDto> getAll(AbstractEntityFilter filter) throws Exception {
        List<AbstractDto> result = this.helper.getDtoListFromDal(this.dal.getAll(this.parseEntityToDalFilter(filter)));

        if (!filter.isLazy()) {
            this.computeLoading(result);
        }

        return result;
    }

    /**
     * @param filter
     * @return
     * @throws Exception
     */
    public int count(AbstractEntityFilter filter) throws Exception {
        return this.dal.count(this.parseEntityToDalFilter(filter));
    }

    /**
     * @param filter
     * @return
     */
    public Map<Object, AbstractDto> getAllMappedBy(AbstractEntityFilter filter, String attr) throws Exception {
        Map<Object, AbstractDto> result = this.helper.getDtoMapFromDal(
                this.dal.getAll(this.parseEntityToDalFilter(filter)),
                attr
        );

        if (!filter.isLazy()) {
            this.computeLoading(result);
        }

        return result;
    }

    /**
     * @param filter
     * @return
     */
    public AbstractDto get(AbstractEntityFilter filter) throws Exception {
        AbstractDto result = this.helper.getDtoFromDal(
                this.dal.getOne(this.parseEntityToDalFilter(filter))
        );

        if (!filter.isLazy()) {
            this.computeLoading(Collections.singletonList(result));
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
    public List<AbstractDto> createAll(List<AbstractEntityRequest> requestList) throws Exception {
        return this.helper.getDtoListFromReturnDal(
                this.dal.createAll(this.parseListEntityToDalRequest(requestList)),
                "id"
        );
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
    protected abstract void computeLoading(List<AbstractDto> dtoList) throws Exception;

    /**
     * @param dtoList
     */
    protected abstract void computeLoading(Map<Object, AbstractDto> dtoList) throws Exception;

    /**
     * @param request
     * @return
     */
    protected abstract AbstractDalRequest parseEntityToDalRequest(AbstractEntityRequest request);

    /**
     * @param filter
     * @return
     */
    protected abstract AbstractDalFilter parseEntityToDalFilter(AbstractEntityFilter filter);

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
    protected final AbstractDalRequest parseBaseEntityToDalRequest(AbstractEntityRequest eRequest, AbstractDalRequest dRequest) {
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
    protected final AbstractDalFilter parseBaseEntityToDalFilter(AbstractEntityFilter eFilter, AbstractDalFilter dFilter) {
        dFilter.setId(eFilter.getId());
        dFilter.setIdList(eFilter.getIdList());
        dFilter.setLabel(eFilter.getLabel());
        dFilter.setSearch(eFilter.getSearch());
        dFilter.setEnabled(eFilter.getEnabled());
        dFilter.setStartDate(eFilter.getStartDate());
        dFilter.setEndDate(eFilter.getEndDate());
        dFilter.setLimit(eFilter.getLimit());
        dFilter.setOffset(eFilter.getOffset());

        return dFilter;
    }
}