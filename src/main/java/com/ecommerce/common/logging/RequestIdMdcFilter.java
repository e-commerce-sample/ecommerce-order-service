package com.ecommerce.common.logging;


import org.slf4j.MDC;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.ecommerce.common.utils.UuidGenerator.newUuid;
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
    static final String REQUEST_ID = "requestId";
    private static final String CLIENT_IP = "clientIp";
    private static final String HEADER_X_REQUEST_ID = "x-request-id";
    private static final String HEADER_X_REAL_IP = "x-real-ip";


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
        MDC.put(CLIENT_IP, clientIp(request));
    }

    private String clientIp(HttpServletRequest request) {
        //client ip in header may come from Gateway, eg. Nginx
        String headerClientIp = request.getHeader(HEADER_X_REAL_IP);
        return isNullOrEmpty(headerClientIp) ? request.getRemoteAddr() : headerClientIp;
    }


    private String requestId(HttpServletRequest request) {
        //request id in header may come from Gateway, eg. Nginx
        String headerRequestId = request.getHeader(HEADER_X_REQUEST_ID);
        return isNullOrEmpty(headerRequestId) ? newUuid() : headerRequestId;
    }

    private void clearMdc() {
        MDC.remove(REQUEST_ID);
        MDC.remove(CLIENT_IP);
    }
}
