package org.ubikz.jscraper.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping(value = "/error")
public class SimpleErrorController implements ErrorController {
    private static final String KEY_TRACE = "trace";
    private final ErrorAttributes errorAttributes;

    @Autowired
    public SimpleErrorController(ErrorAttributes errorAttributes) {
        Assert.notNull(errorAttributes, "ErrorAttributes must not be null");
        this.errorAttributes = errorAttributes;
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }

    @RequestMapping
    public Map<String, Object> error(HttpServletRequest aRequest) {
        Map<String, Object> body = getErrorAttributes(aRequest, getTraceParameter(aRequest));

        String trace = (String) body.get(KEY_TRACE);
        if (trace != null) {
            String[] lines = trace.split("\n\t");
            body.put(KEY_TRACE, lines);
        }

        return body;
    }

    private boolean getTraceParameter(HttpServletRequest request) {
        String parameter = request.getParameter(KEY_TRACE);

        if (parameter == null) {
            return false;
        }

        return !"false".equals(parameter.toLowerCase());
    }

    private Map<String, Object> getErrorAttributes(HttpServletRequest aRequest, boolean includeStackTrace) {
        RequestAttributes requestAttributes = new ServletRequestAttributes(aRequest);

        return errorAttributes.getErrorAttributes(requestAttributes, includeStackTrace);
    }
}