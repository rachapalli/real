/* 
 * ===========================================================================
 * File Name UserService.java
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
 * $Log: UserService.java,v $
 * ===========================================================================
 */
package com.property.buyer.service;

import com.property.buyer.model.Buyers;

/**
 * This Service is used for user operations.
 *
 * @author satyamg
 * @version 1.0 - June 15, 2017
 */
public interface UserService {

	/**
	 * This method is used to get user login by user name;
	 * 
	 * @author prabinar
	 * @date 19-Jun-2017
	 * @return <code>User</code>
	 * @param username
	 */
	Buyers getUserLoginId(String username);

}
