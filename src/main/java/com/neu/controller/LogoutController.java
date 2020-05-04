package com.neu.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

import javax.persistence.Column;
import javax.servlet.http.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.neu.dao.AccountDao;
import com.neu.dao.DAO;
import com.neu.exception.UserException;
import com.neu.pojo.Account;



/**
 * Handles requests for the application home page.
 */



@Controller
@RequestMapping("/logout")

public class LogoutController {
	
	
	
	@Autowired
	AccountDao accountDao;
	
	
	
	@RequestMapping("/*")
	public String viewHome() {
		return "home2";
	}
	
	
	public ModelAndView setUp() throws  UserException {
		
		
//		User user = new User();
//		user.setFirstName("John");
//	
//		
//		Set<Phone> phoneNumber = new HashSet<Phone>();
//		phoneNumber.add(new Phone("6756473637"));
//		//phoneNumber.add(new Phone("9876546374"));
//		
//		user.setPhoneNumber(phoneNumber);
//		new Phone().setUser(user);
//		user = userDao.register(user);
//		
//		user = new User();
//		user.setFirstName("Dohn");
//		
//		phoneNumber = new HashSet<Phone>();
//		phoneNumber.add(new Phone("9824354756"));
//		//phoneNumber.add(new Phone("4253746576"));
//				
//		user.setPhoneNumber(phoneNumber);
//		new Phone().setUser(user);
//		user = userDao.register(user);
//		user = userDao.register(user);
		
		return new ModelAndView("home2");
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	protected ModelAndView handleRequestInternal(HttpServletRequest hsr, HttpServletResponse hsr1) throws Exception {
	
		hsr.getSession().invalidate();
		//DAO.getSession().close();
		ModelAndView mv= new ModelAndView("home2");
		return mv;
	}
	
	
}
