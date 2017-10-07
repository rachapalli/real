
/* 
 * ===========================================================================
 * File Name City.java
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
 * $Log: City.java,v $
 * ===========================================================================
 */
package com.property.buyer.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Table(name = "CITY")
@Entity
public class City implements Serializable {

	private static final long serialVersionUID = -2684086630051332327L;
	private long id;
	private String cityName;
	private State state;

	@Id
	@Column(name = "ID", nullable = false, unique = true)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Column(name = "CITY_NAME", nullable = false, length = 255)
	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "STATE")
	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

}
