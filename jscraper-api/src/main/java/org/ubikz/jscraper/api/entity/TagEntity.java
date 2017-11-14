package org.ubikz.jscraper.api.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.ubikz.jscraper.api.dal.impl.TagDal;
import org.ubikz.jscraper.api.dal.model.filter.AbstractDalFilter;
import org.ubikz.jscraper.api.dal.model.filter.impl.TagDalFilter;
import org.ubikz.jscraper.api.dal.model.request.AbstractDalRequest;
import org.ubikz.jscraper.api.dal.model.request.impl.TagDalRequest;
import org.ubikz.jscraper.api.dto.AbstractDto;
import org.ubikz.jscraper.api.entity.model.filter.AbstractEntityFilter;
import org.ubikz.jscraper.api.entity.model.filter.impl.TagEntityFilter;
import org.ubikz.jscraper.api.entity.helper.impl.TagEntityHelper;
import org.ubikz.jscraper.api.entity.model.request.AbstractEntityRequest;
import org.ubikz.jscraper.api.entity.model.request.impl.TagEntityRequest;

import java.util.List;
import java.util.Map;

@Component
public class TagEntity extends AbstractEntity {
    @Autowired
    public TagEntity(TagDal tagDal) {
        this.dal = tagDal;
        this.helper = new TagEntityHelper();
    }

    @Override
    protected void computeLoading(List<AbstractDto> dtoList) {

    }

    @Override
    protected void computeLoading(Map<Object, AbstractDto> dtoList) throws Exception {
    }

    /**
     * @param request
     * @return
     */
    @Override
    protected AbstractDalRequest parseEntityToDalRequest(AbstractEntityRequest request) {
        TagDalRequest tagDalRequest = new TagDalRequest();
        TagEntityRequest tagEntityRequest = (TagEntityRequest) request;

        tagDalRequest = (TagDalRequest) this.parseBaseEntityToDalRequest(tagEntityRequest, tagDalRequest);

        return tagDalRequest;
    }

    /**
     * @param filter
     * @return
     */
    @Override
    protected AbstractDalFilter parseEntityToDalFilter(AbstractEntityFilter filter) {
        TagDalFilter tagDalFilter = new TagDalFilter();
        TagEntityFilter tagEntityFilter = (TagEntityFilter) filter;

        tagDalFilter = (TagDalFilter) this.parseBaseEntityToDalFilter(tagEntityFilter, tagDalFilter);
        tagDalFilter.setNameList(tagEntityFilter.getNameList());

        return tagDalFilter;
    }
}