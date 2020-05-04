package com.neu.controller;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.neu.pojo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.neu.dao.AccountDao;

@Controller
@RequestMapping("/newPassword")
public class ResetpasswordController {
	
	@Autowired
	AccountDao accountDao;
	 @RequestMapping(value = "/newPassword", method = RequestMethod.GET)
	protected ModelAndView handleRequestInternal(HttpServletRequest hsr, HttpServletResponse hsr1) throws Exception {
	
	String username= hsr.getParameter("User_Name");
	String password = hsr.getParameter("Password");
	String token = hsr.getParameter("token");
		
	int i = accountDao.validToken(username, password, token);
	try {
		if(i<1)
		{
			ModelAndView modelAndView = new ModelAndView("ResetPassword");
		    modelAndView.addObject("message", "Invalid details");
		    return modelAndView;
		}
	}
	catch(Exception e) {
		
		ModelAndView modelAndView = new ModelAndView("ResetPassword");
	    modelAndView.addObject("message", "Invalid details");
	    return modelAndView;
		
	}
	accountDao.updateToken(accountDao.get(username));
		ModelAndView m = new ModelAndView("ResetPassword");
		m.addObject("message", "Reset done.Try login using new passowrd");
		 return m;
	
	}
}
