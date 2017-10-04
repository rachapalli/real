/* 
 * ===========================================================================
 * File Name TwoFactorAuthenticationFilter.java
 * 
 * Created on Apr 9, 2015
 *
 * This code contains copyright information which is the proprietary property
 * of Cognops. No part of this code may be reproduced, stored or transmitted
 * in any form without the prior written permission of Cognops.
 *
 * Copyright (C) Cognops. 2015
 * All rights reserved.
 *
 * Modification history:
 * $Log: TwoFactorAuthenticationFilter.java,v $
 * ===========================================================================
 */
/**
 * A class is used to create custom UsernamePasswordAuthenticationFilter.
 * @author prateekg2 - Chetu
 * @version 1.0 - Apr 9, 2015
 */
package com.property.buyer.helper;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class CustomUsernamePasswordAuthenticationFilter extends
		UsernamePasswordAuthenticationFilter {
	
	@Override
	protected String obtainUsername(final HttpServletRequest request) {
		final String username = request.getParameter(getUsernameParameter());
		return username;
	}
}
