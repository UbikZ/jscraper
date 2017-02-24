package com.ubikz.scraper.core.app.context;

import com.ubikz.scraper.core.app.service.message.BaseMessage;
import com.ubikz.scraper.core.app.service.message.ErrorMessage;
import org.springframework.http.HttpStatus;

import java.util.concurrent.Callable;

abstract class AbstractContext {
    final protected BaseMessage handle(Callable callable) throws Exception {
        return this.handle(callable, HttpStatus.OK, 1);
    }

    final protected BaseMessage handle(Callable callable, HttpStatus defaultStatus, int defaultCode) throws Exception {
        BaseMessage result = new BaseMessage();
        ErrorMessage errorMessage = new ErrorMessage();

        Object data = null;
        boolean isSuccess = false;
        int code = defaultCode;
        int status = defaultStatus.value();

        try {
            data = callable.call();
            isSuccess = true;
        } catch (Exception e) {
            code = -1;
            status = HttpStatus.INTERNAL_SERVER_ERROR.value();
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