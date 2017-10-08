/* 
 * ===========================================================================
 * File Name Utility.java
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
 * $Log: Utility.java,v $
 * ===========================================================================
 */
package com.property.buyer.utility;

import java.util.regex.Matcher;

import org.apache.commons.lang3.math.NumberUtils;
import org.apache.log4j.Logger;

public class Utility {

	public static Logger getLogger(final Class<?> typeClass) {
		return Logger.getLogger(typeClass);
	}

	public static Boolean isValidEmail(String emailStr) {
		Matcher matcher = ApplicationConstants.VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
		return matcher.find();

	}

	public static Boolean isValidMobileNumber(String mobeileNumberStr) {
		int indexOfPlus = mobeileNumberStr.indexOf("+");
		if (indexOfPlus == 0) {
			return true;
		}
		if (NumberUtils.isNumber(mobeileNumberStr)) {
			return true;
		}
		return false;
	}

}
