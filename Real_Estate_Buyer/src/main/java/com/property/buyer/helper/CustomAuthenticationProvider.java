/* 
 * ===========================================================================
 * File Name CustomAuthenticationProvider.java
 * 
 * Created on  Apr 28, 2016
 *
 * This code contains copyright information which is the proprietary property
 * of CashBak-FI. No part of this code may be reproduced, stored or transmitted
 * in any form without the prior written permission of CashBak-FI.
 *
 * Copyright (C) CashBak-FI. 2017
 * All rights reserved.
 *
 * Modification history:
 * $Log: CustomAuthenticationProvider.java,v $
 * ===========================================================================
 */

package com.property.buyer.helper;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
/**
*  It provide the implementation of methods that contains AuthenticationProvider interface.
* 
* @author mukeshs 
* @version 1.0 - Apr 28, 2016
*/
@Component
public class CustomAuthenticationProvider implements AuthenticationProvider{
 
    @Autowired
    private UserDetailsService userDetailsService;
    
    @Autowired
    private MessageSource source;
    
    public Authentication authenticate(final Authentication authentication) {
          String username = authentication.getName();
          String password = (String) authentication.getCredentials();
     
            UserDetails user = userDetailsService.loadUserByUsername(username); 
     
            if (user == null || !user.getUsername().equalsIgnoreCase(username)) {
                throw new BadCredentialsException(source.getMessage("msg.username.not.found", null, null));
            }
     
            if (!password.equals(user.getPassword())) {
                throw new BadCredentialsException(source.getMessage("msg.wrong.pass", null, null));
            }
     
            Collection<? extends GrantedAuthority> authorities = user.getAuthorities();
     
            return new UsernamePasswordAuthenticationToken(user, password, authorities);
    }
 
    public boolean supports(Class<?> arg0) {
        return true;
    }
 
}