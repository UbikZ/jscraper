package org.ubikz.jscraper.api.entity.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.ubikz.jscraper.api.dal.impl.FeedProhibitedDal;
import org.ubikz.jscraper.api.dal.model.filter.impl.FeedProhibitedDalFilter;
import org.ubikz.jscraper.api.dal.model.request.impl.FeedProhibitedDalRequest;
import org.ubikz.jscraper.api.dto.impl.FeedProhibitedDto;
import org.ubikz.jscraper.api.entity.BaseEntity;
import org.ubikz.jscraper.api.entity.helper.impl.FeedProhibitedEntityHelper;

import java.util.List;
import java.util.Map;

@Component
public class FeedProhibitedEntity extends BaseEntity<FeedProhibitedDal, FeedProhibitedDalRequest, FeedProhibitedDalFilter, FeedProhibitedEntityHelper, FeedProhibitedDto> {
    @Autowired
    public FeedProhibitedEntity(FeedProhibitedDal dal) {
        this.dal = dal;
        this.helper = new FeedProhibitedEntityHelper();
        this.dalFilter = new FeedProhibitedDalFilter();
        this.dalRequest = new FeedProhibitedDalRequest();
    }

    @Override
    protected void computeLoading(List<FeedProhibitedDto> dtoList) {
    }

    @Override
    protected void computeLoading(Map<Object, FeedProhibitedDto> dtoList) {
    }
}
