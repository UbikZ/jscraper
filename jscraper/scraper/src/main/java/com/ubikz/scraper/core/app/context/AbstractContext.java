package com.ubikz.scraper.core.app.context;

import com.ubikz.scraper.api.controller.filter.AbstractFilterBody;
import com.ubikz.scraper.api.controller.request.AbstractRequestBody;
import com.ubikz.scraper.core.app.service.filter.AbstractServiceFilter;
import com.ubikz.scraper.core.app.service.message.BaseMessage;
import com.ubikz.scraper.core.app.service.message.ErrorMessage;
import com.ubikz.scraper.core.app.service.request.AbstractServiceRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.stream.Collectors;

abstract class AbstractContext {
    protected final Logger logger = LoggerFactory.getLogger(AbstractContext.class);

    final private int CODE_ERROR = -1;
    final private int CODE_DATA_NOT_FOUND = -2;

    /**
     * @param callable
     * @return
     * @throws Exception
     */
    final protected BaseMessage handle(Callable callable) throws Exception {
        return this.handle(callable, HttpStatus.OK, 1);
    }

    /**
     * @param callable
     * @param defaultStatus
     * @param defaultCode
     * @return
     * @throws Exception
     */
    final protected BaseMessage handle(Callable callable, HttpStatus defaultStatus, int defaultCode) throws Exception {
        BaseMessage result = new BaseMessage();
        ErrorMessage errorMessage = new ErrorMessage();
        Exception exception = null;

        Object data = null;
        boolean isSuccess = false;
        int code = defaultCode;
        int status = defaultStatus.value();

        try {
            data = callable.call();
            isSuccess = true;
        } catch (EmptyResultDataAccessException e) {
            exception = e;
            code = CODE_DATA_NOT_FOUND;
            status = HttpStatus.NOT_FOUND.value();
            errorMessage.setTitle("Not found.");
            errorMessage.setDetail("Bad query. No result returned.");
            data = errorMessage;
        } catch (Exception e) {
            exception = e;
            code = CODE_ERROR;
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
            if (exception != null) {
                List<String> errorLog = new ArrayList<>();
                errorLog.add(exception.getMessage());
                for (StackTraceElement stackTraceElement : exception.getStackTrace()) {
                    errorLog.add("      " + stackTraceElement);
                }
                this.logger.error("Exception : " + errorLog.stream().collect(Collectors.joining("\n")));
            }
        }

        return result;
    }

    /**
     * @param request
     * @return
     */
    abstract protected AbstractServiceRequest parseRequest(AbstractRequestBody data, AbstractServiceRequest request);

    /**
     * @param filter
     * @return
     */
    abstract protected AbstractServiceFilter parseFilter(AbstractFilterBody data, AbstractServiceFilter filter);

    /**
     * @param data
     * @param request
     * @return
     */
    final protected AbstractServiceRequest parseBaseRequest(AbstractRequestBody data, AbstractServiceRequest request) {
        request.setLabel(data.getLabel());
        request.setEnabled(data.getEnabled());

        return request;
    }

    /**
     * @param data
     * @param filter
     * @return
     */
    final protected AbstractServiceFilter parseBaseFilter(AbstractFilterBody data, AbstractServiceFilter filter) {
        filter.setId(data.getId());
        filter.setLabel(data.getLabel());
        filter.setEnabled(data.getEnabled());
        filter.setLazy(data.isLazy());

        return filter;
    }
}