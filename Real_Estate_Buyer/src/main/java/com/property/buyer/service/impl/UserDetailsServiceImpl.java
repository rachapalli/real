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

import com.property.buyer.model.Buyers;
import com.property.buyer.service.BuyerService;
import com.property.buyer.utility.EncryptionUtility;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private BuyerService buyerService;

	@Override
	public UserDetails loadUserByUsername(final String userName) throws UsernameNotFoundException {

		final Buyers user = buyerService.getUserLoginId(userName);
		if (user != null) {
			return new org.springframework.security.core.userdetails.User(user.getUsername(),
					EncryptionUtility.decrypt(user.getPassword()), true, true, true, true, getGrantedAuthorities(user));
		} else {
			throw new UsernameNotFoundException("User does not exists.");
		}
	}

	private List<GrantedAuthority> getGrantedAuthorities(final Buyers user) {
		List<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority(user.getAuthorities()));
		return authorities;
	}

}