/* 
 * ===========================================================================
 * File Name LoginController.java
 * 
 * Created on Oct 7, 2017
 *
 * This code contains copyright information which is the proprietary property
 * of Real_Estate_Buyer. No part of this code may be reproduced, stored or transmitted
 * in any form without the prior written permission of Real_Estate_Buyer.
 *
 * Copyright (C) Real_Estate_Buyer. 2017
 * All rights reserved.
 *
 * Modification history:
 * $Log: LoginController.java,v $
 * ===========================================================================
 */

package com.property.buyer.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.property.buyer.utility.ApplicationConstants;
import com.property.buyer.utility.CashBakConstantEnum;

/**
 * Class Information
 * 
 * @author umamaheswarar - Chetu
 * @version 1.0 - Oct 7, 2017
 */
public class LoginController {
	@Autowired
	private MessageSource messageSource;

	@RequestMapping(value = "/login**")
	public ModelAndView login(@RequestParam(value = "authfailed", required = false) final String authfailed,
			@RequestParam(value = "logout", required = false) final String logout,
			@RequestParam(value = "sessionexpired", required = false) final String sessionexpired,
			@RequestParam(value = "alreadylogin", required = false) final String alreadylogin,
			@RequestParam(value = "denied", required = false) final String denied, final HttpServletRequest request) {
		final ModelAndView modelView = new ModelAndView("login");
		if (authfailed != null) {
			modelView.addObject("authfailed", getErrorMessage(request, "SPRING_SECURITY_LAST_EXCEPTION"));
		}
		if (logout != null) {
			modelView.addObject(ApplicationConstants.MESSAGE,
					messageSource.getMessage("login.error.logout", null, null));
		}
		if (sessionexpired != null) {
			modelView.addObject(ApplicationConstants.MESSAGE,
					messageSource.getMessage("login.error.sessionexpired", null, null));
		}
		if (alreadylogin != null) {
			modelView.addObject(ApplicationConstants.MESSAGE,
					messageSource.getMessage("login.error.alreadylogin", null, null));
		}
		if (denied != null) {
			modelView.addObject(ApplicationConstants.MESSAGE,
					messageSource.getMessage("login.error.denied", null, null));
		}
		return modelView;
	}

	@RequestMapping(value = "/")
	public String dashboard(final HttpServletRequest request, final ModelMap model) {
		String form = CashBakConstantEnum.LOGIN.getValue();
		form = CashBakConstantEnum.WELCOME.getValue();
		return form;
	}

	private String getErrorMessage(final HttpServletRequest request, final String key) {
		final Exception exception = (Exception) request.getSession().getAttribute(key);
		String error;
		if (exception instanceof BadCredentialsException) {
			error = messageSource.getMessage("login.error.badcreds", null, null);
		} else if (exception instanceof LockedException) {
			error = exception.getMessage();
		} else if (exception instanceof DisabledException) {
			error = exception.getMessage();
		} else if (exception instanceof AccountExpiredException) {
			error = exception.getMessage();
		} else if (exception instanceof CredentialsExpiredException) {
			error = exception.getMessage();
		} else {
			error = exception.getMessage() != null ? exception.getMessage()
					: messageSource.getMessage("login.error.badcreds", null, null);
		}
		return error;
	}

	@RequestMapping("403")
	public String get403() {
		return "redirect:login?denied";
	}
}
