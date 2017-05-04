package com.ubikz.scraper.app.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Controller
public class IndexController {
    @GetMapping("/{path:(?!.*.js|.*.css|.*.jpg|api).*$}")
    public String index(Model model, HttpServletRequest request) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();

        Map<String, Object> req = new HashMap<String, Object>() {{
            put("location", request.getQueryString());
        }};

        Map<String, Object> initialState = new HashMap<String, Object>() {{
            put("feedItems", new HashMap<String, Object>() {{
                put("items", new ArrayList<>());
                put("total", 0);
                put("offset", 0);
                put("limit", 20);
                put("tags", new ArrayList<>());
                put("startDate", null);
                put("endDate", null);
            }});
        }};

        model.addAttribute("req", mapper.writeValueAsString(req));
        model.addAttribute("initialState", mapper.writeValueAsString(initialState));

        return "index";
    }
}