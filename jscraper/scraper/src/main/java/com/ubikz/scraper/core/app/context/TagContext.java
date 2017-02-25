package com.ubikz.scraper.core.app.context;

import com.ubikz.scraper.core.app.dto.AbstractDto;
import com.ubikz.scraper.core.app.dto.FeedTypeDto;
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
    final private int TAG_TYPE_CREATED = 30;
    final private int TAG_TYPE_UPDATED = 31;
    final private int TAG_TYPE_GET_ONE = 32;
    final private int TAG_TYPE_GET_ALL = 33;
    final private int TAG_TYPE_DELETE = 34;

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
        }, HttpStatus.CREATED, TAG_TYPE_CREATED);
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
        }, HttpStatus.OK, TAG_TYPE_UPDATED);
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
        }, HttpStatus.OK, TAG_TYPE_GET_ALL);
    }

    /**
     * @param id
     * @return
     * @throws Exception
     */
    public BaseMessage deleteTagById(int id) throws Exception {
        FeedTypeDto filter = new FeedTypeDto();
        filter.setId(id);

        return this.handle(() -> {
            TagServiceFilter serviceFilter = new TagServiceFilter();

            return this.tagService.delete((TagServiceFilter) this.parseFilter(filter, serviceFilter));
        }, HttpStatus.OK, TAG_TYPE_DELETE);
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
        }, HttpStatus.OK, TAG_TYPE_GET_ONE);
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