/* 
 * ===========================================================================
 * File Name BuyerDaoImpl.java
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
 * $Log: BuyerDaoImpl.java,v $
 * ===========================================================================
 */
package com.property.buyer.dao.impl;

import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.property.buyer.dao.BuyerDao;
import com.property.buyer.dao.IBaseDao;
import com.property.buyer.model.Users;

/**
 * This dao implemented class is used to have user/buyer related methods.
 * 
 * @author umamaheswarar - Chetu
 * @version 1.0 - Oct 7, 2017
 */

@Repository
public class BuyerDaoImpl implements BuyerDao {

	@Autowired
	private IBaseDao<Users> usersBaseDao;

	@Override
	public boolean fetchExistingBuyers(String userName, String type) {
		final Users user = (Users) usersBaseDao.getCurrentSession().createCriteria(Users.class).add(Restrictions.eq("username", userName))
				.add(Restrictions.eq("type", type)).uniqueResult();
		if (user != null) {
			return true;
		}
		return false;
	}

}
