/* 
 * ===========================================================================
 * File Name BuyersController.java
 * 
 * Created on 04-October-2017
 *
 * This code contains copyright information which is the proprietary property
 * of CashBak-FI. No part of this code may be reproduced, stored or transmitted
 * in any form without the prior written permission of CashBak-FI.
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
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.property.buyer.utility.CashBakConstantEnum;

@Controller
public class BuyersController {

	private static final Logger LOG = Logger.getLogger(BuyersController.class);

	/**
	 * This method is used for user login.
	 * 
	 * @author mukeshs
	 * @date 01-Mar-2017
	 * @return <code>String</code>
	 * @param request
	 * @param model
	 * @param sessionExpired
	 */
	@RequestMapping(value = "/")
	public String dashboard(final HttpServletRequest request, final ModelMap model) {
		String form = CashBakConstantEnum.LOGIN.getValue();
		form = CashBakConstantEnum.REGISTER.getValue();
		return form;
	}
	
	/**
	 * This method is used for user login.
	 * 
	 * @author mukeshs
	 * @date 01-Mar-2017
	 * @return <code>String</code>
	 * @param request
	 * @param model
	 * @param sessionExpired
	 */
	@RequestMapping(value = "/register")
	public String register(final HttpServletRequest request, final ModelMap model) {
		String form = CashBakConstantEnum.LOGIN.getValue();
		form = CashBakConstantEnum.LOGIN.getValue();
		return form;
	}


	/**
	 * This method is used for user login.
	 * 
	 * @author mukeshs
	 * @date 01-Mar-2017
	 * @return <code>String</code>
	 * @param request
	 * @param model
	 * @param sessionExpired
	 */
	@RequestMapping(value = "/login**")
	public String login(final HttpServletRequest request, final ModelMap model,
			@RequestParam(value = "sessionExpired", required = false) final String sessionExpired) {
		String form = CashBakConstantEnum.LOGIN.getValue();
		if (sessionExpired != null) {
			model.addAttribute(CashBakConstantEnum.SESSIONEXPMSG.getValue(),
					CashBakConstantEnum.SESSION_EXP_MSG.getValue());
			form = CashBakConstantEnum.LOGIN.getValue();
		}
		return form;
	}

	/**
	 * This method is used for user logout.
	 * 
	 * @author mukeshs
	 * @date 01-Mar-2017
	 * @return <code>String</code>
	 * @param model
	 */
	@RequestMapping(value = "/logout")
	public String logout(final ModelMap model) {
		LOG.info("Start getForgotPassword method");
		model.addAttribute(CashBakConstantEnum.LOGOUT.getValue(),
				CashBakConstantEnum.LOGGED_OUT_SUCCESSFULLY.getValue());
		return CashBakConstantEnum.LOGIN.getValue();
	}

	/**
	 * This method is used to check user already login or not.
	 * 
	 * @author mukeshs
	 * @date 01-Mar-2017
	 * @return <code>String</code>
	 * @param model
	 */
	@RequestMapping(value = "/alreadylogin")
	public String alreadylogin(final ModelMap model) {
		model.addAttribute(CashBakConstantEnum.EMPTY_STRING.getValue(), CashBakConstantEnum.ALREADY_LOGIN.getValue());
		return CashBakConstantEnum.LOGOUT.getValue();
	}

	/**
	 * This method is used for user denied.
	 * 
	 * @author mukeshs
	 * @date 01-Mar-2017
	 * @return <code>String</code>
	 * @param model
	 */
	@RequestMapping(value = "/denied")
	public String denied(final ModelMap model) {
		model.addAttribute(CashBakConstantEnum.AUTHFAILED.getValue(),
				CashBakConstantEnum.INVALID_USER_NAME_OR_PASSWORD.getValue());
		return CashBakConstantEnum.LOGIN.getValue();
	}

}
