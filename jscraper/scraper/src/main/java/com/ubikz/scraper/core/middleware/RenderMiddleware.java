package com.ubikz.scraper.core.middleware;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.script.ScriptTemplateConfigurer;
import org.springframework.web.servlet.view.script.ScriptTemplateViewResolver;

@Configuration
public class RenderMiddleware {
    private static final String[] scripts = {"static/polyfill.js", "static/server.js"};

    @Bean
    public ViewResolver reactViewResolver() {
        return new ScriptTemplateViewResolver("/static/", ".html");
    }

    @Bean
    public ScriptTemplateConfigurer reactConfigurer() {
        ScriptTemplateConfigurer configurer = new ScriptTemplateConfigurer();
        configurer.setEngineName("nashorn");
        configurer.setScripts(scripts);
        configurer.setRenderFunction("render");
        configurer.setSharedEngine(false);

        return configurer;
    }
}