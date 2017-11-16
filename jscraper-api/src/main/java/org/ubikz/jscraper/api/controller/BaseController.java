package org.ubikz.jscraper.api.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.ubikz.jscraper.api.context.BaseContext;
import org.ubikz.jscraper.api.controller.model.filter.BaseFilterBody;
import org.ubikz.jscraper.api.controller.model.request.BaseRequestBody;
import org.ubikz.jscraper.api.service.model.message.BaseMessage;

public abstract class BaseController<C extends BaseContext, R extends BaseRequestBody, F extends BaseFilterBody> {
    protected C context;

    public static String buildMessage(BaseMessage message) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(message);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST, produces = "application/json")
    public String create(@RequestBody final R request) throws Exception {
        return sendResponse(context.create(request));
    }

    @RequestMapping(value = "/", method = RequestMethod.GET, produces = "application/json")
    public String get(final F filter) throws Exception {
        return sendResponse(context.getAll(filter));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces = "application/json")
    public String update(@PathVariable("id") final int id, @RequestBody final R request) throws JsonProcessingException {
        return sendResponse(context.update(id, request));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
    public String getById(@PathVariable("id") final int id) throws JsonProcessingException {
        return sendResponse(context.getById(id));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "application/json")
    public String deleteById(@PathVariable("id") final int id) throws JsonProcessingException {
        return sendResponse(context.deleteById(id));
    }

    protected String sendResponse(BaseMessage message) throws JsonProcessingException {
        return buildMessage(message);
    }
}