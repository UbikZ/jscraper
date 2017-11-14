package org.ubikz.jscraper.api.context;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.ubikz.jscraper.api.controller.model.filter.AbstractFilterBody;
import org.ubikz.jscraper.api.controller.model.request.AbstractRequestBody;
import org.ubikz.jscraper.api.service.AbstractService;
import org.ubikz.jscraper.api.service.model.filter.AbstractServiceFilter;
import org.ubikz.jscraper.api.service.model.message.BaseMessage;
import org.ubikz.jscraper.api.service.model.message.ErrorMessage;
import org.ubikz.jscraper.api.service.model.request.AbstractServiceRequest;
import org.ubikz.jscraper.exception.ApplicativeException;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.stream.Collectors;

public abstract class AbstractContext {
    private static final int CODE_ERROR = -1;
    private static final int CODE_DATA_NOT_FOUND = -2;
    protected static int CREATED;
    protected static int UPDATED;
    protected static int GET_ONE;
    protected static int GET_ALL;
    protected static int DELETE;
    protected final Logger logger = LoggerFactory.getLogger(AbstractContext.class);
    protected AbstractService service;
    protected AbstractServiceRequest serviceRequest;
    protected AbstractServiceFilter serviceFilter;
    protected AbstractFilterBody filterBody;

    /**
     * @param request
     * @return
     * @throws Exception
     */
    public BaseMessage create(AbstractRequestBody request) {
        return handle(() -> service.create(parseRequest(request, serviceRequest)), HttpStatus.CREATED, CREATED);
    }

    /**
     * @param request
     * @return
     * @throws Exception
     */
    public BaseMessage update(Integer id, AbstractRequestBody request) {
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
        AbstractFilterBody filter = filterBody;
        filter.setId(id);

        return handle(() -> service.get(parseFilter(filter, serviceFilter)), HttpStatus.OK, GET_ONE);
    }

    /**
     * @param id
     * @return
     * @throws Exception
     */
    public BaseMessage deleteById(int id) {
        AbstractFilterBody filter = filterBody;
        filter.setId(id);

        return handle(() -> service.delete(parseFilter(filter, serviceFilter)), HttpStatus.OK, DELETE);
    }

    /**
     * @param filter
     * @return
     * @throws Exception
     */
    public BaseMessage getAll(AbstractFilterBody filter) {
        return handle(() -> service.getAll(parseFilter(filter, serviceFilter)), HttpStatus.OK, GET_ALL);
    }

    /**
     * @param filter
     * @return
     * @throws Exception
     */
    public int count(AbstractFilterBody filter) throws Exception {
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
    protected abstract AbstractServiceRequest parseRequest(AbstractRequestBody data, AbstractServiceRequest request);

    /**
     * @param filter
     * @return
     */
    protected abstract AbstractServiceFilter parseFilter(AbstractFilterBody data, AbstractServiceFilter filter) throws Exception;

    /**
     * @param data
     * @param request
     * @return
     */
    protected final AbstractServiceRequest parseBaseRequest(AbstractRequestBody data, AbstractServiceRequest request) {
        request.setLabel(data.getLabel());
        request.setEnabled(data.getEnabled());

        return request;
    }

    /**
     * @param data
     * @param filter
     * @return
     */
    protected final AbstractServiceFilter parseBaseFilter(AbstractFilterBody data, AbstractServiceFilter filter) throws Exception {
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