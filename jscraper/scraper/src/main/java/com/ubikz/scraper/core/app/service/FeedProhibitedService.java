package com.ubikz.scraper.core.app.service;

import com.ubikz.scraper.core.app.dto.FeedProhibitedDto;
import com.ubikz.scraper.core.app.entity.FeedProhibitedEntity;
import com.ubikz.scraper.core.app.entity.filter.AbstractEntityFilter;
import com.ubikz.scraper.core.app.entity.filter.FeedProhibitedEntityFilter;
import com.ubikz.scraper.core.app.entity.request.AbstractEntityRequest;
import com.ubikz.scraper.core.app.entity.request.FeedProhibitedEntityRequest;
import com.ubikz.scraper.core.app.service.filter.AbstractServiceFilter;
import com.ubikz.scraper.core.app.service.filter.FeedProhibitedServiceFilter;
import com.ubikz.scraper.core.app.service.request.AbstractServiceRequest;
import com.ubikz.scraper.core.app.service.request.FeedProhibitedServiceRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FeedProhibitedService extends AbstractService {
    private FeedProhibitedEntity feedProhibitedEntity;

    @Autowired
    public FeedProhibitedService(FeedProhibitedEntity feedProhibitedEntity) {
        this.feedProhibitedEntity = feedProhibitedEntity;
    }

    /**
     * @param filter
     * @return
     * @throws Exception
     */
    public List<FeedProhibitedDto> getAllFeedsProhibited(FeedProhibitedServiceFilter filter) throws Exception {
        return this.feedProhibitedEntity.getAllFeedsProhibited(
                (FeedProhibitedEntityFilter) this.parseServiceToEntityFilter(filter)
        );
    }

    /**
     * @param filter
     * @return
     * @throws Exception
     */
    public FeedProhibitedDto getOneFeedProhibited(FeedProhibitedServiceFilter filter) throws Exception {
        return this.feedProhibitedEntity.getOneFeedProhibited(
                (FeedProhibitedEntityFilter) this.parseServiceToEntityFilter(filter)
        );
    }

    /**
     * @param filter
     * @return
     * @throws Exception
     */
    public int delete(FeedProhibitedServiceFilter filter) throws Exception {
        return this.feedProhibitedEntity.deleteFeedProhibited(
                (FeedProhibitedEntityFilter) this.parseServiceToEntityFilter(filter)
        );
    }

    /**
     * @param request
     * @return
     * @throws Exception
     */
    public int createFeedProhibited(FeedProhibitedServiceRequest request) throws Exception {
        return this.feedProhibitedEntity.createFeedProhibited((FeedProhibitedEntityRequest) this.parseServiceToEntityRequest(request));
    }

    /**
     * @param request
     * @return
     * @throws Exception
     */
    public int updateFeedProhibited(FeedProhibitedServiceRequest request) throws Exception {
        return this.feedProhibitedEntity.updateFeedProhibited((FeedProhibitedEntityRequest) this.parseServiceToEntityRequest(request));
    }


    @Override
    protected AbstractEntityRequest parseServiceToEntityRequest(AbstractServiceRequest request) {
        FeedProhibitedEntityRequest feedProhibitedEntityRequest = new FeedProhibitedEntityRequest();
        FeedProhibitedServiceRequest feedProhibitedServiceRequest = (FeedProhibitedServiceRequest) request;

        this.parseBaseServiceToEntityRequest(feedProhibitedServiceRequest, feedProhibitedEntityRequest);

        return feedProhibitedEntityRequest;
    }

    @Override
    protected AbstractEntityFilter parseServiceToEntityFilter(AbstractServiceFilter filter) {
        FeedProhibitedEntityFilter feedProhibitedEntityFilter = new FeedProhibitedEntityFilter();
        FeedProhibitedServiceFilter feedProhibitedServiceFilter = (FeedProhibitedServiceFilter) filter;

        this.parseBaseServiceToEntityFilter(feedProhibitedServiceFilter, feedProhibitedEntityFilter);

        return feedProhibitedEntityFilter;
    }
}