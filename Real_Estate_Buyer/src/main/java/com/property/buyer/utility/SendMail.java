/* 
 * ===========================================================================
 * File Name SendMail.java
 * 
 * Created on Feb 24, 2016
 *
 * This code contains copyright information which is the proprietary property
 * of CashBak-FI. No part of this code may be reproduced, stored or transmitted
 * in any form without the prior written permission of CashBak-FI.
 *
 * Copyright (C) CashBak-FI. 2017
 * All rights reserved.
 *
 * Modification history:
 * $Log: SendMail.java,v $
 * ===========================================================================
 */

package com.property.buyer.utility;
/**
* This  class is used to perform the mail functionality
* 
* @author mukeshs 
* @version 1.0 - Feb 24, 2016
*/
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

	/**
	 * This method is used to send the mail.
	 * @author uttamk
     * @date 01-Mar-2016
	 * @return void
	 * @param from
	 *            String type
	 * @param to
	 *            String type
	 * @param subject
	 *            String type
	 * @param msg
	 *            String type
	 * @param custId
     *            Long type          
	 * @throws MessagingException
	 */
	public void send(String from, String to, String subject, String msg,Long custId) throws MessagingException {
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

	
	/**
     * This method is used to send the mail.
     * @author uttamk
     * @date 01-Mar-2016
     * @return void
     * @param from
     *            String type
     * @param to
     *            String type
     * @param subject
     *            String type
     * @param msg
     *            String type
     * @throws MessagingException
     */
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
