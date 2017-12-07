package org.ubikz.jscraper.api.entity.model.request.impl;

import org.ubikz.jscraper.api.entity.model.request.BaseEntityRequest;

public class TagEntityRequest extends BaseEntityRequest {
    public TagEntityRequest(String label) {
        this.label = label;
    }
}
