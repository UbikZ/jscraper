package com.ubikz.scraper.core.app.context;

import com.ubikz.scraper.api.controller.filter.AbstractFilterBody;
import com.ubikz.scraper.api.controller.filter.FeedTypeFilterBody;
import com.ubikz.scraper.api.controller.filter.TagProhibitedFilterBody;
import com.ubikz.scraper.api.controller.request.AbstractRequestBody;
import com.ubikz.scraper.api.controller.request.TagProhibitedRequestBody;
import com.ubikz.scraper.core.app.exception.MissingParameterException;
import com.ubikz.scraper.core.app.service.TagProhibitedService;
import com.ubikz.scraper.core.app.service.filter.AbstractServiceFilter;
import com.ubikz.scraper.core.app.service.filter.TagProhibitedServiceFilter;
import com.ubikz.scraper.core.app.service.message.BaseMessage;
import com.ubikz.scraper.core.app.service.request.AbstractServiceRequest;
import com.ubikz.scraper.core.app.service.request.TagProhibitedServiceRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class TagProhibitedContext extends AbstractContext {
    final private int TAG_PROHIBITED_CREATED = 60;
    final private int TAG_PROHIBITED_UPDATED = 61;
    final private int TAG_PROHIBITED_GET_ONE = 62;
    final private int TAG_PROHIBITED_GET_ALL = 63;
    final private int TAG_PROHIBITED_DELETE = 64;

    private TagProhibitedService tagProhibitedService;

    @Autowired
    public TagProhibitedContext(TagProhibitedService tagProhibitedService) {
        this.tagProhibitedService = tagProhibitedService;
    }

    /**
     * @param request
     * @return
     * @throws Exception
     */
    public BaseMessage createTagProhibited(TagProhibitedRequestBody request) throws Exception {
        return this.handle(() -> this.tagProhibitedService.createTagProhibited(
                (TagProhibitedServiceRequest) this.parseRequest(request, new TagProhibitedServiceRequest())
        ), HttpStatus.CREATED, TAG_PROHIBITED_CREATED);
    }

    /**
     * @param request
     * @return
     * @throws Exception
     */
    public BaseMessage updateTagProhibited(Integer id, TagProhibitedRequestBody request) throws Exception {
        return this.handle(() -> {
            TagProhibitedServiceRequest serviceRequest = new TagProhibitedServiceRequest();
            serviceRequest.setId(id);

            if (id == null) {
                throw new MissingParameterException();
            }

            return this.tagProhibitedService.createTagProhibited(
                    (TagProhibitedServiceRequest) this.parseRequest(request, serviceRequest)
            );
        }, HttpStatus.OK, TAG_PROHIBITED_UPDATED);
    }

    /**
     * @param filter
     * @return
     * @throws Exception
     */
    public BaseMessage getAllTagProhibiteds(TagProhibitedFilterBody filter) throws Exception {
        return this.handle(() -> this.tagProhibitedService.getAllTagsProhibited(
                (TagProhibitedServiceFilter) this.parseFilter(filter, new TagProhibitedServiceFilter())
        ), HttpStatus.OK, TAG_PROHIBITED_GET_ALL);
    }

    /**
     * @param id
     * @return
     * @throws Exception
     */
    public BaseMessage deleteTagProhibitedById(int id) throws Exception {
        FeedTypeFilterBody filter = new FeedTypeFilterBody();
        filter.setId(id);

        return this.handle(() -> this.tagProhibitedService.delete(
                (TagProhibitedServiceFilter) this.parseFilter(filter, new TagProhibitedServiceFilter())
        ), HttpStatus.OK, TAG_PROHIBITED_DELETE);
    }

    /**
     * @param id
     * @return
     * @throws Exception
     */
    public BaseMessage getTagProhibitedById(int id) throws Exception {
        FeedTypeFilterBody filter = new FeedTypeFilterBody();
        filter.setId(id);

        return this.handle(() -> this.tagProhibitedService.getOneTagProhibited(
                (TagProhibitedServiceFilter) this.parseFilter(filter, new TagProhibitedServiceFilter())
        ), HttpStatus.OK, TAG_PROHIBITED_GET_ONE);
    }

    @Override
    protected AbstractServiceRequest parseRequest(AbstractRequestBody data, AbstractServiceRequest request) {
        return this.parseBaseRequest(data, request);
    }

    @Override
    protected AbstractServiceFilter parseFilter(AbstractFilterBody data, AbstractServiceFilter filter) {
        return this.parseBaseFilter(data, filter);
    }
}