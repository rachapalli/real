/* 
 * ===========================================================================
 * File Name AjaxRequest.java
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
 * $Log: AjaxRequest.java,v $
 * ===========================================================================
 */
package com.property.buyer.ajax;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * This class is used to keep ajax request attribute values.
 * 
 * @author Umamaheswara
 * @version 1.0 - Oct 05, 2017
 */

public class AjaxRequest implements Serializable {

	/** long Short Description */
	private static final long serialVersionUID = -5955085522435056314L;
	private String propertyName;
	private BigDecimal lowPrice;
	private BigDecimal highPrice;
	private String city;
	private String state;
	private String street;
	private String email;
	private String message;
	private long id;
	private boolean highTolow;

	/**
	 * 
	 * @description
	 */
	public AjaxRequest() {
		super();
	}

	public String getPropertyName() {
		return propertyName;
	}

	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public BigDecimal getLowPrice() {
		return lowPrice;
	}

	public void setLowPrice(BigDecimal lowPrice) {
		this.lowPrice = lowPrice;
	}

	public BigDecimal getHighPrice() {
		return highPrice;
	}

	public void setHighPrice(BigDecimal highPrice) {
		this.highPrice = highPrice;
	}

	public boolean isHighTolow() {
		return highTolow;
	}

	public void setHighTolow(boolean highTolow) {
		this.highTolow = highTolow;
	}

}