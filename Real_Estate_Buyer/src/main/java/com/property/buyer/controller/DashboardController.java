/* 
 * ===========================================================================
 * File Name DashboardController.java
 * 
 * Created on Oct 04, 2017
 *
 * This code contains copyright information which is the proprietary property
 * of Real_Estate. No part of this code may be reproduced, stored or transmitted
 * in any form without the prior written permission of Real_Estate.
 *
 * Copyright (C) Real_Estate. 2017
 * All rights reserved.
 *
 * Modification history:
 * $Log: DashboardController.java,v $
 * ===========================================================================
 */
package com.property.buyer.controller;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.property.buyer.model.Users;
import com.property.buyer.service.BuyerService;
import com.property.buyer.utility.ApplicationConstants;
import com.property.buyer.utility.Utility;

/**
 * This class is having all the handler method to load dash board after succesfull login.
 * 
 * @author Umamaheswara
 * @version 1.0 - Oct 05, 2017
 */
@Controller
@SessionAttributes(ApplicationConstants.LOGGED_USER)
@RequestMapping(value = "/real")
public class DashboardController {
	private static final Logger LOG = Logger.getLogger(BuyersController.class);
	@Autowired
	private BuyerService buyerService;

	@RequestMapping(value = "/dashboard", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView dashboardView(final HttpServletRequest request, final ModelMap model) {
		LOG.info("Inside dashboardView handler method ");
		final ModelAndView modelView = new ModelAndView("dashboard");
		Users loggedUser = null;
		try {
			final HttpSession httpSession = request.getSession(false);

			/* Get the authentication from the context holder. */
			final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			/* Checking if the user is authenticated */
			if (authentication != null && authentication.isAuthenticated()
					&& authentication.getPrincipal() instanceof User) {
				final User user = (User) authentication.getPrincipal();
				loggedUser = buyerService.getUserLoginId(user.getUsername());
				if (loggedUser != null) {
					modelView.addObject(ApplicationConstants.LOGGED_USER, loggedUser);
				}
				if (httpSession != null) {
					model.remove(ApplicationConstants.LOGGED_USER);
					httpSession.setAttribute(ApplicationConstants.LOGGED_USER, loggedUser);
				}
				/* Getting the roles/authorities assigned to the user */
				@SuppressWarnings("unchecked")
				final Collection<GrantedAuthority> authorities = (Collection<GrantedAuthority>) authentication
						.getAuthorities();
				final StringBuilder authorityInfo = new StringBuilder();
				for (GrantedAuthority authority : authorities) {
					authorityInfo.append(authority.getAuthority());
				}
				Utility.getLogger(getClass()).info("Module and Role: " + authorityInfo.toString());
			}

			if (null == loggedUser) {
				return new ModelAndView("redirect:/login");
			}
		} catch (final Exception e) {
			LOG.error("Exception in dashboardView", e);
		}
		LOG.info("Inside dashboardView handler method ");
		return modelView;
	}
}
