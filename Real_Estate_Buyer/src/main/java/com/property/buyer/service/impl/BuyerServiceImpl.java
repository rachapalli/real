/* 
 * ===========================================================================
 * File Name UserServiceImpl.java
 * 
 * Created on Jun 19, 2017
 *
 * This code contains copyright information which is the proprietary property
 * of GirasolPayments-Admin. No part of this code may be reproduced, stored or transmitted
 * in any form without the prior written permission of GirasolPayments-Admin.
 *
 * Copyright (C) GirasolPayments-Admin. 2017
 * All rights reserved.
 *
 * Modification history:
 * $Log: UserServiceImpl.java,v $
 * ===========================================================================
 */
package com.property.buyer.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.property.buyer.dao.IBaseDao;
import com.property.buyer.dto.RegisterBuyersDTO;
import com.property.buyer.model.Buyers;
import com.property.buyer.service.BuyerService;
import com.property.buyer.utility.Utility;

@Service
public class BuyerServiceImpl implements BuyerService {

	@Autowired
	private IBaseDao<Buyers> buyersBaseDao;

	@Override
	@Transactional
	public Buyers getUserLoginId(String username) {
		try {
			/* find column for login */
			String column = Utility.getColumnNameByValue(username);
			final Buyers user = buyersBaseDao.findUniqueByColumn(Buyers.class, column, username);
			if (user != null) {
				return user;
			}
		} catch (final Exception e) {
			Utility.getLogger(getClass()).error("Exception occurs at Login", e);
		}
		return null;
	}

	@Transactional
	public boolean saveBuyer(final RegisterBuyersDTO registerBuyersDTO) {
		// TODO check for the existing buyers
		final Buyers buyers = new Buyers();
		buyers.setCreateDate(new Date());
		buyers.setAuthorities("USER");
		buyers.setFirstName(registerBuyersDTO.getFirstName());
		buyers.setLastName(registerBuyersDTO.getLastName());
		buyers.setUsername(registerBuyersDTO.getEmail());
		// TODO encrypt password
		buyers.setPassword(registerBuyersDTO.getPassword());
		buyersBaseDao.save(buyers);

		return true;
	}
}
