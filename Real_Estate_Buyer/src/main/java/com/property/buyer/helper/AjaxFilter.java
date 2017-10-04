/* 
 * ===========================================================================
 * File Name AjaxFilter.java
 * 
 * Created on 24-Nov-2016
 *
 * This code contains copyright information which is the proprietary property
 * of CashBak-FI. No part of this code may be reproduced, stored or transmitted
 * in any form without the prior written permission of CashBak-FI.
 *
 * Copyright (C) CashBak-FI. 2016
 * All rights reserved.
 *
 * Modification history:
 * $Log: AjaxFilter.java,v $
 * ===========================================================================
 */
package com.property.buyer.helper;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * This class is used to filter ajax request.
 * 
 * @author mukeshs 
 * @version 1.0 - 24-Nov-2016
 */
public class AjaxFilter extends OncePerRequestFilter {

    private static final Logger LOG = Logger.getLogger(AjaxFilter.class);

    @SuppressWarnings("unused")
    private String invalidSessionUrl;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {


        boolean sessionExpired = false;

        HttpServletRequest httpRequest = null;
        if (request instanceof HttpServletRequest) {
            httpRequest = (HttpServletRequest) request;
            sessionExpired = hasSessionExpired(httpRequest);
        }
        if (isAjax(request) && sessionExpired) {
            performRedirect(httpRequest, response);
        } else {
            chain.doFilter(request, response);
        }

    }

    /**
     * This method is used to check has Session Expired.
     * 
     * @author uttamk
     * @date 12-Jul-2017
     * @return <code>boolean</code>
     * @param httpRequest
     */
    private boolean hasSessionExpired(HttpServletRequest httpRequest) {
        if (httpRequest.getRequestedSessionId() != null
                && !httpRequest.isRequestedSessionIdValid()) {
            if (LOG.isDebugEnabled()) {
                LOG.debug("Requested session ID "
                        + httpRequest.getRequestedSessionId() + " is invalid.");
            }

            return true;
        }
        return false;
    }

    /**
     * This method is used to perform Redirect.
     * 
     * @author uttamk
     * @date 12-Jul-2017
     * @return <code>void</code>
     * @param request
     * @param response
     * @throws IOException
     */
    private void performRedirect(HttpServletRequest request,
            HttpServletResponse response) throws IOException {

        response.setStatus(HttpServletResponse.SC_REQUEST_TIMEOUT);
    }


    public void setInvalidSessionUrl(String invalidSessionUrl) {
        this.invalidSessionUrl = invalidSessionUrl;
    }

    /**
     * This method is used to check request type is Ajax request or not.
     * 
     * @author uttamk
     * @date 12-Jul-2017
     * @return <code>boolean</code>
     * @param request
     * @return
     */
    private boolean isAjax(HttpServletRequest request) {
        return "XMLHttpRequest".equals(request.getHeader("X-Requested-With"));
    }
}