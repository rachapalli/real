/* 
 * ===========================================================================
 * File Name AjaxRequest.java
 * 
 * Created on 15-Jun-2017
 *
 * This code contains copyright information which is the proprietary property
 * of GirasolPayments-Admin. No part of this code may be reproduced, stored or transmitted
 * in any form without the prior written permission of GirasolPayments-Admin.
 *
 * Copyright (C) GirasolPayments-Admin. 2017
 * All rights reserved.
 *
 * Modification history:
 * $Log: AjaxRequest.java,v $
 * ===========================================================================
 */

package com.property.buyer.ajax;

/**
 * This class is used to keep ajax request attribute values.
 * 
 * @author Umamaheswara
 * @version 1.0 - Sep 22, 2017
 */

public class AjaxRequest {

	private String taskStatus;
	private long customerId;
	private long employeeId;
	private String taskLead;
	private long storeId;
	private long loanId;
	private String taskId;
	private String bankName;

	/**
	 * 
	 * @description
	 */
	public AjaxRequest() {
		super();
	}

	public String getTaskStatus() {
		return taskStatus;
	}

	public void setTaskStatus(String taskStatus) {
		this.taskStatus = taskStatus;
	}

	public long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}

	public long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(long employeeId) {
		this.employeeId = employeeId;
	}

	public String getTaskLead() {
		return taskLead;
	}

	public void setTaskLead(String taskLead) {
		this.taskLead = taskLead;
	}

	public long getStoreId() {
		return storeId;
	}

	public void setStoreId(long storeId) {
		this.storeId = storeId;
	}

	public long getLoanId() {
		return loanId;
	}

	public void setLoanId(long loanId) {
		this.loanId = loanId;
	}

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

}