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
@RequestMapping("/showlistCriteria")
public class ShowListCriteriaController {
	
	
	@Autowired
	RequestDao reqDao;
	  @RequestMapping(value = "/showlistCriteria", method = RequestMethod.GET)
		protected ModelAndView handleRequestInternal(HttpServletRequest hsr, HttpServletResponse hsr1) throws Exception {
		
		  String id=  hsr.getParameter("id");
		  System.out.print("id:id"+id);
		  Long id1 = Long.parseLong(id);

		  Account acc = (Account) hsr.getSession().getAttribute("Account");
	//	  Long userid = acc.getUser1().getUser_Id();
		  ModelAndView modelAndView = new ModelAndView("ActionList");
		  List<Request> ActionList= new ArrayList<Request>();
			if(acc.getType().equals("Employee"))
			{
				  System.out.print("id:id"+id);
				 ActionList =   reqDao.RequestCriteriaonEmp(acc.getUser1(),id1);
				  modelAndView.addObject("type","hidden");
				  modelAndView.addObject("Approve","");
				  modelAndView.addObject("Reject","");
				//  modelAndView.addObject("Cancel","Cancel");
				  modelAndView.addObject("type1","hidden");
				//  modelAndView.addObject("typeShip","hidden");
				//  modelAndView.addObject("typeQas","hidden");
				  modelAndView.addObject("typeDeliver","hidden");
				//  modelAndView.addObject("typeCancel","radio");
				  
				  
				  
				  
				  
			}
			if(acc.getType().equals("Manager"))
			{
				
				 ActionList =   reqDao.RequestCriteriaonStatus("Intial",Long.parseLong(id));
				  modelAndView.addObject("type","radio");
				  modelAndView.addObject("Approve","Approve");
				  modelAndView.addObject("Reject","Reject");
				//  modelAndView.addObject("Cancel","");
				  modelAndView.addObject("type1","submit");
				//  modelAndView.addObject("typeShip","hidden");
				//  modelAndView.addObject("typeQas","submit");
				  modelAndView.addObject("typeDeliver","hidden");
			//	  modelAndView.addObject("typeCancel","hidden");
				  //pdf view button for quality response
			}
		 
			if(acc.getType().equals("QAS"))
			{
				 ActionList =   reqDao.RequestCriteriaonStatus("Inspect",Long.parseLong(id));
				  modelAndView.addObject("type","radio");
				  
				  modelAndView.addObject("Approve","Approve");
				//  modelAndView.addObject("Cancel","");
				  modelAndView.addObject("Reject","Reject");
				  modelAndView.addObject("type1","submit");
			//	  modelAndView.addObject("typeShip","hidden");
				//  modelAndView.addObject("typeQas","hidden");
				  modelAndView.addObject("typeDeliver","hidden");
			//	  modelAndView.addObject("typeCancel","hidden");
				  //navigate to quality values (approve + reject)views(form controller)
			}
			
			if(acc.getType().equals("Vendor"))
			{
				// ActionList =   reqDao.getProductUser("Order","Shipped","Delivered");
				 ActionList =   reqDao.RequestCriteriaonVendor(Long.parseLong(id),acc.getUser1());
				  modelAndView.addObject("type","radio");
				  modelAndView.addObject("Approve","Ship it");
				 // modelAndView.addObject("Cancel","");
				 // modelAndView.addObject("Reject","Reject");
			//	  modelAndView.addObject("Shipped","Shipped");
				  modelAndView.addObject("Delivered","Delivered");
				  modelAndView.addObject("type1","submit");
			//	  modelAndView.addObject("typeShip","radio");
				//  modelAndView.addObject("typeQas","hidden");
				 // modelAndView.addObject("typeCancel","hidden");

				  modelAndView.addObject("typeDeliver","radio");
				  
				 
				  // add delivered buttons
				  
			}
		 
		 
		  
			
			  
			 for(Request p : ActionList)
			 {   
				    byte[] bytes = toByteArray(p.getProduct().getImage());
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
	            BufferedImage bufferedImage=ImageIO.read(file);
	            ByteArrayOutputStream byteOutStream=new ByteArrayOutputStream();
	            ImageIO.write(bufferedImage, "png", byteOutStream);
	            return byteOutStream.toByteArray();
	         } catch (IOException e) {
	            e.printStackTrace();
	         }
	      }
	      return null;
	   }
	
	
	private byte[] toByteArray(Blob fromImageBlob) {
	    ByteArrayOutputStream baos = new ByteArrayOutputStream();
	    try {
	      return toByteArrayImpl(fromImageBlob, baos);
	    } catch (Exception e) {
	    }
	    return null;
	  }

	  private byte[] toByteArrayImpl(Blob fromImageBlob,
	      ByteArrayOutputStream baos) throws SQLException, IOException {
	    byte buf[] = new byte[4000];
	    int dataSize;
	    InputStream is = fromImageBlob.getBinaryStream();

	    try {
	      while((dataSize = is.read(buf)) != -1) {
	        baos.write(buf, 0, dataSize);
	      }
	    } finally {
	      if(is != null) {
	        is.close();
	      }
	    }
	    return baos.toByteArray();
	  }

}
