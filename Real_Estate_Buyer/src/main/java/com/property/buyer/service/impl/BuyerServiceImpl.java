package com.property.buyer.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.property.buyer.dao.BuyerDao;
import com.property.buyer.dao.IBaseDao;
import com.property.buyer.dto.RegisterBuyersDTO;
import com.property.buyer.model.Users;
import com.property.buyer.service.BuyerService;
import com.property.buyer.utility.ApplicationConstants;
import com.property.buyer.utility.EncryptionUtility;
import com.property.buyer.utility.Utility;

@Service
public class BuyerServiceImpl implements BuyerService {

	@Autowired
	private IBaseDao<Users> buyersBaseDao;

	@Autowired
	private BuyerDao buyerDao;

	@Transactional
	public boolean saveBuyer(final RegisterBuyersDTO registerBuyersDTO) {
		if (buyerDao.fetchExistingBuyers(registerBuyersDTO.getEmail(), ApplicationConstants.BUYUER)) {
			//TODO
		}
		final Users buyers = new Users();
		buyers.setCreateDate(new Date());
		buyers.setAuthorities(ApplicationConstants.USER);
		buyers.setFirstName(registerBuyersDTO.getFirstName());
		buyers.setLastName(registerBuyersDTO.getLastName());
		buyers.setUsername(registerBuyersDTO.getEmail());
		buyers.setCellPhone(registerBuyersDTO.getPhone());
		buyers.setType(ApplicationConstants.BUYUER);
		buyers.setPassword(EncryptionUtility.encrypt(registerBuyersDTO.getPassword()));
		buyersBaseDao.save(buyers);

		return true;
	}

	@Override
	@Transactional
	public Users getUserLoginId(String username) {
		try {
			/* find column for login */
			final Users user = buyersBaseDao.findUniqueByColumn(Users.class, "username", username);
			if (user != null) {
				return user;
			}
		} catch (final Exception e) {
			Utility.getLogger(getClass()).error("Exception occurs at Login", e);
		}
		return null;
	}
}
