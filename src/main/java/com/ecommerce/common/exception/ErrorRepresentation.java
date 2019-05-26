package com.ecommerce.common.exception;

import com.ecommerce.common.logging.RequestIdAwareRepresentation;

import java.time.Instant;
import java.util.Map;

import static com.google.common.collect.Maps.newHashMap;
import static org.apache.commons.collections4.MapUtils.isEmpty;

public class ErrorRepresentation extends RequestIdAwareRepresentation {
    private final ErrorDetail error;

    public ErrorRepresentation(AppException ex, String path) {
        ErrorEnum error = ex.getError();
        this.error = new ErrorDetail(error.getCode(), error.getStatus(), error.getMessage(), path, ex.getData());
    }

    public ErrorRepresentation(ErrorDetail error) {
        this.error = error;
    }

    public ErrorDetail getError() {
        return error;
    }

    static class ErrorDetail {
        private final String code;
        private final int status;
        private final String message;
        private final String path;
        private final Instant timestamp;
        private final Map<String, Object> data = newHashMap();

        public ErrorDetail(String code, int status, String message, String path, Map<String, Object> data) {
            this.code = code;
            this.status = status;
            this.message = message;
            this.path = path;
            this.timestamp = Instant.now();
            if (!isEmpty(data)) {
                this.data.putAll(data);
            }
        }

        public int getStatus() {
            return status;
        }
    }
}
