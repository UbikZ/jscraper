package org.ubikz.jscraper.api.entity.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.ubikz.jscraper.api.dal.impl.FeedTypeDal;
import org.ubikz.jscraper.api.dal.model.filter.impl.FeedTypeDalFilter;
import org.ubikz.jscraper.api.dal.model.request.impl.FeedTypeDalRequest;
import org.ubikz.jscraper.api.dto.impl.FeedTypeDto;
import org.ubikz.jscraper.api.entity.BaseEntity;
import org.ubikz.jscraper.api.entity.helper.impl.FeedTypeEntityHelper;
import org.ubikz.jscraper.api.entity.model.request.BaseEntityRequest;
import org.ubikz.jscraper.api.entity.model.request.impl.FeedTypeEntityRequest;

import java.util.List;
import java.util.Map;

@Component
public class FeedTypeEntity extends BaseEntity<FeedTypeDal, FeedTypeDalRequest, FeedTypeDalFilter, FeedTypeEntityHelper, FeedTypeDto> {
    @Autowired
    public FeedTypeEntity(FeedTypeDal dal) {
        this.dal = dal;
        this.helper = new FeedTypeEntityHelper();
        this.dalFilter = new FeedTypeDalFilter();
        this.dalRequest = new FeedTypeDalRequest();
    }

    @Override
    protected <T extends BaseEntityRequest> void parseRequest(T request) {
        super.parseRequest(request);
        FeedTypeEntityRequest entityRequest = (FeedTypeEntityRequest) request;
        dalRequest.setUrlRegex(entityRequest.getUrlRegex());
        dalRequest.setContentRegex(entityRequest.getContentRegex());
    }

    @Override
    protected void computeLoading(List<FeedTypeDto> dtoList) {
    }

    @Override
    protected void computeLoading(Map<Object, FeedTypeDto> dtoList) {
    }
}
