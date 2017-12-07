package org.ubikz.jscraper.api.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.ubikz.jscraper.api.dal.impl.UserDal;
import org.ubikz.jscraper.api.service.model.message.BaseMessage;
import org.ubikz.jscraper.api.service.model.message.impl.ErrorMessage;

import java.util.function.Supplier;

public abstract class AbstractController {
    protected static final Logger logger = LoggerFactory.getLogger(UserDal.class);

    public String send(Supplier<BaseMessage> function) throws JsonProcessingException {
        Object result;
        BaseMessage successMessage = null;
        ErrorMessage errorMessage = new ErrorMessage();

        try {
            successMessage = function.get();
        } catch (EmptyResultDataAccessException e) {
            errorMessage.setData(e);
            errorMessage.setStatus(HttpStatus.NOT_FOUND.value());
            errorMessage.setTitle("Not found.");
            errorMessage.setDetail("Bad query. No result returned.");
        } catch (Exception e) {
            errorMessage.setData(e);
            errorMessage.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            errorMessage.setTitle(String.format("An exception (%s) occurred.", e.getClass().getName()));
            errorMessage.setDetail(e.getMessage());
        } finally {
            result = successMessage;
            if (errorMessage.getData() != null) {
                result = errorMessage;
                logger.error("Exception occurred", errorMessage.getData());
            }
        }

        return new ObjectMapper().writeValueAsString(result);
    }
}