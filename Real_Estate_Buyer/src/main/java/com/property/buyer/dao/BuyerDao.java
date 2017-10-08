/* 
 * ===========================================================================
 * File Name IBuyerDao.java
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
 * $Log: IBuyerDao.java,v $
 * ===========================================================================
 */
package com.property.buyer.dao;

import java.util.List;

import com.property.buyer.ajax.AjaxRequest;
import com.property.buyer.ajax.AjaxResponse;
import com.property.buyer.model.Property;

/**
 * This interface have all the buyer related abstract methods which will be used
 * to implement.
 * 
 * @author umamaheswarar - Chetu
 * @version 1.0 - Oct 7, 2017
 */

public interface BuyerDao {

	/**
	 * This dao method is used to fetch existing users and returns true/false.
	 * 
	 * @author umamaheswarar
	 * @date Oct 7, 2017
	 * @return <code>boolean</code>
	 * @param userName
	 * @param type
	 */
	boolean fetchExistingBuyers(final String userName, final String type);

	/**
	 * This dao method is used to search property details.
	 * 
	 * @author umamaheswarar
	 * @date Oct 7, 2017
	 * @param ajaxRequest
	 * @param ajaxResponse
	 * @return List<Property>
	 */
	List<Property> searchProperty(AjaxRequest ajaxRequest, AjaxResponse ajaxResponse);

	/**
	 * This dao method is used to filter properties.
	 * 
	 * @author umamaheswarar
	 * @date Oct 7, 2017
	 * @param ajaxRequest
	 * @param ajaxResponse
	 * @return List<Property>
	 */
	List<Property> filterProperty(AjaxRequest ajaxRequest, AjaxResponse ajaxResponse);
}
