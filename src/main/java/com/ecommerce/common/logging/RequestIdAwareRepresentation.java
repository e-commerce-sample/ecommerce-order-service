package com.ecommerce.common.logging;

import org.slf4j.MDC;

import static com.ecommerce.common.logging.RequestIdMdcFilter.REQUEST_ID;

public abstract class RequestIdAwareRepresentation {
    private final String requestId;

    public RequestIdAwareRepresentation() {
        this.requestId = MDC.get(REQUEST_ID);
    }

    public String getRequestId() {
        return requestId;
    }
}
