package org.ubikz.jscraper.database;

import java.util.HashMap;
import java.util.Map;

public class DatabaseUtil {
    public static class Request<F> {
        private Map<F, Object> request;

        public <V> Request<F> add(F key, V value) {
            if (value != null) {
                request.put(key, value);
            }

            return this;
        }

        public Request<F> setDefault() {
            request = new HashMap<>();

            return this;
        }

        public Map<F, Object> get() {
            return request;
        }

        public Request<F> set(Map<F, Object> request) {
            this.request = request;

            return this;
        }
    }
}
