/* 
 * ===========================================================================
 * File Name CashBakConstantEnum.java
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
 * $Log: CashBakConstantEnum.java,v $
 * ===========================================================================
 */


package com.property.buyer.utility;

/**
 * This is a enum class related to cashback application.
 * 
 * @author mukeshs
 * @version 1.0 - Apr 7, 2016
 */
public enum CashBakConstantEnum {
	WELCOME("index"), 
	REGISTER("register"),
	EMPTY_STRING(""),
	LOGGED_OUT_SUCCESSFULLY("You have been logged out successfully."),
	LOGIN("login"),
	LOGOUT("logout"),
	FORGOT_PASSWORD_TEXT("forgotPassword"),
	SESSIONEXPMSG("sessionExpMsg"),
	SESSION_EXP_MSG("Your Session has been expired."),
	LOGO_IMAGE_NOT_FOUND_MSG("unable to find logoImage in classpath: "),	
	ALREADY_LOGIN("alreadylogin"),
	AUTHFAILED("authfailed"),
	INVALID_USER_NAME_OR_PASSWORD("Invalid User Name or Password");
	
	private String value;

	CashBakConstantEnum(String value) {
		this.value = value;

	}

	public String getValue() {
		return value;
	}

}
