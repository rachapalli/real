/* 
 * ===========================================================================
 * File Name ApplicationConstants.java
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
 * $Log: ApplicationConstants.java,v $
 * ===========================================================================
 */

package com.property.buyer.utility;

import java.text.SimpleDateFormat;
import java.util.regex.Pattern;

public class ApplicationConstants {
	public static final String LOGGED_USER = "loggedUser";
	public static final String MESSAGE = "message";
	public static final String ALGORITHM = "AES";
	public static final String KEY = "1Hbfh667adfDEJ78";
	public static final String UTF_8 = "UTF-8";
	public static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
			Pattern.CASE_INSENSITIVE);
	public static final SimpleDateFormat FORMATTER = new SimpleDateFormat("MM/dd/yyyy");
	public static final SimpleDateFormat TIME_FORMATTER = new SimpleDateFormat("hh:mm:ss");
	public static final String USER_NAME_COLUMN = "username";
	// AESCrypt-ObjC uses CBC and PKCS7Padding
	public static final String AES_MODE = "AES/CBC/PKCS7Padding";
	// AESCrypt-ObjC uses SHA-256 (and so a 256-bit key)
	public static final String HASH_ALGORITHM = "SHA-256";
	public static final String SELLER = "SELLER";
	public static final String BUYUER = "BUYER";
	public static final String USER = "USER";
	// AESCrypt-ObjC uses blank IV (not the best security, but the aim here is
	// compatibility)

}