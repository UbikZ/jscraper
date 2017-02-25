package com.ubikz.scraper.core.app.service;

import com.ubikz.scraper.core.app.dto.FeedTypeDto;
import com.ubikz.scraper.core.app.entity.FeedTypeEntity;
import com.ubikz.scraper.core.app.entity.filter.AbstractEntityFilter;
import com.ubikz.scraper.core.app.entity.filter.FeedTypeEntityFilter;
import com.ubikz.scraper.core.app.entity.request.AbstractEntityRequest;
import com.ubikz.scraper.core.app.entity.request.FeedTypeEntityRequest;
import com.ubikz.scraper.core.app.service.filter.AbstractServiceFilter;
import com.ubikz.scraper.core.app.service.filter.FeedTypeServiceFilter;
import com.ubikz.scraper.core.app.service.request.AbstractServiceRequest;
import com.ubikz.scraper.core.app.service.request.FeedServiceRequest;
import com.ubikz.scraper.core.app.service.request.FeedTypeServiceRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FeedTypeService extends AbstractService {
    private FeedTypeEntity feedTypeEntity;

    @Autowired
    public FeedTypeService(FeedTypeEntity feedTypeEntity) {
        this.feedTypeEntity = feedTypeEntity;
    }


    /**
     * @param filter
     * @return
     * @throws Exception
     */
    public List<FeedTypeDto> getAllFeedTypes(FeedTypeServiceFilter filter) throws Exception {
        return this.feedTypeEntity.getAllFeedTypes(
                (FeedTypeEntityFilter) this.parseServiceToEntityFilter(filter)
        );
    }

    /**
     * @param filter
     * @return
     * @throws Exception
     */
    public FeedTypeDto getOneFeedType(FeedTypeServiceFilter filter) throws Exception {
        return this.feedTypeEntity.getOneFeedType(
                (FeedTypeEntityFilter) this.parseServiceToEntityFilter(filter)
        );
    }

    /**
     * @param filter
     * @return
     * @throws Exception
     */
    public int delete(FeedTypeServiceFilter filter) throws Exception {
        return this.feedTypeEntity.deleteFeedType(
                (FeedTypeEntityFilter) this.parseServiceToEntityFilter(filter)
        );
    }

    /**
     * @param request
     * @return
     * @throws Exception
     */
    public int createFeedType(FeedTypeServiceRequest request) throws Exception {
        return this.feedTypeEntity.createFeedType(
                (FeedTypeEntityRequest) this.parseServiceToEntityRequest(request)
        );
    }

    /**
     * @param request
     * @return
     * @throws Exception
     */
    public int updateFeedType(FeedServiceRequest request) throws Exception {
        return this.feedTypeEntity.updateFeedType(
                (FeedTypeEntityRequest) this.parseServiceToEntityRequest(request)
        );
    }

    @Override
    protected AbstractEntityRequest parseServiceToEntityRequest(AbstractServiceRequest request) {
        FeedTypeEntityRequest feedTypeEntityRequest = new FeedTypeEntityRequest();
        FeedTypeServiceRequest feedTypeServiceRequest = (FeedTypeServiceRequest) request;

        this.parseBaseServiceToEntityRequest(feedTypeServiceRequest, feedTypeEntityRequest);
        feedTypeEntityRequest.setUrlRegex(feedTypeServiceRequest.getUrlRegex());
        feedTypeEntityRequest.setContentRegex(feedTypeServiceRequest.getContentRegex());

        return feedTypeEntityRequest;
    }

    @Override
    protected AbstractEntityFilter parseServiceToEntityFilter(AbstractServiceFilter filter) {
        FeedTypeEntityFilter feedTypeEntityFilter = new FeedTypeEntityFilter();
        FeedTypeServiceFilter feedTypeServiceFilter = (FeedTypeServiceFilter) filter;

        this.parseBaseServiceToEntityFilter(feedTypeServiceFilter, feedTypeEntityFilter);

        return feedTypeEntityFilter;
    }
}