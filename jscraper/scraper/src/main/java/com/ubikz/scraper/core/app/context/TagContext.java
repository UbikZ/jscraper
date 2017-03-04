package com.ubikz.scraper.core.app.context;

import com.ubikz.scraper.api.controller.filter.AbstractFilterBody;
import com.ubikz.scraper.api.controller.filter.FeedTypeFilterBody;
import com.ubikz.scraper.api.controller.filter.TagFilterBody;
import com.ubikz.scraper.api.controller.request.AbstractRequestBody;
import com.ubikz.scraper.api.controller.request.TagRequestBody;
import com.ubikz.scraper.core.app.exception.MissingParameterException;
import com.ubikz.scraper.core.app.service.TagService;
import com.ubikz.scraper.core.app.service.filter.AbstractServiceFilter;
import com.ubikz.scraper.core.app.service.filter.TagServiceFilter;
import com.ubikz.scraper.core.app.service.message.BaseMessage;
import com.ubikz.scraper.core.app.service.request.AbstractServiceRequest;
import com.ubikz.scraper.core.app.service.request.TagServiceRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class TagContext extends AbstractContext {
    final private int TAG_CREATED = 30;
    final private int TAG_UPDATED = 31;
    final private int TAG_GET_ONE = 32;
    final private int TAG_GET_ALL = 33;
    final private int TAG_DELETE = 34;

    private TagService tagService;

    @Autowired
    public TagContext(TagService tagService) {
        this.tagService = tagService;
    }

    /**
     * @param request
     * @return
     * @throws Exception
     */
    public BaseMessage createTag(TagRequestBody request) throws Exception {
        return this.handle(() -> this.tagService.createTag(
                (TagServiceRequest) this.parseRequest(request, new TagServiceRequest())
        ), HttpStatus.CREATED, TAG_CREATED);
    }

    /**
     * @param request
     * @return
     * @throws Exception
     */
    public BaseMessage updateTag(Integer id, TagRequestBody request) throws Exception {
        return this.handle(() -> {
            TagServiceRequest serviceRequest = new TagServiceRequest();
            serviceRequest.setId(id);

            if (id == null) {
                throw new MissingParameterException();
            }

            return this.tagService.createTag(
                    (TagServiceRequest) this.parseRequest(request, serviceRequest)
            );
        }, HttpStatus.OK, TAG_UPDATED);
    }

    /**
     * @param filter
     * @return
     * @throws Exception
     */
    public BaseMessage getAllTags(TagFilterBody filter) throws Exception {
        return this.handle(() -> this.tagService.getAllTags(
                (TagServiceFilter) this.parseFilter(filter, new TagServiceFilter())
        ), HttpStatus.OK, TAG_GET_ALL);
    }

    /**
     * @param id
     * @return
     * @throws Exception
     */
    public BaseMessage deleteTagById(int id) throws Exception {
        FeedTypeFilterBody filter = new FeedTypeFilterBody();
        filter.setId(id);

        return this.handle(() -> this.tagService.delete(
                (TagServiceFilter) this.parseFilter(filter, new TagServiceFilter())
        ), HttpStatus.OK, TAG_DELETE);
    }

    /**
     * @param id
     * @return
     * @throws Exception
     */
    public BaseMessage getTagById(int id) throws Exception {
        FeedTypeFilterBody filter = new FeedTypeFilterBody();
        filter.setId(id);

        return this.handle(() -> this.tagService.getOneTag(
                (TagServiceFilter) this.parseFilter(filter, new TagServiceFilter())
        ), HttpStatus.OK, TAG_GET_ONE);
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