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

import org.springframework.stereotype.Service;

import com.property.buyer.model.Buyers;
import com.property.buyer.service.UserService;


/**
 * This Service is used for user operations.
 *
 * @author satyamg - Chetu
 * @version 1.0 - June 15, 2017
 */
@Service
public class UserServiceImpl implements UserService {

	/* (non-Javadoc)
	 * @see com.property.buyer.service.UserService#getUserLoginId(java.lang.String)
	 */
	public Buyers getUserLoginId(String username) {
		// TODO Auto-generated method stub
		return null;
	}
}
