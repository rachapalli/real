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

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.property.buyer.utility.ApplicationConstants;
import com.property.buyer.utility.ConstantEnum;

/**
 * This controller have all the log in related handler methods.
 * 
 * @author umamaheswarar - Chetu
 * @version 1.0 - Oct 7, 2017
 */
@Controller
public class LoginController {
	private static final Logger LOG = Logger.getLogger(BuyersController.class);
	@Autowired
	private MessageSource messageSource;

	@RequestMapping(value = "/login**")
	public ModelAndView login(@RequestParam(value = "authfailed", required = false) final String authfailed,
			@RequestParam(value = "logout", required = false) final String logout,
			//@RequestParam(value = "sessionexpired", required = false) final String sessionexpired,
			@RequestParam(value = "alreadylogin", required = false) final String alreadylogin,
			@RequestParam(value = "denied", required = false) final String denied, final HttpServletRequest request) {
		LOG.info("Inside login handler method");
		final ModelAndView modelView = new ModelAndView("login");
		if (authfailed != null) {
			modelView.addObject("authfailed", getErrorMessage(request, "SPRING_SECURITY_LAST_EXCEPTION"));
		}
		if (logout != null) {
			modelView.addObject(ApplicationConstants.MESSAGE,
					messageSource.getMessage("login.error.logout", null, null));
		}
		/*if (sessionexpired != null) {
			modelView.addObject(ApplicationConstants.MESSAGE,
					messageSource.getMessage("login.error.sessionexpired", null, null));
		}*/
		if (alreadylogin != null) {
			modelView.addObject(ApplicationConstants.MESSAGE,
					messageSource.getMessage("login.error.alreadylogin", null, null));
		}
		if (denied != null) {
			modelView.addObject(ApplicationConstants.MESSAGE,
					messageSource.getMessage("login.error.denied", null, null));
		}
		LOG.info("End login handler method");
		return modelView;
	}

	@RequestMapping(value = "/")
	public String dashboard(final HttpServletRequest request, final ModelMap model) {
		LOG.info("Inside dashboard handler method");
		String form = ConstantEnum.WELCOME.getValue();
		LOG.info("End dashboard handler method");
		return form;
	}

	private String getErrorMessage(final HttpServletRequest request, final String key) {
		LOG.info("Inside getErrorMessage handler method");
		final Exception exception = (Exception) request.getSession().getAttribute(key);
		String error = "";
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
		} else if (exception != null){
			error = exception.getMessage() != null ? exception.getMessage()
					: messageSource.getMessage("login.error.badcreds", null, null);
		}
		LOG.info("End getErrorMessage handler method");
		return error;
	}

	@RequestMapping("403")
	public String get403() {
		return "redirect:login?denied";
	}
}
