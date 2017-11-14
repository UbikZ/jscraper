package org.ubikz.jscraper.api.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.ubikz.jscraper.api.service.model.message.BaseMessage;

@RestController
@RequestMapping(value = "/api")
public abstract class ApiController {
    /**
     * @param message
     * @return
     * @throws JsonProcessingException
     */
    public static String buildMessage(BaseMessage message) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(message);
    }

    /**
     * @param message
     * @return
     * @throws JsonProcessingException
     */
    public String sendResponse(BaseMessage message) throws JsonProcessingException {
        return ApiController.buildMessage(message);
    }
}