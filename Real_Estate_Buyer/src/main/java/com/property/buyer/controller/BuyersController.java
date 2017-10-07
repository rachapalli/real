/* 
 * ===========================================================================
 * File Name BuyersController.java
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
 * $Log: BuyersController.java,v $
 * ===========================================================================
 */

package com.property.buyer.controller;

import java.io.IOException;
import java.text.ParseException;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.property.buyer.dto.RegisterBuyersDTO;
import com.property.buyer.service.BuyerService;
import com.property.buyer.utility.CashBakConstantEnum;

@Controller
@RequestMapping(value="/buyer")
public class BuyersController {

	private static final Logger LOG = Logger.getLogger(BuyersController.class);

	@Autowired
	private BuyerService buyerService;
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ModelAndView register(final HttpServletRequest request, final ModelMap model) {
		final ModelAndView modelAndView = new ModelAndView("register");
		modelAndView.addObject("registerBuyer", new RegisterBuyersDTO());
		return modelAndView;
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String saveEmployeeData(final ModelMap model, @ModelAttribute RegisterBuyersDTO registerBuyers)
			throws ParseException, IOException {
		String form = "";
		if (buyerService.saveBuyer(registerBuyers)){
			form = CashBakConstantEnum.LOGIN.getValue();
		}	

		return form;
	}
	
}
