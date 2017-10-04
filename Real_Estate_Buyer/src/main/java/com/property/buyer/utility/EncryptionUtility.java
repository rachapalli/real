/* 
 * ===========================================================================
 * File Name EncryptionUtility.java
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
 * $Log: EncryptionUtility.java,v $
 * ===========================================================================
 */
package com.property.buyer.utility;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Locale;
import java.util.Random;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SealedObject;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.util.StringUtils;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * This utility class is used for password encryption and decryption.
 * 
 * @author prabinar
 * @date Mar 30, 2017
 */
public class EncryptionUtility {

	private static final BASE64Encoder enc = new BASE64Encoder();
	private static final BASE64Decoder dec = new BASE64Decoder();

	/**
	 * This method is used to encrypt password using AES algorithm.
	 * 
	 * @author prabinar
	 * @date 21-Jun-2017
	 * @return <code>String</code>
	 * @param value
	 */
	public static String encrypt(final String value) {
		if (null != value) {
			try {
				final Cipher cipher = Cipher.getInstance(ApplicationConstants.ALGORITHM);
				cipher.init(Cipher.ENCRYPT_MODE, generateKey());
				return new sun.misc.BASE64Encoder().encode(cipher.doFinal(value.getBytes(ApplicationConstants.UTF_8)));
			} catch (final InvalidKeyException exception) {
				Utility.getLogger(EncryptionUtility.class)
						.error("InvalidKeyException occured while encrypting the string ", exception);
			} catch (final NoSuchAlgorithmException exception) {
				Utility.getLogger(EncryptionUtility.class)
						.error("NoSuchAlgorithmException occured while encrypting the string ", exception);
			} catch (final NoSuchPaddingException exception) {
				Utility.getLogger(EncryptionUtility.class)
						.error("NoSuchPaddingException occured while encrypting the string ", exception);
			} catch (final IllegalBlockSizeException exception) {
				Utility.getLogger(EncryptionUtility.class)
						.error("IllegalBlockSizeException occured while encrypting the string ", exception);
			} catch (final BadPaddingException exception) {
				Utility.getLogger(EncryptionUtility.class)
						.error("BadPaddingException occured while encrypting the string ", exception);
			} catch (final UnsupportedEncodingException exception) {
				Utility.getLogger(EncryptionUtility.class)
						.error("UnsupportedEncodingException occured while encrypting the string ", exception);
			}
		}
		return null;
	}

	/**
	 * This method is decrypt password.
	 * 
	 * @author prabinar
	 * @date 21-Jun-2017
	 * @return <code>String</code>
	 * @param value
	 */
	public static String decrypt(final String value) {
		if (null != value) {
			try {
				final Cipher cipher = Cipher.getInstance(ApplicationConstants.ALGORITHM);
				cipher.init(Cipher.DECRYPT_MODE, generateKey());
				return new String(cipher.doFinal(new sun.misc.BASE64Decoder().decodeBuffer(value)),
						ApplicationConstants.UTF_8);
			} catch (final NoSuchAlgorithmException exception) {
				Utility.getLogger(EncryptionUtility.class)
						.error("NoSuchAlgorithmException occured while decrypting the string ", exception);
			} catch (final NoSuchPaddingException exception) {
				Utility.getLogger(EncryptionUtility.class)
						.error("NoSuchPaddingException occured while decrypting the string ", exception);
			} catch (final InvalidKeyException exception) {
				Utility.getLogger(EncryptionUtility.class)
						.error("InvalidKeyException occured while decrypting the string ", exception);
			} catch (final UnsupportedEncodingException exception) {
				Utility.getLogger(EncryptionUtility.class)
						.error("UnsupportedEncodingException occured while decrypting the string ", exception);
			} catch (final IllegalBlockSizeException exception) {
				Utility.getLogger(EncryptionUtility.class)
						.error("IllegalBlockSizeException occured while decrypting the string ", exception);
			} catch (final BadPaddingException exception) {
				Utility.getLogger(EncryptionUtility.class)
						.error("BadPaddingException occured while decrypting the string ", exception);
			} catch (final IOException exception) {
				Utility.getLogger(EncryptionUtility.class).error("IOException occured while decrypting the string ",
						exception);
			}
		}
		return null;
	}

	/**
	 * This method is used to generate a key.
	 * 
	 * @author prabinar
	 * @date 21-Jun-2017
	 * @return <code>Key</code>
	 * @throws Exception
	 */
	private static Key generateKey() {
		return new SecretKeySpec(ApplicationConstants.KEY.getBytes(), ApplicationConstants.ALGORITHM);
	}

	/**
	 * This method is used to generate random string having given length.
	 * 
	 * @author prabinar
	 * @date 21-Jun-2017
	 * @return <code>String</code>
	 * @param length
	 */
	public static String generateString(final int length) {
		return RandomStringUtils.randomAlphanumeric(length).toUpperCase(Locale.US);
	}

	/**
	 * Generate random number.
	 * 
	 * @author prabinar
	 * @date 21-Jun-2017
	 * @return <code>int</code>
	 * @param min
	 * @param max
	 */
	public static int randInt(final int min, final int max) {
		/*
		 * NOTE: Usually this should be a field rather than a method variable so
		 * that it is not re-seeded every call.
		 */
		final Random rand = new Random();
		/*
		 * nextInt is normally exclusive of the top value, so add 1 to make it
		 * inclusive
		 */
		final int randomNum = rand.nextInt((max - min) + 1) + min;
		return randomNum;
	}

	/**
	 * This method is used to decode String
	 * 
	 * @author shravans
	 * @date Sep 8, 2017
	 * @return <code>String</code>
	 * @param encoded
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 */
	public static String decode(final String encoded) throws UnsupportedEncodingException, IOException {
		final String key = encoded.substring(0, 23);
		final String text = encoded.substring(23);
		return xorMessage(new String(dec.decodeBuffer(text), ApplicationConstants.UTF_8), key);
	}

	/**
	 * This method is used by encode and decode methods
	 * 
	 * @author shravans
	 * @date Sep 8, 2017
	 * @return <code>String</code>
	 * @param message
	 * @param key
	 */
	public static String xorMessage(final String message, final String key) {
		try {
			if (message == null || key == null)
				return null;

			final char[] keys = key.toCharArray();
			final char[] mesg = message.toCharArray();

			final int ml = mesg.length;
			final int kl = keys.length;
			final char[] newmsg = new char[ml];

			for (int i = 0; i < ml; i++) {
				newmsg[i] = (char) (mesg[i] ^ keys[i % kl]);
			} // for i

			return new String(newmsg);
		} catch (final NullPointerException npException) {
			return null;
		}
	}

	/**
	 * This method is used to generate random string
	 * 
	 * @author shravans
	 * @date Sep 8, 2017
	 * @return <code>String</code>
	 * @param length
	 */
	public static String generateRandomString(final int length) {
		final char[] chars = "abcdefghijklmnopqrstuvwxyz".toCharArray();
		final StringBuilder strBuilder = new StringBuilder();
		final Random random = new Random();
		for (int index = 0; index < length; index++) {
			final char charFromArr = chars[random.nextInt(chars.length)];
			strBuilder.append(charFromArr);
		}
		return strBuilder.toString();
	}

	/**
	 * This method is used to encrypt and write object to given file name
	 * 
	 * @author shravans
	 * @date Sep 8, 2017
	 * @return <code>void</code>
	 * @param object
	 * @param fileName
	 * @throws IllegalBlockSizeException
	 * @throws IOException
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchPaddingException
	 * @throws InvalidKeyException
	 */
	public static void encryptAndWriteObject(final Object object, final String fileName)
			throws IllegalBlockSizeException, IOException, NoSuchAlgorithmException, NoSuchPaddingException,
			InvalidKeyException {
		if (object != null && !StringUtils.isEmpty(fileName)) {
			final SecretKey key64 = new SecretKeySpec(new byte[] { 0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07 },
					"Blowfish");
			final Cipher cipher = Cipher.getInstance("Blowfish");
			cipher.init(Cipher.ENCRYPT_MODE, key64);
			final CipherOutputStream cipherOutputStream = new CipherOutputStream(
					new BufferedOutputStream(new FileOutputStream(fileName)), cipher);
			final ObjectOutputStream outputStream = new ObjectOutputStream(cipherOutputStream);
			outputStream.writeObject(new SealedObject((Serializable) object, cipher));
			outputStream.close();
		}

	}

	/**
	 * This method is used to decrypt and read object to given file name
	 * 
	 * @author shravans
	 * @date Sep 8, 2017
	 * @return <code>Object</code>
	 * @param fileName
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchPaddingException
	 * @throws InvalidKeyException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 */
	public static Object decryptAndReadObject(final String fileName)
			throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IOException,
			ClassNotFoundException, IllegalBlockSizeException, BadPaddingException {
		final SecretKey key64 = new SecretKeySpec(new byte[] { 0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07 },
				"Blowfish");
		final Cipher cipher = Cipher.getInstance("Blowfish");
		cipher.init(Cipher.DECRYPT_MODE, key64);

		final CipherInputStream cipherInputStream = new CipherInputStream(
				new BufferedInputStream(new FileInputStream(fileName)), cipher);

		final ObjectInputStream inputStream = new ObjectInputStream(cipherInputStream);
		final SealedObject sealedObject = (SealedObject) inputStream.readObject();
		final Object object = (Serializable) sealedObject.getObject(cipher);
		cipherInputStream.close();
		return object;

	}

}