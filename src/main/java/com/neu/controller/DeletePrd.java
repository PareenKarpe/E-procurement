package com.neu.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.neu.dao.ProductDao;
import com.neu.pojo.Product;


@Controller
@RequestMapping("/DelPrd")
public class DeletePrd {
	
	
	
	@Autowired
	ProductDao productDao;
	@RequestMapping(value = "/DelPrd", method = RequestMethod.GET)
	protected ModelAndView handleRequestInternal(HttpServletRequest hsr, HttpServletResponse hsr1) throws Exception {
Product product = new Product();
String s = (String)hsr.getSession().getAttribute("pid");
	
		productDao.Delete(Long.parseLong(s));
		
	 ModelAndView modelAndView = new ModelAndView("add2");
	modelAndView.addObject("nu", hsr.getSession().getAttribute("pid"));
	
		    return modelAndView;
		
		
	}
}
