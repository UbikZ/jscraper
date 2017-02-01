package com.ubikz.scraper.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;

@RestController
public class TestController extends Throwable {
    private static final String template = "Hello, %s!";

    @RequestMapping(value = "/test", method = RequestMethod.GET, produces = "application/json")
    public String test() {
        Jedis jedis = new Jedis();
        return template;
    }
}