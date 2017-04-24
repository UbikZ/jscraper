package com.ubikz.scraper.app.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import java.util.HashMap;
import java.util.Map;

@Controller
abstract public class AppController {
    /**
     * @param model
     * @param requestUrl
     * @return
     * @throws JsonProcessingException
     */
    public String render(Model model, String requestUrl) throws JsonProcessingException {
        return this.render(model, requestUrl, "index");
    }

    /**
     * @param model
     * @param requestUrl
     * @param view
     * @return
     * @throws JsonProcessingException
     */
    public String render(Model model, String requestUrl, String view) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> req = new HashMap<String, Object>() {{
            put("location", requestUrl);
        }};

        model.addAttribute("req", mapper.writeValueAsString(req));

        return view;
    }
}