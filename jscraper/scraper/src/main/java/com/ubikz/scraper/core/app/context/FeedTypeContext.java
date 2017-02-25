package com.ubikz.scraper.core.app.context;

import com.ubikz.scraper.core.app.dto.AbstractDto;
import com.ubikz.scraper.core.app.dto.FeedTypeDto;
import com.ubikz.scraper.core.app.exception.MissingParameterException;
import com.ubikz.scraper.core.app.service.FeedTypeService;
import com.ubikz.scraper.core.app.service.filter.AbstractServiceFilter;
import com.ubikz.scraper.core.app.service.filter.FeedServiceFilter;
import com.ubikz.scraper.core.app.service.filter.FeedTypeServiceFilter;
import com.ubikz.scraper.core.app.service.message.BaseMessage;
import com.ubikz.scraper.core.app.service.request.AbstractServiceRequest;
import com.ubikz.scraper.core.app.service.request.FeedTypeServiceRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class FeedTypeContext extends AbstractContext {
    final private int FEED_TYPE_CREATED = 5;
    final private int FEED_TYPE_UPDATED = 6;
    final private int FEED_TYPE_GET_ONE = 7;
    final private int FEED_TYPE_GET_ALL = 8;

    private FeedTypeService feedTypeService;

    @Autowired
    public FeedTypeContext(FeedTypeService feedTypeService) {
        this.feedTypeService = feedTypeService;
    }

    /**
     * @param request
     * @return
     * @throws Exception
     */
    public BaseMessage createFeedType(FeedTypeDto request) throws Exception {
        return this.handle(() -> {
            FeedTypeServiceRequest serviceRequest = new FeedTypeServiceRequest();

            return this.feedTypeService.createFeedType(
                    (FeedTypeServiceRequest) this.parseRequest(request, serviceRequest)
            );
        }, HttpStatus.CREATED, FEED_TYPE_CREATED);
    }

    /**
     * @param request
     * @return
     * @throws Exception
     */
    public BaseMessage updateFeedType(FeedTypeDto request) throws Exception {
        return this.handle(() -> {
            FeedTypeServiceRequest serviceRequest = new FeedTypeServiceRequest();

            if (request.getId() == null) {
                throw new MissingParameterException();
            }

            return this.feedTypeService.createFeedType(
                    (FeedTypeServiceRequest) this.parseRequest(request, serviceRequest)
            );
        }, HttpStatus.OK, FEED_TYPE_UPDATED);
    }

    /**
     * @param id
     * @return
     * @throws Exception
     */
    public BaseMessage getFeedTypeById(int id) throws Exception {
        FeedTypeDto filter = new FeedTypeDto();
        filter.setId(id);

        return this.handle(() -> {
            FeedTypeServiceFilter serviceFilter = new FeedTypeServiceFilter();
            serviceFilter = (FeedTypeServiceFilter) this.parseFilter(filter, serviceFilter);

            return this.feedTypeService.getOneFeedType(serviceFilter);
        }, HttpStatus.OK, FEED_TYPE_GET_ONE);
    }

    /**
     * @param enabled
     * @return
     * @throws Exception
     */
    public BaseMessage getAllFeedTypes(Boolean enabled) throws Exception {
        FeedTypeDto filter = new FeedTypeDto();
        filter.setEnabled(enabled);

        return this.handle(() -> {
            FeedServiceFilter serviceFilter = new FeedServiceFilter();

            return this.feedTypeService.getAllFeedTypes((FeedTypeServiceFilter) this.parseFilter(filter, serviceFilter));
        }, HttpStatus.OK, FEED_TYPE_GET_ALL);
    }

    @Override
    protected AbstractServiceRequest parseRequest(AbstractDto data, AbstractServiceRequest request) {
        FeedTypeDto feedTypeDto = (FeedTypeDto) data;
        FeedTypeServiceRequest serviceRequest = (FeedTypeServiceRequest) this.parseBaseRequest(feedTypeDto, request);
        serviceRequest.setUrlRegex(feedTypeDto.getUrlRegex());
        serviceRequest.setUrlRegex(feedTypeDto.getUrlRegex());

        return serviceRequest;
    }

    @Override
    protected AbstractServiceFilter parseFilter(AbstractDto data, AbstractServiceFilter filter) {
        FeedTypeDto feedTypeDto = (FeedTypeDto) data;
        FeedTypeServiceFilter serviceFilter = (FeedTypeServiceFilter) this.parseBaseFilter(feedTypeDto, filter);

        return serviceFilter;
    }
}