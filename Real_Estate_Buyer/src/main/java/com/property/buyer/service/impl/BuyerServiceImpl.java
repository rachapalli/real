package com.property.buyer.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.property.buyer.dao.IBaseDao;
import com.property.buyer.dto.RegisterBuyersDTO;
import com.property.buyer.model.Buyers;
import com.property.buyer.service.BuyerService;
import com.property.buyer.utility.EncryptionUtility;
import com.property.buyer.utility.Utility;

@Service
public class BuyerServiceImpl implements BuyerService {

	@Autowired
	private IBaseDao<Buyers> buyersBaseDao;

	@Transactional
	public boolean saveBuyer(final RegisterBuyersDTO registerBuyersDTO) {
		// TODO check for the existing buyers
		final Buyers buyers = new Buyers();
		buyers.setCreateDate(new Date());
		buyers.setAuthorities("USER");
		buyers.setFirstName(registerBuyersDTO.getFirstName());
		buyers.setLastName(registerBuyersDTO.getLastName());
		buyers.setUsername(registerBuyersDTO.getEmail());
		buyers.setCellPhone(registerBuyersDTO.getPhone());
		buyers.setPassword(EncryptionUtility.encrypt(registerBuyersDTO.getPassword()));
		buyersBaseDao.save(buyers);

		return true;
	}

	@Override
	@Transactional
	public Buyers getUserLoginId(String username) {
		try {
			/* find column for login */
			final Buyers user = buyersBaseDao.findUniqueByColumn(Buyers.class, "username", username);
			if (user != null) {
				return user;
			}
		} catch (final Exception e) {
			Utility.getLogger(getClass()).error("Exception occurs at Login", e);
		}
		return null;
	}
}
