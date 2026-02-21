package com.register.backend.rest.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.codec.binary.Hex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.util.concurrent.ThreadLocalRandom;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class RequestResponseFilter implements Filter {

    public static final String LOGGING_ID_PROPERTY = "LOGGING.ID";

    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @Value("${app.instanceId}")
    private String instanceId;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest requestToEvaluate = (HttpServletRequest) request;
        byte[] tempRandom = new byte[5];
        ThreadLocalRandom.current().nextBytes(tempRandom);

        String id = instanceId + "-" + Hex.encodeHexString(tempRandom);
        MDC.put(LOGGING_ID_PROPERTY, id);
        logger.info("Request to {} with request-ID: {}", requestToEvaluate.getRequestURI(), id);
        chain.doFilter(request, response);
        logger.info("Response with http-Code {} to Request with request-ID: {}", ((HttpServletResponse)response).getStatus(), id);
    }
}
