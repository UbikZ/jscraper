package com.ubikz.scraper.context;

import com.ubikz.scraper.service.message.HeaderMessage;

abstract class AbstractContext {
    final public HeaderMessage getHeaderMessage() {
        return new HeaderMessage();
    }
}