/* 
 * ===========================================================================
 * File Name BuyerService.java
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
 * $Log: BuyerService.java,v $
 * ===========================================================================
 */
package com.property.buyer.service;

import com.property.buyer.dto.RegisterBuyersDTO;
import com.property.buyer.model.Users;

public interface BuyerService {

	Users getUserLoginId(String username);

	boolean saveBuyer(final RegisterBuyersDTO registerBuyersDTO);

}
