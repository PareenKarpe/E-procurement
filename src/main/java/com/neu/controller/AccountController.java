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
import com.neu.exception.UserException;
import com.neu.pojo.Account;



/**
 * Handles requests for the application home page.
 */



@Controller
@RequestMapping("/*")

public class AccountController {
	
	
	
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
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	protected ModelAndView handleRequestInternal(HttpServletRequest hsr, HttpServletResponse hsr1) throws Exception {
	
	String user_name = hsr.getParameter("User_Name");
	
	
	String password = hsr.getParameter("Password");	
	hsr.getSession().setAttribute("Password",password);
	
	
	if(user_name.length() == 0 || password.length() == 0)
	{
		
		ModelAndView modelAndView = new ModelAndView("home2");
	    modelAndView.addObject("message", "All details are mandatory");
	    return modelAndView;

	}
	
	
	
		Account acc = accountDao.get(user_name, password);
		
		hsr.getSession().setAttribute("Account", acc);
	
		try {
		if(acc.getUser_Name().isEmpty() == true)
		{
			ModelAndView modelAndView = new ModelAndView("home2");
		    modelAndView.addObject("message", "Invalid details");
		    return modelAndView;
		}
		else
		{
			HttpSession s;
			 s = hsr.getSession();
			 s.setAttribute("user_name",acc.getUser_Name());
			 s.setAttribute("type",acc.getType());
				
			
			//valid details now check the type
			ModelAndView modelAndView = new ModelAndView("Dashboard");
			 modelAndView.addObject("value1", acc.getUser_Name());
			if(acc.getType().equals("Employee"))
			{
				//modelAndView.addObject("message", "iam in");
				 modelAndView.addObject("Vis_Employee", "submit");
				 modelAndView.addObject("Vis_Manager", "hidden");
				 modelAndView.addObject("Vis_Qas", "hidden");
				 modelAndView.addObject("Vis_Vendor", "hidden");
			}
			if(acc.getType().equals("Manager"))
			{
				 modelAndView.addObject("Vis_Employee", "hidden");
				 modelAndView.addObject("Vis_Manager","submit");
				 modelAndView.addObject("Vis_Qas", "hidden");
				 modelAndView.addObject("Vis_Vendor", "hidden");
			}
			
			if(acc.getType().equals("QAS"))
			{
				 modelAndView.addObject("Vis_Employee", "hidden");
				 modelAndView.addObject("Vis_Manager", "hidden");
				 modelAndView.addObject("Vis_Qas", "submit");
				 modelAndView.addObject("Vis_Vendor", "hidden");
			}
			if(acc.getType().equals("Vendor"))
			{
				 modelAndView.addObject("Vis_Employee", "hidden");
				 modelAndView.addObject("Vis_Manager", "hidden");
				 modelAndView.addObject("Vis_Qas", "hidden");
				 modelAndView.addObject("Vis_Vendor", "submit");
			}
			
			if(acc.getType().equals("Admin"))
			{
				ModelAndView modelAndView1 = new ModelAndView("home");
			   // modelAndView.addObject("message", "Invalid details");
			    return modelAndView1;
			}
			
		
			
			
			
			
	return modelAndView;
	}
		}
		catch(Exception e)
		{
			
			
			ModelAndView modelAndView = new ModelAndView("home2");
		    modelAndView.addObject("message", "Invalid details");
		    return modelAndView;
		}
		
		
	}
	
	
}
