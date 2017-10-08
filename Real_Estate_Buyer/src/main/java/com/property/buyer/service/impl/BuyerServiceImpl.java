/* 
 * ===========================================================================
 * File Name BuyerServiceImpl.java
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
 * $Log: BuyerServiceImpl.java,v $
 * ===========================================================================
 */
package com.property.buyer.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.property.buyer.ajax.AjaxRequest;
import com.property.buyer.ajax.AjaxResponse;
import com.property.buyer.dao.BuyerDao;
import com.property.buyer.dao.IBaseDao;
import com.property.buyer.dto.PropertyDTO;
import com.property.buyer.dto.RegisterBuyersDTO;
import com.property.buyer.model.Address;
import com.property.buyer.model.Property;
import com.property.buyer.model.Users;
import com.property.buyer.service.BuyerService;
import com.property.buyer.utility.ApplicationConstants;
import com.property.buyer.utility.EncryptionUtility;
import com.property.buyer.utility.SendMail;
import com.property.buyer.utility.Utility;

/**
 * This service implementation class have all the buyer related service methods.
 * 
 * @author umamaheswarar - Chetu
 * @version 1.0 - Oct 7, 2017
 */
@Service
public class BuyerServiceImpl implements BuyerService {

	@Autowired
	private IBaseDao<Users> buyersBaseDao;
	@Autowired
	private IBaseDao<Property> propertyBaseDao;
	@Autowired
	private BuyerDao buyerDao;
	@Autowired
	private SendMail sender;

	@Transactional
	public boolean saveBuyer(final RegisterBuyersDTO registerBuyersDTO) {
		if (buyerDao.fetchExistingBuyers(registerBuyersDTO.getEmail(), ApplicationConstants.BUYUER)) {
			return false;
		}
		final Users buyers = new Users();
		buyers.setCreateDate(new Date());
		buyers.setAuthorities(ApplicationConstants.USER);
		buyers.setFirstName(registerBuyersDTO.getFirstName());
		buyers.setLastName(registerBuyersDTO.getLastName());
		buyers.setUsername(registerBuyersDTO.getEmail());
		buyers.setCellPhone(registerBuyersDTO.getPhone());
		buyers.setType(ApplicationConstants.BUYUER);
		buyers.setPassword(EncryptionUtility.encrypt(registerBuyersDTO.getPassword()));
		buyersBaseDao.save(buyers);

		return true;
	}

	@Override
	@Transactional
	public Users getUserLoginId(String username) {
		try {
			/* find column for login */
			final Users user = buyersBaseDao.findUniqueByColumn(Users.class, "username", username);
			if (user != null) {
				return user;
			}
		} catch (final Exception e) {
			Utility.getLogger(getClass()).error("Exception occurs at Login", e);
		}
		return null;
	}

	@Override
	@Transactional
	public void searchProperty(AjaxRequest ajaxRequest, AjaxResponse ajaxResponse) {
		final List<Property> properties = buyerDao.searchProperty(ajaxRequest, ajaxResponse);
		if (properties != null && !properties.isEmpty()) {
			final List<PropertyDTO> propertyDTOs = new ArrayList<>();
			properties.forEach(property -> {
				final Address address = property.getAddress();
				final Users users = buyersBaseDao.findById(Users.class, property.getId());
				PropertyDTO dto = new PropertyDTO();
				dto.setDescription(property.getDescription());
				dto.setNoOfBathRooms(property.getNoOfBathRooms());
				dto.setNoOfBedRooms(property.getNoOfBedRooms());
				dto.setPrice(property.getPrice());
				dto.setPropertyName(property.getPropertyName());
				dto.setPostedBy(property.getPostedBy());
				dto.setPlotArea(property.getPlotArea());
				dto.setCity(address.getCity().getCityName());
				dto.setCountry(address.getCountry());
				dto.setState(address.getState().getState());
				dto.setId(property.getId());
				dto.setEmail(users.getUsername());
				propertyDTOs.add(dto);
			});

			ajaxResponse.setData(propertyDTOs);
		}
	}

	@Override
	@Transactional
	public boolean contactSeller(AjaxRequest ajaxRequest) {
		try {
			sender.send("real.lean.app@gmail.com", ajaxRequest.getEmail(), "Property Buyer Mailed",
					ajaxRequest.getMessage());
			return true;
		} catch (MessagingException e) {
			// TODO EXception Handling
			Utility.getLogger(getClass()).error("Exception occurs", e);
		}
		return true;
	}

	@Override
	@Transactional
	public void fetchProperty(AjaxRequest ajaxRequest, AjaxResponse ajaxResponse) {
		final Property property = propertyBaseDao.findById(Property.class, ajaxRequest.getId());
		if (property != null) {
			final List<PropertyDTO> propertyDTOs = new ArrayList<>();
			final Address address = property.getAddress();
			final Users users = buyersBaseDao.findById(Users.class, property.getId());
			PropertyDTO dto = new PropertyDTO();
			dto.setDescription(property.getDescription());
			dto.setNoOfBathRooms(property.getNoOfBathRooms());
			dto.setNoOfBedRooms(property.getNoOfBedRooms());
			dto.setPrice(property.getPrice());
			dto.setPropertyName(property.getPropertyName());
			dto.setPostedBy(property.getPostedBy());
			dto.setPlotArea(property.getPlotArea());
			dto.setCity(address.getCity().getCityName());
			dto.setCountry(address.getCountry());
			dto.setState(address.getState().getState());
			dto.setId(property.getId());
			dto.setEmail(users.getUsername());
			propertyDTOs.add(dto);
			ajaxResponse.setData(propertyDTOs);
		}
	}

	@Override
	@Transactional
	public void filterProperty(AjaxRequest ajaxRequest, AjaxResponse ajaxResponse) {
		final List<Property> properties = buyerDao.filterProperty(ajaxRequest, ajaxResponse);
		if (properties != null && !properties.isEmpty()) {
			final List<PropertyDTO> propertyDTOs = new ArrayList<>();
			properties.forEach(property -> {
				final Address address = property.getAddress();
				final Users users = buyersBaseDao.findById(Users.class, property.getId());
				PropertyDTO dto = new PropertyDTO();
				dto.setDescription(property.getDescription());
				dto.setNoOfBathRooms(property.getNoOfBathRooms());
				dto.setNoOfBedRooms(property.getNoOfBedRooms());
				dto.setPrice(property.getPrice());
				dto.setPropertyName(property.getPropertyName());
				dto.setPostedBy(property.getPostedBy());
				dto.setPlotArea(property.getPlotArea());
				dto.setCity(address.getCity().getCityName());
				dto.setCountry(address.getCountry());
				dto.setState(address.getState().getState());
				dto.setId(property.getId());
				dto.setEmail(users.getUsername());
				propertyDTOs.add(dto);
			});

			ajaxResponse.setData(propertyDTOs);
		}
	}
}
