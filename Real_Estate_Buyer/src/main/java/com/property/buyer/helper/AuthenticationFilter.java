/* 
 * ===========================================================================
 * File Name AuthenticationFilter.java
 * 
 * Created on Oct 5, 2017
 *
 * This code contains copyright information which is the proprietary property
 * of Real_Estate_Buyer. No part of this code may be reproduced, stored or transmitted
 * in any form without the prior written permission of Real_Estate_Buyer.
 *
 * Copyright (C) Real_Estate_Buyer. 2017
 * All rights reserved.
 *
 * Modification history:
 * $Log: AuthenticationFilter.java,v $
 * ===========================================================================
 */
package com.property.buyer.helper;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	@Override
	protected String obtainUsername(final HttpServletRequest request) {
		final String username = request.getParameter(getUsernameParameter());
		return username;
	}
}
