package com.property.buyer.service.impl;

import java.util.Date;
import java.util.List;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;

import com.mchange.net.MailSender;
import com.property.buyer.ajax.AjaxRequest;
import com.property.buyer.ajax.AjaxResponse;
import com.property.buyer.dao.BuyerDao;
import com.property.buyer.dao.IBaseDao;
import com.property.buyer.dto.RegisterBuyersDTO;
import com.property.buyer.model.Property;
import com.property.buyer.model.Users;
import com.property.buyer.service.BuyerService;
import com.property.buyer.utility.ApplicationConstants;
import com.property.buyer.utility.EncryptionUtility;
import com.property.buyer.utility.SendMail;
import com.property.buyer.utility.Utility;

@Service
public class BuyerServiceImpl implements BuyerService {

	@Autowired
	private IBaseDao<Users> buyersBaseDao;
	@Autowired
	private BuyerDao buyerDao;
	@Autowired
	private SendMail sender;
	/*@Value("${mail.from}")
	private String from;
	@Value("${pmt.to.cust.subject}")
	private String subject;
	@Value("${pmt.to.cust.msg}")
	private String msg;*/

	@Transactional
	public boolean saveBuyer(final RegisterBuyersDTO registerBuyersDTO) {
		if (buyerDao.fetchExistingBuyers(registerBuyersDTO.getEmail(), ApplicationConstants.BUYUER)) {
			// TODO
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

	@Override
	@Transactional
	public void searchProperty(ModelMap map, AjaxRequest ajaxRequest, AjaxResponse ajaxResponse) {
		final List<Property> properties = buyerDao.searchProperty(map, ajaxRequest, ajaxResponse);
		if (properties != null && !properties.isEmpty()) {
			ajaxResponse.setData(properties);
		}
	}

	@Override
	@Transactional
	public boolean contactSeller(ModelMap map, AjaxRequest ajaxRequest) {
		/*try {
			//sender.send(from, ajaxRequest.getEmail(), subject, ajaxRequest.getMessage());
			return true;
		} catch (MessagingException e) {
			//TODO EXception Handling
		}*/
		return true;
	}
}
