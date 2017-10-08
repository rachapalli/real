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

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.property.buyer.ajax.AjaxRequest;
import com.property.buyer.ajax.AjaxResponse;
import com.property.buyer.dto.RegisterBuyersDTO;
import com.property.buyer.service.BuyerService;
import com.property.buyer.utility.ConstantEnum;

/**
 * This class is having all the handler methods to tackle buyer services.
 * 
 * @author Umamaheswara
 * @version 1.0 - Oct 05, 2017
 */
@Controller
@RequestMapping(value = "/buyer")
public class BuyersController {

	private static final Logger LOG = Logger.getLogger(BuyersController.class);

	@Autowired
	private BuyerService buyerService;

	@RequestMapping(value = "/register", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView register(final HttpServletRequest request, final ModelMap model) {
		LOG.info("Inside register handler method . . ");
		final ModelAndView modelAndView = new ModelAndView("register");
		modelAndView.addObject("registerBuyer", new RegisterBuyersDTO());
		LOG.info("End register handler method . . ");
		return modelAndView;
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ModelAndView saveEmployeeData(final ModelMap model, @ModelAttribute RegisterBuyersDTO registerBuyers) {
		LOG.info("Inside saveEmployeeData handler method . . ");
		ModelAndView modelAndView = new ModelAndView("register");
		if (buyerService.saveBuyer(registerBuyers)) {
			modelAndView = new ModelAndView("index");
		} else {
			model.addAttribute("registerBuyer", registerBuyers);
		}
		LOG.info("End saveEmployeeData handler method . . ");
		return modelAndView;
	}

	@RequestMapping(value = "/property/search", method = RequestMethod.POST, consumes = {
			"application/json" }, produces = { "application/json" })
	public @ResponseBody AjaxResponse searchProperty(final HttpServletRequest request, final ModelMap model,
			@RequestBody final AjaxRequest ajaxRequest) {
		LOG.info("Inside searchProperty handler method . . ");
		final AjaxResponse ajaxResponse = new AjaxResponse();
		buyerService.searchProperty(ajaxRequest, ajaxResponse);
		LOG.info("End searchProperty handler method . . ");
		return ajaxResponse;
	}

	@RequestMapping(value = "/property/fetch", method = RequestMethod.POST, consumes = {
			"application/json" }, produces = { "application/json" })
	public @ResponseBody AjaxResponse fetchProperty(final HttpServletRequest request, final ModelMap model,
			@RequestBody final AjaxRequest ajaxRequest) {
		LOG.info("Inside fetchProperty handler method . . ");
		final AjaxResponse ajaxResponse = new AjaxResponse();
		buyerService.fetchProperty(ajaxRequest, ajaxResponse);
		LOG.info("End fetchProperty handler method . . ");
		return ajaxResponse;
	}

	@RequestMapping(value = "/contact/seller")
	public @ResponseBody String contactSeller(final HttpServletRequest request, final ModelMap model,
			@RequestBody final AjaxRequest ajaxRequest) {
		LOG.info("Inside contactSeller handler method . . ");
		if (buyerService.contactSeller(ajaxRequest)) {
			return "SUCCESS";
		}
		LOG.info("End contactSeller handler method . . ");
		return "FAILURE";
	}

	@RequestMapping(value = "/property/filter", method = RequestMethod.POST, consumes = {
			"application/json" }, produces = { "application/json" })
	public @ResponseBody AjaxResponse filterPrice(final HttpServletRequest request, final ModelMap model,
			@RequestBody final AjaxRequest ajaxRequest) {
		LOG.info("Inside filterPrice handler method . . ");
		final AjaxResponse ajaxResponse = new AjaxResponse();
		buyerService.filterProperty(ajaxRequest, ajaxResponse);
		LOG.info("End filterPrice handler method . . ");
		return ajaxResponse;
	}

}
