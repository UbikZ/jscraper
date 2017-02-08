package com.ubikz.scraper.api.service;

import com.ubikz.scraper.api.service.message.BaseMessage;
import com.ubikz.scraper.api.service.message.ErrorMessage;

import java.util.concurrent.Callable;

abstract class AbstractService {
    final protected BaseMessage handle(Callable callable) throws Exception {
        return this.handle(callable, 200, 1);
    }

    final protected BaseMessage handle(Callable callable, int defaultStatus, int defaultCode) throws Exception {
        BaseMessage result = new BaseMessage();
        ErrorMessage errorMessage = new ErrorMessage();

        Object data = null;
        boolean isSuccess = false;
        int code = defaultCode;
        int status = defaultStatus;

        try {
            data = callable.call();
            isSuccess = true;
        } catch (Exception e) {
            code = -1;
            String detail = e.getMessage() + "\n";
            for (StackTraceElement stackTraceElement : e.getStackTrace()) {
                detail += stackTraceElement + "\n";
            }
            errorMessage.setTitle("An exception (" + e.getClass().getName() + ") occurred.");
            errorMessage.setDetail(detail);
            data = errorMessage;
        } finally {
            result.setData(data);
            result.setCode(code);
            result.setStatus(status);
            result.setSuccess(isSuccess);
        }

        return result;
    }
}