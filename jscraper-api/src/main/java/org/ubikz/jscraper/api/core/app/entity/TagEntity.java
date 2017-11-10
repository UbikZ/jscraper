package org.ubikz.jscraper.api.core.app.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.ubikz.jscraper.api.core.app.dal.TagDal;
import org.ubikz.jscraper.api.core.app.dal.filter.AbstractDalFilter;
import org.ubikz.jscraper.api.core.app.dal.filter.TagDalFilter;
import org.ubikz.jscraper.api.core.app.dal.request.AbstractDalRequest;
import org.ubikz.jscraper.api.core.app.dal.request.TagDalRequest;
import org.ubikz.jscraper.api.core.app.dto.AbstractDto;
import org.ubikz.jscraper.api.core.app.entity.filter.AbstractEntityFilter;
import org.ubikz.jscraper.api.core.app.entity.filter.TagEntityFilter;
import org.ubikz.jscraper.api.core.app.entity.helper.TagEntityHelper;
import org.ubikz.jscraper.api.core.app.entity.request.AbstractEntityRequest;
import org.ubikz.jscraper.api.core.app.entity.request.TagEntityRequest;

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