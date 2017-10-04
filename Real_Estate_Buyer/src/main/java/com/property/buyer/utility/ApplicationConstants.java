/* 
 * ===========================================================================
 * File Name ApplicationConstants.java
 * 
 * Created on Jun 19, 2017
 *
 * This code contains copyright information which is the proprietary property
 * of GirasolPayments-Admin. No part of this code may be reproduced, stored or transmitted
 * in any form without the prior written permission of GirasolPayments-Admin.
 *
 * Copyright (C) GirasolPayments-Admin. 2017
 * All rights reserved.
 *
 * Modification history:
 * $Log: ApplicationConstants.java,v $
 * ===========================================================================
 */

package com.property.buyer.utility;

import java.text.SimpleDateFormat;
import java.util.regex.Pattern;

/**
 * This class contains constants used in import.
 * 
 * @author prabinar
 * @version 1.0 - Jun 20, 2017
 */
public class ApplicationConstants {
	public static final String MESSAGE = "message";
	public static final String RANDOMPASSWORD = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789~`!@#$%^&*()-_=+[{]}\\|;:\'\",<.>/?";
	public static final int RANDOM_PASSWORD_LENGTH = 8;
	public static final String ALGORITHM = "AES";
	public static final String KEY = "1Hbfh667adfDEJ78";
	public static final String UTF_8 = "UTF-8";
	public static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
			Pattern.CASE_INSENSITIVE);
	public static final SimpleDateFormat FORMATTER = new SimpleDateFormat("MM/dd/yyyy");
	public static final SimpleDateFormat TIME_FORMATTER = new SimpleDateFormat("hh:mm:ss");
	public static final String LOGIN_INVITATION = "LOGIN_INVITATION";
	public static final String MERCHANT_INVITATION_VALUE = "MERCHANT_INVITATION";
	public static final String COMPANY_INVITATION_VALUE = "COMPANY_INVITATION";
	public static final String MERCHANT_LOGIN_INVITATION = "MERCHANT_LOGIN_INVITATION";
	public static final String MERCHANT_VERIFICATION_INVITATION = "MERCHANT_VERIFICATION_INVITATION";
	public static final String APPROVED_USER_ACCOUNTS = "APPROVED_USER_ACCOUNTS";
	public static final String MERCHANT_DELETE_COLUMN = "merchnatDeleted";
	public static final String ROLE_DELETE_COLUMN = "deleted";
	public static final String USER_NAME_COLUMN = "username";
	public static final String ID_COLUMN = "id";
	public static final String ROLE_OBJECT = "role";
	public static final String LOGGEDUSER = "loggedUser";
	public static final String CONTENTTYPE = "application/pdf";
	public static final String CONTENTTYPE_JSON = "application/json";
	public static final String CONTENT_HEADER = "Cache-Control";
	public static final String CONTENT_HEADER_NO_STORE = "no-store";
	public static final int FIVE = 5;
	public static final int ZERO = 0;
	public static final int ONE = 1;
	public static final int TEN = 10;
	public static final int FIVEHUNDRED = 500;
	public static final int THREE = 3;
	public static final int FOUR = 4;
	public static final Long BASIC_MERCHANT_ROLE_ID = 3L;
	public static final Long LENDER_MERCHANT_ROLE_ID = 4L;
	public static final Long VOUCHER_MERCHANT_ROLE_ID = 5L;
	public static final Long VOUCHER_COMPANY_ROLE_ID = 8L;
	public static final int TWO = 2;
	public static final String ASSIGN_VOUCHER_PER = "assignVoucherPer";
	public static final String MANAGE_ROLE_PER = "manageRolePer";
	public static final String MANAGE_VOUCHER_PER = "manageVoucherPer";
	public static final String MANAGE_EMPLOYEE_PER = "manageEmployeePer";
	public static final String ASSIGN_VOUCHER = "Assign Voucher";
	public static final String MANAGE_ROLE = "Manage Role";
	public static final String MANAGE_VOUCHER = "Manage Voucher";
	public static final String MANAGE_EMPLOYEE = "Manage Employee";
	public static final String CONSTANT_PASS = "Girasol@123";
	public static final int VOUCHER_QRCODE_SIZE = 180;
	public static final int QRDOC_WIDTH = 700;
	public static final int QRDOC_HEIGHT = 420;
	public static final int MERCHANT_QRDOC_WIDTH = 300;
	public static final int MERCHANT_QRDOC_HEIGHT = 300;
	public static final int QRDOC_MARGIN = 20;
	public static final int VCTABLE_WITDH_PER = 100;
	public static final int VCTABLE_TOTAL_COL = 5;
	public static final int VCTABLE_FIRST_COL_WIDTH_PER = 10;
	public static final int VCTABLE_SECOND_COL_WIDTH_PER = 35;
	public static final int VCTABLE_THIRD_COL_WIDTH_PER = 10;
	public static final int VCTABLE_FOURTH_COL_WIDTH_PER = 35;
	public static final int VCTABLE_FIFTH_COL_WIDTH_PER = 10;
	public static final int DEFAULT_FONT_SIZE = 12;
	public static final int HEADER_FONT_SIZE = 24;
	public static final int AMOUNT_FONT_SIZE = 18;
	public static final float QR_IMG_HT = 150f;
	public static final float TWENTY_FL = 20f;
	public static final float THIRTY_FIVE_FL = 35f;
	public static final float EIGHTY_FIVE_FL = 85f;
	public static final int EIGHT = 8;
	public static final float SIXTEEN_FL = 16f;
	public static final int VOUCHER_NAME_FONT_SIZE = 34;
	public static final String VOUCHER_NAME_COLOR = "#009cbf";
	public static final char ZERO_CHAR = '0';
	public static final int HUNDRED = 100;
	//AESCrypt-ObjC uses CBC and PKCS7Padding
	public static final String AES_MODE = "AES/CBC/PKCS7Padding";
	//AESCrypt-ObjC uses SHA-256 (and so a 256-bit key)
	public static final String HASH_ALGORITHM = "SHA-256";
	//AESCrypt-ObjC uses blank IV (not the best security, but the aim here is compatibility)
	public static final byte[] IV_BYTES = {0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00};
	public static final String QR_CODE_STRING = "qr code";
	public static final String GIRASOL_LOGO_PATH = "../resources/images/logo.png";
	public static final String PENDING = "PENDING";

}