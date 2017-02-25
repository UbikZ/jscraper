package com.ubikz.scraper.api.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ubikz.scraper.core.app.service.message.BaseMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value="/api")
@RestController
public class AbstractController {
    protected final Logger logger = LoggerFactory.getLogger(AbstractController.class);

    /**
     * @param message
     * @return
     * @throws JsonProcessingException
     */
    public String sendResponse(BaseMessage message) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(message);
    }
}