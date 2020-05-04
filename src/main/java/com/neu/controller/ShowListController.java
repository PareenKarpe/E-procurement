package com.neu.controller;


import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;

import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.hibernate.engine.jdbc.BlobProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


import com.neu.dao.*;
import com.neu.exception.UserException;
import com.neu.pojo.*;

@Controller
@RequestMapping("/RequestList")
public class ShowListController {
	
	
	@Autowired
	RequestDao reqDao;
	  @RequestMapping(value = "/RequestList", method = RequestMethod.GET)
		protected ModelAndView handleRequestInternal(HttpServletRequest hsr, HttpServletResponse hsr1) throws Exception {
		

		  Account acc = (Account) hsr.getSession().getAttribute("Account");
		  Long userid = acc.getUser1().getUser_Id();
		  ModelAndView modelAndView = new ModelAndView("ActionList");
		  List<Request> ActionList= new ArrayList<Request>();
			if(acc.getType().equals("Employee"))
			{
				
				 ActionList =   reqDao.getProductEmp(userid);
				 modelAndView.addObject("typeApp","");
				 modelAndView.addObject("Approve","");
		  
			
				 
				 modelAndView.addObject("typeDel","hidden");
				 modelAndView.addObject("Delivered","");
				 modelAndView.addObject("typeReject","hidden");
				 modelAndView.addObject("Reject","");
				 
				 modelAndView.addObject("typeSub","hidden");
				  
				  
				  
				  
			}
			if(acc.getType().equals("Manager"))
			{
				
				 ActionList =   reqDao.getAllreq();
				 modelAndView.addObject("typeApp","radio");
				 modelAndView.addObject("Approve","Approve");
		  
			
				 
				 modelAndView.addObject("typeDel","hidden");
				 modelAndView.addObject("Delivered","");
				 modelAndView.addObject("typeReject","radio");
				 modelAndView.addObject("Reject","Reject");
				 
				 modelAndView.addObject("typeSub","submit");
			}
		 
			if(acc.getType().equals("QAS"))
			{
				 ActionList =   reqDao.getProductUser("Inspect","","");
				 modelAndView.addObject("typeApp","radio");
				 modelAndView.addObject("Approve","Approve");
		  
			
				 
				 modelAndView.addObject("typeDel","hidden");
				 modelAndView.addObject("Delivered","");
				 modelAndView.addObject("typeReject","radio");
				 modelAndView.addObject("Reject","Reject");
				 
				 modelAndView.addObject("typeSub","submit");
			//	  modelAndView.addObject("typeCancel","hidden");
				  //navigate to quality values (approve + reject)views(form controller)
			}
			
			if(acc.getType().equals("Vendor"))
			{
				System.out.print("hh");
				// ActionList =   reqDao.getProductUser("Order","Shipped","Delivered");
				 ActionList =   reqDao.getReg_forVendor(acc.getUser1());
//				  modelAndView.addObject("type","radio");
//				  modelAndView.addObject("type3","radio");
//				  modelAndView.addObject("type2","hidden");
//				  modelAndView.addObject("Approve","Ship it");
//				  modelAndView.addObject("Reject", "");
//				  modelAndView.addObject("Delivered", "Delivered");
//				 // modelAndView.addObject("Cancel","");
//				 // modelAndView.addObject("Reject","Reject");
//			//	  modelAndView.addObject("Shipped","Shipped");
//				///  modelAndView.addObject("Delivered","Delivered");
//				  modelAndView.addObject("type1","submit");
//			//	  modelAndView.addObject("typeShip","radio");
//				//  modelAndView.addObject("typeQas","hidden");
//				 // modelAndView.addObject("typeCancel","hidden");
//
//				  modelAndView.addObject("typeDeliver","radio");
				
				  
						 modelAndView.addObject("typeApp","radio");
						 modelAndView.addObject("Approve","Ship it");
				  
					
						 
						 modelAndView.addObject("typeDel","radio");
						 modelAndView.addObject("Delivered","Delivered");
						 modelAndView.addObject("typeReject","radio");
						 modelAndView.addObject("Reject","Reject");
						 
						 modelAndView.addObject("typeSub","submit");
						// modelAndView.addObject("Delivered","Delivered");
				  
				 
				  // add delivered buttons
				  
			}
		 
		 
		  
			
			  
			 for(Request p : ActionList)
			 {   
				    byte[] bytes = toBArray(p.getProduct().getImage());
				    byte[] encodeBase64 = Base64.encodeBase64(bytes);
		            String base64Encoded = new String(encodeBase64, "UTF-8");
		            p.getProduct().setFile1(base64Encoded);
			 }
		  modelAndView.addObject("ActionList",ActionList);
	    	 return modelAndView;
		  
	  }
	  
	  
	 
	  
	  public static byte[] getImage(String path) {
	      File file =new File(path);
	      if(file.exists()){
	         try {
	            BufferedImage bufferedim=ImageIO.read(file);
	            ByteArrayOutputStream byteout=new ByteArrayOutputStream();
	            ImageIO.write(bufferedim, "png", byteout);
	            return byteout.toByteArray();
	         } catch (IOException ex) {
	            ex.printStackTrace();
	         }
	      }
	      return null;
	   }
	
	private byte[] toBArray(Blob fromim) {
	    ByteArrayOutputStream bast = new ByteArrayOutputStream();
	    try {
	      return toByteIm(fromim, bast);
	    } catch (Exception ex) {
	    }
	    return null;
	  }

	  private byte[] toByteIm(Blob fromim,
	      ByteArrayOutputStream bast) throws SQLException, IOException {
	    byte buffer[] = new byte[4000];
	    int dataSize;
	    InputStream ist = fromim.getBinaryStream();

	    try {
	      while((dataSize = ist.read(buffer)) != -1) {
	    	  bast.write(buffer, 0, dataSize);
	      }
	    } finally {
	      if(ist != null) {
	        ist.close();
	      }
	    }
	    return bast.toByteArray();
	  }

}
