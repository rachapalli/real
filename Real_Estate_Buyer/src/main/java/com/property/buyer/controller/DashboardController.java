package com.property.buyer.controller;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.annotations.Any;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.property.buyer.model.Buyers;
import com.property.buyer.service.BuyerService;
import com.property.buyer.utility.ApplicationConstants;
import com.property.buyer.utility.Utility;

/**
 * @author mukeshs CashBak-F1 Jan 30, 2016 It is used to handle the requested
 *         related to dashboard
 */
@Controller
@SessionAttributes(ApplicationConstants.LOGGED_USER)
@RequestMapping(value = "/real")
public class DashboardController {

	@Autowired
	private BuyerService buyerService;

	/**
	 * This method is used to view home page.
	 * 
	 * @author mukeshs
	 * @param request
	 *            A HttpServletRequest object containing
	 * @exception Any
	 *                exception
	 * @return ModelAndView type object
	 */
	@RequestMapping(value = "/dashboard", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView dashboardView(final HttpServletRequest request, final ModelMap model) {
		final ModelAndView modelView = new ModelAndView("dashboard");
		Buyers loggedUser = null;
		try {
			final HttpSession httpSession = request.getSession(false);

			/* Get the authentication from the context holder. */
			final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			/* Checking if the user is authenticated */
			if (authentication != null && authentication.isAuthenticated()
					&& authentication.getPrincipal() instanceof User) {
				final User user = (User) authentication.getPrincipal();
				loggedUser = buyerService.getUserLoginId(user.getUsername());
				if (loggedUser != null) {
					modelView.addObject(ApplicationConstants.LOGGEDUSER, loggedUser);
				}
				if (httpSession != null) {
					model.remove(ApplicationConstants.LOGGEDUSER);
					httpSession.setAttribute(ApplicationConstants.LOGGEDUSER, loggedUser);
				}
				/* Getting the roles/authorities assigned to the user */
				@SuppressWarnings("unchecked")
				final Collection<GrantedAuthority> authorities = (Collection<GrantedAuthority>) authentication
						.getAuthorities();
				final StringBuilder authorityInfo = new StringBuilder();
				for (GrantedAuthority authority : authorities) {
					authorityInfo.append(authority.getAuthority());
				}
				Utility.getLogger(getClass()).info("Module and Role: " + authorityInfo.toString());
			}

			if (null == loggedUser) {
				return new ModelAndView("redirect:/login");
			}
		} catch (final Exception e) {
			Utility.getLogger(getClass()).error("Exception in dashboardView", e);
		}
		return modelView;
	}
}
