/* 
 * ===========================================================================
 * File Name Address.java
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
 * $Log: Address.java,v $
 * ===========================================================================
 */
package com.property.buyer.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "ADDRESS")
public class Address implements java.io.Serializable {

	private static final long serialVersionUID = -7167209048907624661L;
	private String city;
	private Date createDate;
	private Long id;
	private Double latitude;
	private Double longitude;
	private State state;
	private String streetAddress;
	private String zip;
	private String country;

	public Address() {
	}

	@Column(name = "CITY")
	public String getCity() {
		return this.city;
	}

	/**
	 * @return createDate of Date Type
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATE_DATE", length = 19)
	public Date getCreateDate() {
		return this.createDate;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "ID", unique = true, nullable = false)
	public Long getId() {
		return this.id;
	}

	@Column(name = "LATITUDE", precision = 22, scale = 0)
	public Double getLatitude() {
		return this.latitude;
	}

	@Column(name = "LONGITUDE", precision = 22, scale = 0)
	public Double getLongitude() {
		return this.longitude;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "STATE")
	public State getState() {
		return this.state;
	}

	/**
	 * @return streetAddressOne of String Type
	 */
	@Column(name = "STREET_ADDRESS")
	public String getStreetAddress() {
		return streetAddress;
	}

	@Column(name = "ZIP")
	public String getZip() {
		return this.zip;
	}

	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @param Date
	 *            type set into createDate
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public void setState(State state) {
		this.state = state;
	}

	/**
	 * @param String
	 *            type set into streetAddressOne
	 */
	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	@Column(name = "COUNTRY")
	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

}
