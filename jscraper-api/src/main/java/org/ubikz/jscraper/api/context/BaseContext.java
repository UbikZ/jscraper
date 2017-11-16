package org.ubikz.jscraper.api.context;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.ubikz.jscraper.api.controller.model.filter.BaseFilterBody;
import org.ubikz.jscraper.api.controller.model.request.BaseRequestBody;
import org.ubikz.jscraper.api.service.BaseService;
import org.ubikz.jscraper.api.service.model.filter.BaseServiceFilter;
import org.ubikz.jscraper.api.service.model.message.BaseMessage;
import org.ubikz.jscraper.api.service.model.message.ErrorMessage;
import org.ubikz.jscraper.api.service.model.request.BaseServiceRequest;
import org.ubikz.jscraper.exception.ApplicativeException;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.stream.Collectors;

@Component
public abstract class BaseContext {
    private static final int CODE_ERROR = -1;
    private static final int CODE_DATA_NOT_FOUND = -2;
    protected static int CREATED;
    protected static int UPDATED;
    protected static int GET_ONE;
    protected static int GET_ALL;
    protected static int DELETE;
    protected final Logger logger = LoggerFactory.getLogger(BaseContext.class);
    protected BaseService service;
    protected BaseServiceRequest serviceRequest;
    protected BaseServiceFilter serviceFilter;
    protected BaseFilterBody filterBody;

    /**
     * @param request
     * @return
     * @throws Exception
     */
    public BaseMessage create(BaseRequestBody request) {
        return handle(() -> service.create(parseRequest(request, serviceRequest)), HttpStatus.CREATED, CREATED);
    }

    /**
     * @param request
     * @return
     * @throws Exception
     */
    public BaseMessage update(Integer id, BaseRequestBody request) {
        return handle(() -> {
            serviceRequest.setId(id);

            if (id == null) {
                throw new ApplicativeException();
            }

            return service.update(parseRequest(request, serviceRequest));
        }, HttpStatus.OK, UPDATED);
    }

    /**
     * @param id
     * @return
     * @throws Exception
     */
    public BaseMessage getById(int id) {
        BaseFilterBody filter = filterBody;
        filter.setId(id);

        return handle(() -> service.get(parseFilter(filter, serviceFilter)), HttpStatus.OK, GET_ONE);
    }

    /**
     * @param id
     * @return
     * @throws Exception
     */
    public BaseMessage deleteById(int id) {
        BaseFilterBody filter = filterBody;
        filter.setId(id);

        return handle(() -> service.delete(parseFilter(filter, serviceFilter)), HttpStatus.OK, DELETE);
    }

    /**
     * @param filter
     * @return
     * @throws Exception
     */
    public BaseMessage getAll(BaseFilterBody filter) {
        return handle(() -> service.getAll(parseFilter(filter, serviceFilter)), HttpStatus.OK, GET_ALL);
    }

    /**
     * @param filter
     * @return
     * @throws Exception
     */
    public int count(BaseFilterBody filter) throws Exception {
        return service.count(parseFilter(filter, serviceFilter));
    }

    /**
     * @param callable
     * @return
     * @throws Exception
     */
    protected final BaseMessage handle(Callable callable) {
        return handle(callable, HttpStatus.OK, 1);
    }

    /**
     * @param callable
     * @param defaultStatus
     * @param defaultCode
     * @return
     * @throws Exception
     */
    protected final BaseMessage handle(Callable callable, HttpStatus defaultStatus, int defaultCode) {
        BaseMessage result = new BaseMessage();
        ErrorMessage errorMessage = new ErrorMessage();
        Exception exception = null;

        Object data = null;
        boolean isSuccess = false;
        int size = 1;
        int code = defaultCode;
        int status = defaultStatus.value();

        try {
            data = callable.call();
            if (data instanceof List) {
                size = ((List) data).size();
            }
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
            result.setSize(size);
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
                logger.error("Exception : " + errorLog.stream().collect(Collectors.joining("\n")));
            }
        }

        return result;
    }

    /**
     * @param request
     * @return
     */
    protected abstract BaseServiceRequest parseRequest(BaseRequestBody data, BaseServiceRequest request);

    /**
     * @param filter
     * @return
     */
    protected abstract BaseServiceFilter parseFilter(BaseFilterBody data, BaseServiceFilter filter) throws Exception;

    /**
     * @param data
     * @param request
     * @return
     */
    protected final BaseServiceRequest parseBaseRequest(BaseRequestBody data, BaseServiceRequest request) {
        request.setLabel(data.getLabel());
        request.setEnabled(data.getEnabled());

        return request;
    }

    /**
     * @param data
     * @param filter
     * @return
     */
    protected final BaseServiceFilter parseBaseFilter(BaseFilterBody data, BaseServiceFilter filter) throws Exception {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        filter.setId(data.getId());
        filter.setLabel(data.getLabel());
        filter.setSearch(data.getSearch());
        filter.setEnabled(data.getEnabled());
        filter.setLazy(data.isLazy());
        filter.setStartDate(data.getStartDate() == null ? null : formatter.parse(data.getStartDate()));
        filter.setEndDate(data.getEndDate() == null ? null : formatter.parse(data.getEndDate()));
        filter.setLimit(data.getLimit());
        filter.setOffset(data.getOffset());

        return filter;
    }
}