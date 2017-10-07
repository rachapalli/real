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

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.ui.ModelMap;

import com.property.buyer.ajax.AjaxRequest;
import com.property.buyer.ajax.AjaxResponse;
import com.property.buyer.dao.BuyerDao;
import com.property.buyer.dao.IBaseDao;
import com.property.buyer.model.Property;
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
	@Autowired
	private IBaseDao<Property> propertyBaseDao;

	@Override
	public boolean fetchExistingBuyers(String userName, String type) {
		final Users user = (Users) usersBaseDao.getCurrentSession().createCriteria(Users.class)
				.add(Restrictions.eq("username", userName)).add(Restrictions.eq("type", type)).uniqueResult();
		if (user != null) {
			return true;
		}
		return false;
	}

	@Override
	public List<Property> searchProperty(ModelMap map, AjaxRequest ajaxRequest,
			AjaxResponse ajaxResponse) {
		Criteria criteria = propertyBaseDao.getCurrentSession().createCriteria(
				Property.class, "property");
		criteria.createAlias("property.address", "address");
		criteria.createAlias("address.state", "state");
		criteria.createAlias("address.city", "city");
		criteria.add(Restrictions.or(
				Restrictions.ilike("property.propertyName", ajaxRequest.getPropertyName(),MatchMode.ANYWHERE),
				Restrictions.ilike("property.description", ajaxRequest.getPropertyName(), MatchMode.ANYWHERE),
				Restrictions.ilike("state.state", ajaxRequest.getPropertyName(), MatchMode.ANYWHERE),
				Restrictions.ilike("city.cityName", ajaxRequest.getPropertyName(), MatchMode.ANYWHERE)));
		@SuppressWarnings("unchecked")
		final List<Property> properties = criteria.list();
		return properties;
	}

}
