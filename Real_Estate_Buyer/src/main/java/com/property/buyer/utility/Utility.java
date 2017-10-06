/* 
 * ===========================================================================
 * File Name CashBackUtility.java
 * 
 * Created on Apr 7, 2016
 *
 * This code contains copyright information which is the proprietary property
 * of CashBak-FI. No part of this code may be reproduced, stored or transmitted
 * in any form without the prior written permission of CashBak-FI.
 *
 * Copyright (C) CashBak-FI. 2017
 * All rights reserved.
 *
 * Modification history:
 * $Log: CashBackUtility.java,v $
 * ===========================================================================
 */

package com.property.buyer.utility;

import java.io.File;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.log4j.Logger;
import org.hibernate.annotations.Any;

/**
 * This is a utility class related to cashback application.
 * 
 * @author mukeshs - Chetu
 * @version 1.0 - Apr 7, 2016
 */

public class Utility {
	private static final Logger LOG = Logger.getLogger(EncryptionUtility.class);
	private static final String CHAR_LIST = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
	private static final String USER_AGENT = "Mozilla/5.0";
	private static final String URL = "http://zipfeeder.us/zip?key=GKZ8oait&zips=";

	/**
	 * A generic method to get the logger object of specified type.
	 * 
	 * @author prabinar
	 * @date 21-Jun-2017
	 * @return <code>Logger</code>
	 * @param typeClass
	 */
	public static Logger getLogger(final Class<?> typeClass) {
		return Logger.getLogger(typeClass);
	}

	/**
	 * This method is used to get current time and date.
	 * 
	 * @author mukeshs
	 * @date 26-Oct-2016
	 * @return String
	 */
	public static String getCurrentTime() {
		LOG.info("Start getCurrentTime method");
		DateFormat dateFormat = null;
		Calendar cal = null;
		dateFormat = new SimpleDateFormat("HH:mm:ss a");
		cal = Calendar.getInstance();
		LOG.info("End getCurrentTime method");
		return dateFormat.format(cal.getTime());
	}

	/**
	 * This method is used to get date from string type date.
	 * 
	 * @author mukeshs
	 * @date 26-Oct-2016
	 * @exception @return
	 *                Date.
	 * @throws ParseException
	 */
	public static Date getDateFromDate(final Date date) throws ParseException {
		Date convertedCurrentDate = null;
		if (date != null) {
			final SimpleDateFormat sdf1 = new SimpleDateFormat(CashBakConstantEnum.MM_DD_YYYY.getValue());
			String dob2 = sdf1.format(date);
			Date convCurrentDate = sdf1.parse(dob2);

			final SimpleDateFormat sdf2 = new SimpleDateFormat(CashBakConstantEnum.YYYY_MM_DD.getValue());
			String formDate = sdf2.format(convCurrentDate);
			convertedCurrentDate = sdf2.parse(formDate);

		}
		return convertedCurrentDate;
	}

	/**
	 * This method is used to get string type date.
	 * 
	 * @author uttamk
	 * @date 26-Oct-2016
	 * @exception @return
	 *                String.
	 * @throws ParseException
	 */
	public static String getDateFromStringyyyyMMdd(final Date date) throws ParseException {
		String convertedDateStr = null;
		if (date != null) {
			final SimpleDateFormat sdf1 = new SimpleDateFormat(CashBakConstantEnum.MM_DD_YYYY.getValue());
			String dob2 = sdf1.format(date);
			Date convCurrentDate = sdf1.parse(dob2);

			final SimpleDateFormat sdf2 = new SimpleDateFormat(CashBakConstantEnum.YYYY_MM_DD.getValue());
			convertedDateStr = sdf2.format(convCurrentDate);

		}
		return convertedDateStr;
	}

	/**
	 * This method is used to create directory.
	 * 
	 * @author mukeshs
	 * @date 26-Oct-2016
	 * @param directoryName
	 */
	public static void createDirectoryIfNeeded(String directoryName) {
		LOG.info("Start createDirectoryIfNeeded method");
		File theDir = new File(directoryName);
		if (!theDir.exists()) {
			theDir.mkdir();
		}
		LOG.info("End createDirectoryIfNeeded method");
	}

	/**
	 * This method generates random numbers
	 * 
	 * @author mukeshs
	 * @date 26-Oct-2016
	 * @return integer type value
	 */
	private static int getRandomNumber() {
		int randomInt = 0;
		Random randomGenerator = new Random();
		randomInt = randomGenerator.nextInt(CHAR_LIST.length());
		if (randomInt - 1 == -1) {
			return randomInt;
		} else {
			return randomInt - 1;
		}
	}

	/**
	 * This method is used to calculate the number of days when customer has
	 * taken previous loan.
	 * 
	 * @author mukeshs
	 * @date 26-Oct-2016
	 * @param currentDate
	 * @param prevDate
	 * @exception exception
	 * @return int
	 */
	public static int calculateNumOfDays(Date currentDate, Date prevDate) {
		long oneDay = 24 * 60 * 60 * 1000;
		int diff = 0;
		if (currentDate != null && prevDate != null) {
			diff = Math.round(Math.abs((currentDate.getTime() - prevDate.getTime()) / (oneDay)));
		}
		return diff;
	}

	/**
	 * This method is used to get current date string.
	 * 
	 * @author uttamk
	 * @date 26-Oct-2016
	 * @exception Any
	 *                exception
	 * @return String type date.
	 */
	public static String getCurrentDateStr() {
		LOG.info("Start getCurrentDateStr method");
		DateFormat df = new SimpleDateFormat(CashBakConstantEnum.MM_DD_YYYY.getValue());
		Date dateObj = new Date();
		LOG.info("End getCurrentDateStr method");
		return df.format(dateObj);
	}

	/**
	 * This method is used to get current date .
	 * 
	 * @author uttamk
	 * @date 26-Oct-2016
	 * @exception Any
	 *                exception
	 * @return Date type date.
	 */
	public Date getCurrentDate() {
		LOG.info("Start getCurrentDate method");
		Date dateObj = new Date();
		LOG.info("End getCurrentDate method");
		return dateObj;
	}

	/**
	 * This method is used to get string type date in MM/dd/yyyy.
	 * 
	 * @author uttamk
	 * @date 26-Oct-2016
	 * @exception Any
	 *                exception
	 * @return String.
	 * @throws ParseException
	 */
	public static String getDateFromStringGen(Date date) throws ParseException {
		String convertedDateStr = null;
		if (date != null) {
			SimpleDateFormat sdf1 = new SimpleDateFormat(CashBakConstantEnum.MM_DD_YYYY.getValue());
			String dob2 = sdf1.format(date);
			Date convCurrentDate = sdf1.parse(dob2);
			convertedDateStr = sdf1.format(convCurrentDate);
		}
		return convertedDateStr;
	}

	/**
	 * This method is used to get String type date in yyyy/MM/dd format From
	 * Date
	 * 
	 * @author uttamk
	 * @date 26-Oct-2016
	 * @exception Any
	 *                exception
	 * @return String.
	 * @throws ParseException
	 */
	public static String getStringyyyyMMddFromDate(Date date) throws ParseException {
		String convertedDateStr = null;
		if (date != null) {
			SimpleDateFormat sdf1 = new SimpleDateFormat(CashBakConstantEnum.YYYY_MM_DD.getValue());
			convertedDateStr = sdf1.format(date);
		}
		return convertedDateStr;
	}

	/**
	 * This method is used to get String type date in MM/dd/yyyy format From
	 * Date
	 * 
	 * @author uttamk
	 * @date 26-Oct-2016
	 * @exception Any
	 *                exception
	 * @return String.
	 * @throws ParseException
	 */
	public static String getStringMMddyyyyFromDate(Date date) throws ParseException {
		String convertedDateStr = null;
		if (date != null) {
			SimpleDateFormat sdf1 = new SimpleDateFormat(CashBakConstantEnum.MM_DD_YYYY.getValue());
			convertedDateStr = sdf1.format(date);
		}
		return convertedDateStr;
	}

	/**
	 * This method is used to get String type time in HH:mm:ss a format From
	 * Date
	 * 
	 * @author uttamk
	 * @date 26-Oct-2016
	 * @exception Any
	 *                exception
	 * @return String.
	 * @throws ParseException
	 */
	public static String getStringHHmmssaFromDate(Date date) throws ParseException {
		String convertedDateStr = null;
		if (date != null) {
			SimpleDateFormat sdf1 = new SimpleDateFormat("HH:mm:ss a");
			convertedDateStr = sdf1.format(date);
		}
		return convertedDateStr;
	}

	/**
	 * This method is used to get Date type date from a string .
	 * 
	 * @author uttamk
	 * @date 26-Oct-2016
	 * @exception Any
	 *                exception
	 * @return String.
	 * @throws ParseException
	 */
	public static Date getDateFromString(String dateStr) throws ParseException {
		Date convCurrentDate = null;
		try {
			if (!StringUtils.isEmpty(dateStr)) {
				DateFormat formatter = new SimpleDateFormat(CashBakConstantEnum.MM_DD_YYYY.getValue());
				convCurrentDate = (Date) formatter.parse(dateStr);
			}
			return convCurrentDate;
		} catch (ParseException e) {
			LOG.info("inside parse date exception. ", e);
		}
		return null;
	}

	/**
	 * This method is used to get Date type date from a string .
	 * 
	 * @author uttamk
	 * @date 26-Oct-2016
	 * @exception Any
	 *                exception
	 * @return String.
	 * @throws ParseException
	 */
	public static Date getDateFromStringYYYYMMDD(String dateStr) throws ParseException {
		Date convCurrentDate = null;
		if (!StringUtils.isEmpty(dateStr)) {
			DateFormat formatter = new SimpleDateFormat(CashBakConstantEnum.YYYY_MM_DD.getValue());
			convCurrentDate = (Date) formatter.parse(dateStr);
		}
		return convCurrentDate;
	}

	/**
	 * This method is used to get round of double value
	 * 
	 * @author shravans
	 * @date 26-Oct-2016
	 * @param value
	 *            double and places integer containing
	 * @exception Anyexception
	 * @return double
	 */
	public static double round(double value, int places) {
		if (places < 0)
			throw new IllegalArgumentException();

		BigDecimal bd = new BigDecimal(value);
		bd = bd.setScale(places, RoundingMode.HALF_UP);
		return bd.doubleValue();
	}

	/**
	 * This method is used to compare whether date are equal or not
	 * 
	 * @author mukeshs
	 * @date 26-Oct-2016
	 * @param dbDate
	 *            A Date object containing
	 * @param inputDate
	 *            A Date object containing
	 * @return boolean
	 * @throws Exception
	 */
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

	/**
	 * This method is used to sort map by value.
	 * 
	 * @author shravans
	 * @date Jun 9, 2017
	 * @return Map<K,V>
	 * @param map
	 */
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

	/**
	 * parsing the date object into the date string
	 * 
	 * @author shravans
	 * @date Jun 28, 2017
	 * @return <code>String</code>
	 * @param dateParam
	 */
	public static String parseUTCDateTimeToString(final Date dateParam) {
		if (dateParam != null) {
			final String dateFormatNow = CashBakConstantEnum.MM_DD_YYYY_HH_MM_SS.getValue();
			final SimpleDateFormat sdf = new SimpleDateFormat(dateFormatNow);
			return sdf.format(dateParam);
		} else {
			LOG.error("parseDateTimeToString : Date parameter is null");
		}
		return "";
	}

	/**
	 * Method is written to parse the date string to date Object with time
	 * 
	 * @author shravans
	 * @date Jun 28, 2017
	 * @return <code>Date</code>
	 * @param dateParam
	 */
	public static Date parseStringToUTCDateTime(final String dateParam) {
		final DateFormat df = new SimpleDateFormat(CashBakConstantEnum.MM_DD_YYYY_HH_MM_SS.getValue());
		try {
			LOG.info("Parsing the Date : " + dateParam);
			return df.parse(dateParam);
		} catch (ParseException e) {
			LOG.info("inside parse date exception. ");
		}
		return null;
	}

	/**
	 * This method is used to concat the strings to single string.
	 * 
	 * @author shravans
	 * @date Jul 10, 2017
	 * @return <code>String</code>
	 */
	public static String stringConcatanator(String... strings) {
		final StringBuilder stringBuilder = new StringBuilder();
		for (int index = 0; index < strings.length; index++) {
			stringBuilder.append(strings[index]);
		}
		return stringBuilder.toString();
	}

	/**
	 * This method is used to concat the strings to single string builder.
	 * 
	 * @author shravans
	 * @date Jul 12, 2017
	 * @return <code>String</code>
	 * @param strings
	 */
	public static StringBuilder stringBuilderConcatanator(String... strings) {
		final StringBuilder stringBuilder = new StringBuilder();
		for (int index = 0; index < strings.length; index++) {
			stringBuilder.append(strings[index]);
		}
		return stringBuilder;
	}

	public static String getColumnNameByValue(String valToCheck) {
		if (isValidEmail(valToCheck)) {
			return "username";
		} else if (isValidMobileNumber(valToCheck)) {
			return "cellPhone";
		} else {
			return "id";
		}
	}

	/**
	 * This method return true if passed email is a valid email otherwise return
	 * false.
	 * 
	 * @author prabinar
	 * @date 26-Jun-2017
	 * @return <code>Boolean</code>
	 * @param emailStr
	 */
	public static Boolean isValidEmail(String emailStr) {
		Matcher matcher = ApplicationConstants.VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
		return matcher.find();

	}

	/**
	 * This method will return true if passed mobile number is vaplid other wise
	 * return false.
	 * 
	 * @author prabinar
	 * @date 26-Jun-2017
	 * @return <code>Boolean</code>
	 * @param mobeileNumberStr
	 */
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
