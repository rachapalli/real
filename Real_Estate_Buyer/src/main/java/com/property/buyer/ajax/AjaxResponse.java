/* 
 * ===========================================================================
 * File Name AjaxResponse.java
 * 
 * Created on 15-Jun-2017
 *
 * This code contains copyright information which is the proprietary property
 * of GirasolPayments-Admin. No part of this code may be reproduced, stored or transmitted
 * in any form without the prior written permission of GirasolPayments-Admin.
 *
 * Copyright (C) GirasolPayments-Admin. 2017
 * All rights reserved.
 *
 * Modification history:
 * $Log: AjaxResponse.java,v $
 * ===========================================================================
 */

package com.property.buyer.ajax;

/**
 * This class is used to design the response to be sent as ajax response.
 * 
 * @author prabinar
 * @version 1.1 - Jun 21, 2017
 */
public class AjaxResponse {
	private Boolean status = null;
	private Object data = null;

	/**
	 *   
	 * @description 
	 */
	public AjaxResponse() {
		super();
	}

	/**
	 * @return data of Object Type
	 */
	public Object getData() {
		return data;
	}

	/**
	 * @param Object
	 *            type set into data
	 */
	public void setData(Object data) {
		this.data = data;
	}

	/**
	 * @return status of Boolean Type
	 */
	public Boolean getStatus() {
		return status;
	}

	/**
	 * @param Boolean
	 *            type set into status
	 */
	public void setStatus(Boolean status) {
		this.status = status;
	}
}