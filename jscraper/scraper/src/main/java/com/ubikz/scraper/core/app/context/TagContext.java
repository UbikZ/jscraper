package com.ubikz.scraper.core.app.context;

import com.ubikz.scraper.core.app.dto.AbstractDto;
import com.ubikz.scraper.core.app.dto.TagDto;
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
    final private int FEED_TYPE_CREATED = 5;
    final private int FEED_TYPE_UPDATED = 6;
    final private int FEED_TYPE_GET_ONE = 7;
    final private int FEED_TYPE_GET_ALL = 8;

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
    public BaseMessage createTag(TagDto request) throws Exception {
        return this.handle(() -> {
            TagServiceRequest serviceRequest = new TagServiceRequest();

            return this.tagService.createTag(
                    (TagServiceRequest) this.parseRequest(request, serviceRequest)
            );
        }, HttpStatus.CREATED, FEED_TYPE_CREATED);
    }

    /**
     * @param request
     * @return
     * @throws Exception
     */
    public BaseMessage updateTag(TagDto request) throws Exception {
        return this.handle(() -> {
            TagServiceRequest serviceRequest = new TagServiceRequest();

            if (request.getId() == null) {
                throw new MissingParameterException();
            }

            return this.tagService.createTag(
                    (TagServiceRequest) this.parseRequest(request, serviceRequest)
            );
        }, HttpStatus.OK, FEED_TYPE_UPDATED);
    }

    /**
     * @param enabled
     * @return
     * @throws Exception
     */
    public BaseMessage getAllTags(Boolean enabled) throws Exception {
        TagDto filter = new TagDto();
        filter.setEnabled(enabled);

        return this.handle(() -> {
            TagServiceFilter serviceFilter = new TagServiceFilter();

            return this.tagService.getAllTags((TagServiceFilter) this.parseFilter(filter, serviceFilter));
        }, HttpStatus.OK, FEED_TYPE_GET_ALL);
    }

    /**
     * @param id
     * @return
     * @throws Exception
     */
    public BaseMessage getTagById(int id) throws Exception {
        TagDto filter = new TagDto();
        filter.setId(id);

        return this.handle(() -> {
            TagServiceFilter serviceFilter = new TagServiceFilter();
            serviceFilter = (TagServiceFilter) this.parseFilter(filter, serviceFilter);

            return this.tagService.getOneTag(serviceFilter);
        }, HttpStatus.OK, FEED_TYPE_GET_ONE);
    }

    @Override
    protected AbstractServiceRequest parseRequest(AbstractDto data, AbstractServiceRequest request) {
        TagDto feedTypeDto = (TagDto) data;
        TagServiceRequest serviceRequest = (TagServiceRequest) this.parseBaseRequest(feedTypeDto, request);

        return serviceRequest;
    }

    @Override
    protected AbstractServiceFilter parseFilter(AbstractDto data, AbstractServiceFilter filter) {
        TagDto feedTypeDto = (TagDto) data;
        TagServiceFilter serviceFilter = (TagServiceFilter) this.parseBaseFilter(feedTypeDto, filter);

        return serviceFilter;
    }
}