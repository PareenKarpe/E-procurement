package com.neu.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;
import java.util.UUID;

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

import com.neu.dao.*;
import com.neu.*;
import com.neu.pojo.*;
import com.neu.exception.*;



/**
 * Handles requests for the application home page.
 */



@Controller
@RequestMapping("/create")
public class UserController {
	
	
	
	@Autowired
	UserDao userDao;
	
	
	
	@RequestMapping("/")
	public String viewHome() {
		return "home";
	}
	
//	
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
		
		return new ModelAndView("home");
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	protected ModelAndView handleRequestInternal(HttpServletRequest hsr, HttpServletResponse hsr1) throws Exception {
	
		
		 String First_Name = hsr.getParameter("First_Name");
		
		
		 String Last_Name= hsr.getParameter("Last_Name");
		
	 String Phone_Number = hsr.getParameter("Phone_Number");
	 
	
		 String Email_Id = hsr.getParameter("Email_Id");
		 
		 String regex = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\\\.[A-Z]{2,6}$";
		 Boolean b = Email_Id.matches(regex);
		
		 
		
		 String Apartment_Name = hsr.getParameter("Apartment_Name");
		
		String Street_Name = hsr.getParameter("Street_Name");
		
		 String Unit_Number = hsr.getParameter("Unit_Number");
		
	 String City = hsr.getParameter("City");
		
		 String State = hsr.getParameter("State");
		
		 String Postal = hsr.getParameter("Postal");	
		
		 String Country = hsr.getParameter("Country");
		 String Type = hsr.getParameter("Type");

		
		 String Date_Birth = hsr.getParameter("Date_Birth");
		 String User_Name = hsr.getParameter("User_Name");
		 
	String Password = hsr.getParameter("Password");
		
	if(First_Name.length()<1||Last_Name.length()<1||Phone_Number.length()<11||Email_Id.length()<1||Apartment_Name.length()<1||Street_Name.length()<1||Unit_Number.length()<1||City.length()<1||State.length()<1||Postal.length()<1||Country.length()<1||Type.length()<1||Date_Birth.length()<1||User_Name.length()<1||Password.length()<1)
	{
		ModelAndView modelAndView = new ModelAndView("home");
	    modelAndView.addObject("msg", "All details are mandatory ");
	    return modelAndView;
	}
	
	
	 if(Phone_Number.length()<11 || Phone_Number.matches("[0-9]+")==false)
	 {
		 
		 ModelAndView modelAndView = new ModelAndView("home");
		    modelAndView.addObject("msg", "Enter valid phone ");
		    return modelAndView;
	 }
	 if(b==false)
	 {
		 
		 ModelAndView modelAndView = new ModelAndView("home");
		    modelAndView.addObject("msg", "Enter valid email id");
		    return modelAndView;
	 }
	//valid username
	int m = userDao.userExist(User_Name);
	if(m>0)
	{
		ModelAndView modelAndView = new ModelAndView("home");
	    modelAndView.addObject("userValid", "Choose unique user name");
	    return modelAndView;
	}
	
	
	
	
		User user = new User();
		
		
		
		user.setCity(City);
		user.setApartment_Name(Apartment_Name);
		user.setCountry(Country);
		// user.setDate_Birth(Date_Birth);
		 user.setEmail_Id(Email_Id);
		 user.setFirst_name(First_Name);
		 user.setLast_name(Last_Name);
		 user.setPhone_Number(Phone_Number);
		 user.setPostal(Postal);
		 user.setState(State);
		 user.setStreet_Name(Street_Name);
		 user.setUnit_Number(Unit_Number);
		 
		 
		 Set<Account> account = new HashSet<Account>();
		 account.add(new Account(User_Name,Password,user,Type,UUID.randomUUID().toString()));
		user.setPhoneNumber(account);
		 
		 new Account().setUser1(user);
		 
	//	 user.setAccount(account);
		// account.setUser(user);
		 user = userDao.register(user);
		
		
		
		
		
		
		
		
		
		return new ModelAndView("NewProduct");
	}
	
	
}