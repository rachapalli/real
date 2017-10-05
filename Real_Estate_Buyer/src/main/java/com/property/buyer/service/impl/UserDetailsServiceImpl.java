/* 
 * ===========================================================================
 * File Name AuthenticateManagerService.java
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
 * $Log: AuthenticateManagerService.java,v $
 * ===========================================================================
 */

package com.property.buyer.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.property.buyer.model.Buyers;
import com.property.buyer.service.BuyerService;
import com.property.buyer.utility.EncryptionUtility;

/**
 * This class implements userDetailsService with is used to authenticate user
 * for Spring Security.
 * 
 * @author prabinar
 * @version 1.0 - Jun 20, 2017
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private BuyerService userService;

	public UserDetails loadUserByUsername(final String userName) throws UsernameNotFoundException {
		final Buyers user = userService.getUserLoginId(userName);
		if (user != null) {
			return new org.springframework.security.core.userdetails.User(user.getUsername(),
					EncryptionUtility.decrypt(user.getPassword()), true, true, true, true,
					getGrantedAuthorities(user));
		} else {
			throw new UsernameNotFoundException("User does not exists.");
		}
	}

	/**
	 * A method to return the Granted authority list to the user so that his
	 * role can be checked.
	 * 
	 * @author prabinar
	 * @date 19-Jun-2017
	 * @return <code>List<GrantedAuthority></code>
	 * @param user
	 */
	private List<GrantedAuthority> getGrantedAuthorities(final Buyers user) {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		//authorities.add(new SimpleGrantedAuthority(user.getRole().getRoleName()));
		return authorities;
	}
}