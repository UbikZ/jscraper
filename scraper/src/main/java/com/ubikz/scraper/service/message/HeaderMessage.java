package com.ubikz.scraper.service.message;

import java.util.Locale;

public class HeaderMessage {
    private Locale locale;
    private Boolean isDebug;

    public HeaderMessage() {
    }

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public Boolean getDebug() {
        return isDebug;
    }

    public void setDebug(Boolean debug) {
        isDebug = debug;
    }
}