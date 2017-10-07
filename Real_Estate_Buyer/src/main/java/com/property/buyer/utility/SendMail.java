/* 
 * ===========================================================================
 * File Name SendMail.java
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
 * $Log: SendMail.java,v $
 * ===========================================================================
 */
package com.property.buyer.utility;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class SendMail {

	private static final Logger LOG = Logger.getLogger(SendMail.class);

	@Autowired
	JavaMailSender mailSender;

	public void send(String from, String to, String subject, String msg) throws MessagingException {
		LOG.info("Start send method");
		MimeMessage message = mailSender.createMimeMessage();
		message.setSubject(subject);
		MimeMessageHelper helper;
		helper = new MimeMessageHelper(message, true);
		helper.setFrom(from);
		helper.setTo(to);
		helper.setText(msg, true);
		mailSender.send(message);
		LOG.info("End send method");
	}

	public void setMailSender(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}
}
