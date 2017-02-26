package com.ubikz.scraper.core.app.context;

import com.ubikz.scraper.api.controller.filter.AbstractFilterBody;
import com.ubikz.scraper.api.controller.filter.FeedTypeFilterBody;
import com.ubikz.scraper.api.controller.request.AbstractRequestBody;
import com.ubikz.scraper.api.controller.request.FeedTypeRequestBody;
import com.ubikz.scraper.core.app.exception.MissingParameterException;
import com.ubikz.scraper.core.app.service.FeedTypeService;
import com.ubikz.scraper.core.app.service.filter.AbstractServiceFilter;
import com.ubikz.scraper.core.app.service.filter.FeedTypeServiceFilter;
import com.ubikz.scraper.core.app.service.message.BaseMessage;
import com.ubikz.scraper.core.app.service.request.AbstractServiceRequest;
import com.ubikz.scraper.core.app.service.request.FeedTypeServiceRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class FeedTypeContext extends AbstractContext {
    final private int FEED_TYPE_CREATED = 20;
    final private int FEED_TYPE_UPDATED = 21;
    final private int FEED_TYPE_GET_ONE = 22;
    final private int FEED_TYPE_GET_ALL = 23;
    final private int FEED_TYPE_DELETE = 24;

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
    public BaseMessage createFeedType(FeedTypeRequestBody request) throws Exception {
        return this.handle(() -> this.feedTypeService.createFeedType(
                (FeedTypeServiceRequest) this.parseRequest(request, new FeedTypeServiceRequest())
        ), HttpStatus.CREATED, FEED_TYPE_CREATED);
    }

    /**
     * @param request
     * @return
     * @throws Exception
     */
    public BaseMessage updateFeedType(Integer id, FeedTypeRequestBody request) throws Exception {
        return this.handle(() -> {
            if (id == null) {
                throw new MissingParameterException();
            }

            return this.feedTypeService.createFeedType(
                    (FeedTypeServiceRequest) this.parseRequest(request, new FeedTypeServiceRequest())
            );
        }, HttpStatus.OK, FEED_TYPE_UPDATED);
    }

    /**
     * @param id
     * @return
     * @throws Exception
     */
    public BaseMessage getFeedTypeById(int id) throws Exception {
        FeedTypeFilterBody filter = new FeedTypeFilterBody();
        filter.setId(id);

        return this.handle(() -> this.feedTypeService.getOneFeedType(
                (FeedTypeServiceFilter) this.parseFilter(filter, new FeedTypeServiceFilter())
        ), HttpStatus.OK, FEED_TYPE_GET_ONE);
    }

    /**
     * @param id
     * @return
     * @throws Exception
     */
    public BaseMessage deleteFeedTypeById(int id) throws Exception {
        FeedTypeFilterBody filter = new FeedTypeFilterBody();
        filter.setId(id);

        return this.handle(() -> this.feedTypeService.delete(
                (FeedTypeServiceFilter) this.parseFilter(filter, new FeedTypeServiceFilter())
        ), HttpStatus.OK, FEED_TYPE_DELETE);
    }

    /**
     * @param filter
     * @return
     * @throws Exception
     */
    public BaseMessage getAllFeedTypes(FeedTypeFilterBody filter) throws Exception {
        return this.handle(() -> this.feedTypeService.getAllFeedTypes(
                (FeedTypeServiceFilter) this.parseFilter(filter, new FeedTypeServiceFilter())
        ), HttpStatus.OK, FEED_TYPE_GET_ALL);
    }

    @Override
    protected AbstractServiceRequest parseRequest(AbstractRequestBody data, AbstractServiceRequest request) {
        FeedTypeRequestBody requestBody = (FeedTypeRequestBody) data;
        FeedTypeServiceRequest serviceRequest = (FeedTypeServiceRequest) this.parseBaseRequest(requestBody, request);
        serviceRequest.setUrlRegex(requestBody.getUrlRegex());
        serviceRequest.setContentRegex(requestBody.getContentRegex());

        return serviceRequest;
    }

    @Override
    protected AbstractServiceFilter parseFilter(AbstractFilterBody data, AbstractServiceFilter filter) {
        return this.parseBaseFilter(data, filter);
    }
}