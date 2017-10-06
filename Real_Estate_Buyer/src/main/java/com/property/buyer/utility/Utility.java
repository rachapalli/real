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

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;

import org.apache.commons.lang3.math.NumberUtils;
import org.apache.log4j.Logger;

public class Utility {

	public static Logger getLogger(final Class<?> typeClass) {
		return Logger.getLogger(typeClass);
	}

	
	public static double round(double value, int places) {
		if (places < 0)
			throw new IllegalArgumentException();

		BigDecimal bd = new BigDecimal(value);
		bd = bd.setScale(places, RoundingMode.HALF_UP);
		return bd.doubleValue();
	}

	
	public static boolean dateCompare(Date dbDate, Date inputDate) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String dbDateString = dateFormat.format(dbDate);
		String inputDateString = dateFormat.format(inputDate);
		if (dbDateString.equals(inputDateString)) {
			return true;
		} else {
			return false;
		}
	}

	public static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> map) {
		List<Map.Entry<K, V>> list = new LinkedList<Map.Entry<K, V>>(map.entrySet());
		Collections.sort(list, new Comparator<Map.Entry<K, V>>() {
			public int compare(Map.Entry<K, V> o1, Map.Entry<K, V> o2) {
				return (o1.getValue()).compareTo(o2.getValue());
			}
		});

		Map<K, V> result = new LinkedHashMap<K, V>();
		for (Map.Entry<K, V> entry : list) {
			result.put(entry.getKey(), entry.getValue());
		}
		return result;
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
