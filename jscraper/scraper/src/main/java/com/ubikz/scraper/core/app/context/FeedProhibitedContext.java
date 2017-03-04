package com.ubikz.scraper.core.app.context;

import com.ubikz.scraper.api.controller.filter.AbstractFilterBody;
import com.ubikz.scraper.api.controller.filter.FeedProhibitedFilterBody;
import com.ubikz.scraper.api.controller.request.AbstractRequestBody;
import com.ubikz.scraper.api.controller.request.FeedProhibitedRequestBody;
import com.ubikz.scraper.core.app.exception.MissingParameterException;
import com.ubikz.scraper.core.app.service.FeedProhibitedService;
import com.ubikz.scraper.core.app.service.filter.AbstractServiceFilter;
import com.ubikz.scraper.core.app.service.filter.FeedProhibitedServiceFilter;
import com.ubikz.scraper.core.app.service.message.BaseMessage;
import com.ubikz.scraper.core.app.service.request.AbstractServiceRequest;
import com.ubikz.scraper.core.app.service.request.FeedProhibitedServiceRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class FeedProhibitedContext extends AbstractContext {
    final private int FEED_PROHIBITED_CREATED = 50;
    final private int FEED_PROHIBITED_UPDATED = 51;
    final private int FEED_PROHIBITED_GET_ONE = 52;
    final private int FEED_PROHIBITED_GET_ALL = 52;
    final private int FEED_PROHIBITED_DELETE = 53;

    private FeedProhibitedService feedProhibitedService;

    @Autowired
    public FeedProhibitedContext(FeedProhibitedService feedProhibitedService) {
        this.feedProhibitedService = feedProhibitedService;
    }

    /**
     * @param request
     * @return
     * @throws Exception
     */
    public BaseMessage createFeedProhibited(FeedProhibitedRequestBody request) throws Exception {
        return this.handle(() -> this.feedProhibitedService.createFeedProhibited(
                (FeedProhibitedServiceRequest) this.parseRequest(request, new FeedProhibitedServiceRequest())
        ), HttpStatus.CREATED, FEED_PROHIBITED_CREATED);
    }

    /**
     * @param request
     * @return
     * @throws Exception
     */
    public BaseMessage updateFeedProhibited(Integer id, FeedProhibitedRequestBody request) throws Exception {
        return this.handle(() -> {
            FeedProhibitedServiceRequest serviceRequest = new FeedProhibitedServiceRequest();
            serviceRequest.setId(id);

            if (id == null) {
                throw new MissingParameterException();
            }

            return this.feedProhibitedService.updateFeedProhibited((FeedProhibitedServiceRequest) this.parseRequest(request, serviceRequest));
        }, HttpStatus.OK, FEED_PROHIBITED_UPDATED);
    }

    /**
     * @param id
     * @return
     * @throws Exception
     */
    public BaseMessage getFeedProhibitedById(int id) throws Exception {
        FeedProhibitedFilterBody filter = new FeedProhibitedFilterBody();
        filter.setId(id);

        return this.handle(() -> this.feedProhibitedService.getOneFeedProhibited(
                (FeedProhibitedServiceFilter) this.parseFilter(filter, new FeedProhibitedServiceFilter())
        ), HttpStatus.OK, FEED_PROHIBITED_GET_ONE);
    }

    /**
     * @param id
     * @return
     * @throws Exception
     */
    public BaseMessage deleteFeedProhibitedById(int id) throws Exception {
        FeedProhibitedFilterBody filter = new FeedProhibitedFilterBody();
        filter.setId(id);

        return this.handle(() -> this.feedProhibitedService.delete(
                (FeedProhibitedServiceFilter) this.parseFilter(filter, new FeedProhibitedServiceFilter())
        ), HttpStatus.OK, FEED_PROHIBITED_DELETE);
    }

    /**
     * @param filter
     * @return
     * @throws Exception
     */
    public BaseMessage getAllFeedProhibiteds(FeedProhibitedFilterBody filter) throws Exception {
        return this.handle(() -> this.feedProhibitedService.getAllFeedsProhibited(
                (FeedProhibitedServiceFilter) this.parseFilter(filter, new FeedProhibitedServiceFilter())
        ), HttpStatus.OK, FEED_PROHIBITED_GET_ALL);
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