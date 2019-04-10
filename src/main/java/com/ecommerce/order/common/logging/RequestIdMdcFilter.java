package com.ecommerce.order.common.logging;


import org.slf4j.MDC;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.ecommerce.order.common.utils.UuidGenerator.newUuid;
import static com.google.common.base.Strings.isNullOrEmpty;
import static org.springframework.core.Ordered.HIGHEST_PRECEDENCE;

/**
 * Add request id to each request for logback logging.
 * If request contains `X-Request-Id` header then it's used as request id.
 * Otherwise a random request id is generated.
 */
@Component
@Order(HIGHEST_PRECEDENCE)
public class RequestIdMdcFilter extends OncePerRequestFilter {
    public static final String REQUEST_ID = "requestId";
    private static final String HEADER_X_REQUEST_ID = "x-request-id";


    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {
        populateMdc(request);
        try {
            filterChain.doFilter(request, response);
        } finally {
            clearMdc();
        }
    }

    private void populateMdc(HttpServletRequest request) {
        MDC.put(REQUEST_ID, requestId(request));
    }

    private String requestId(HttpServletRequest request) {
        //request id in header may come from Gateway, eg. Nginx
        String headerRequestId = request.getHeader(HEADER_X_REQUEST_ID);
        return isNullOrEmpty(headerRequestId) ? newUuid() : headerRequestId;
    }

    private void clearMdc() {
        MDC.remove(REQUEST_ID);
    }

}
