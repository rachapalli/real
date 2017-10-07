/* 
 * ===========================================================================
 * File Name UserDetailsServiceImpl.java
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
 * $Log: UserDetailsServiceImpl.java,v $
 * ===========================================================================
 */
package com.property.buyer.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.property.buyer.model.Users;
import com.property.buyer.service.BuyerService;
import com.property.buyer.utility.ApplicationConstants;
import com.property.buyer.utility.EncryptionUtility;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private BuyerService buyerService;

	@Override
	public UserDetails loadUserByUsername(final String userName) throws UsernameNotFoundException {

		final Users user = buyerService.getUserLoginId(userName);
		if (user != null && !user.getType().equalsIgnoreCase(ApplicationConstants.BUYUER)) {
			return new org.springframework.security.core.userdetails.User(user.getUsername(),
					EncryptionUtility.decrypt(user.getPassword()), true, true, true, true, getGrantedAuthorities(user));
		} else {
			throw new UsernameNotFoundException("Seems to be you don't have valid credentials to log in as Buyer");
		}
	}

	private List<GrantedAuthority> getGrantedAuthorities(final Users user) {
		List<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority(user.getAuthorities()));
		return authorities;
	}

}