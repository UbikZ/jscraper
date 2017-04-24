package com.ubikz.scraper.app.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;

@Controller
public class IndexController extends AppController {
    @GetMapping("/")
    public String index(Model model, HttpServletRequest request) throws JsonProcessingException {
        return this.render(model, request.getQueryString());
    }

    @GetMapping("/feed-items")
    public String feedItems(Model model, HttpServletRequest request) throws JsonProcessingException {
        return this.render(model, request.getQueryString());
    }
}