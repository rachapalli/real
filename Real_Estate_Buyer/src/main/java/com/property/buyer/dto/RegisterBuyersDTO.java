/* 
 * ===========================================================================
 * File Name RegisterBuyers.java
 * 
 * Created on Oct 5, 2017
 *
 * This code contains copyright information which is the proprietary property
 * of Real_Estate_Buyer. No part of this code may be reproduced, stored or transmitted
 * in any form without the prior written permission of Real_Estate_Buyer.
 *
 * Copyright (C) Real_Estate_Buyer. 2017
 * All rights reserved.
 *
 * Modification history:
 * $Log: RegisterBuyers.java,v $
 * ===========================================================================
 */

package com.property.buyer.dto;

/**
 * Class Information
 * 
 * @author umamaheswarar - Chetu
 * @version 1.0 - Oct 5, 2017
 */
public class RegisterBuyersDTO {

	private long id;
	private String firstName;
	private String lastName;
	private String password;
	private String phone;
	private String email;

	public RegisterBuyersDTO() {
		super();
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

}
