package com.neu.controller;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.neu.pojo.Product;

@Controller
@RequestMapping("/dashNewProduct")
public class DashBoardNewProduct {

	
	
	@RequestMapping(value = "/dashNewProduct", method = RequestMethod.GET)
	protected ModelAndView handleRequestInternal(HttpServletRequest hsr, HttpServletResponse hsr1) throws Exception {
		
	
		 
		 
		 ModelAndView modelAndView = new ModelAndView("NewProduct");
		//   modelAndView.addObject("val", "Request is raised");
			    return modelAndView;
		 
		
		
		
	}
	
}
