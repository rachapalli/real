/* 
 * ===========================================================================
 * File Name Property.java
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
 * $Log: Property.java,v $
 * ===========================================================================
 */

package com.property.buyer.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * This class in encapsulated all the property related details.
 * 
 * @author umamaheswarar - Chetu
 * @version 1.0 - Oct 4, 2017
 */

@Table(name = "PROPERTY")
@Entity
public class Property {

	private long id;
	private String propertyName;
	private BigDecimal price;
	private Address address;
	private Date createdDate;
	private Date updatedDate;
	private String postedBy;
	private String email;
	private String plotArea;
	private String description;
	private int noOfBedRooms;
	private int noOfBathRooms;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Column(name = "PROPERTY_NAME")
	public String getPropertyName() {
		return propertyName;
	}

	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}

	@Column(name = "PRICE")
	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	@OneToOne(fetch=FetchType.LAZY, cascade = CascadeType.ALL, mappedBy="property")
	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "CREATE_DATE")
	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "UPDATE_DATE")
	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	@Column(name = "POSTED_BY")
	public String getPostedBy() {
		return postedBy;
	}

	public void setPostedBy(String postedBy) {
		this.postedBy = postedBy;
	}

	@Column(name = "CONTACT_MAIL")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "AREA")
	public String getPlotArea() {
		return plotArea;
	}

	public void setPlotArea(String plotArea) {
		this.plotArea = plotArea;
	}

	@Column(name = "ADDITIONAL_NOTES")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "BED_ROOMS")
	public int getNoOfBedRooms() {
		return noOfBedRooms;
	}

	public void setNoOfBedRooms(int noOfBedRooms) {
		this.noOfBedRooms = noOfBedRooms;
	}

	@Column(name = "BATH_ROOMS")
	public int getNoOfBathRooms() {
		return noOfBathRooms;
	}

	public void setNoOfBathRooms(int noOfBathRooms) {
		this.noOfBathRooms = noOfBathRooms;
	}

}
