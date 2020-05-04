package com.neu.controller;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.imageio.ImageIO;

import org.apache.commons.codec.binary.Base64;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.engine.jdbc.BlobProxy;
import org.hibernate.HibernateException;
import java.util.*;
import org.hibernate.Query;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.neu.dao.ProductDao;
import com.neu.pojo.*;

@Controller
@RequestMapping("/createProduct")
public class ProductController {
	@Autowired
	ProductDao productDao;
	
	@RequestMapping(method = RequestMethod.GET)
	protected ModelAndView handleRequestInternal(HttpServletRequest hsr, HttpServletResponse hsr1) throws Exception {
		 ModelAndView modelAndView;
		try {
		
		
		int price = Integer.parseInt(hsr.getParameter("Price"));
		String des = hsr.getParameter("Description");
	String name = hsr.getParameter("Product_Name")	;
	
			Product product = new Product();
			product.setName(name);
			product.setDescription(des);
			product.setPrice(price);
			Account acc = (Account) hsr.getSession().getAttribute("Account");
			product.setUser1(acc.getUser1());
		
			SimpleDateFormat formatter = new SimpleDateFormat(
				      "dd/MM/yyyy");
	    	Date date = new Date();
	    	
	    	product.setPrd_Date(new Date(formatter.format(date)));
		
		 product.setImage(BlobProxy.generateProxy(getImage(hsr.getParameter("pic"))));
		//product.setName("hkhs");
		
		
		 productDao.saveProduct(product);
	}
	catch(Exception e)
	{
		  modelAndView = new ModelAndView("NewProduct");
		    modelAndView.addObject("message", "enter valid data");
			    return modelAndView;
		 
	}
		//display shopping list on login
		 List<Product> ProductList = productDao.getProduct() ;

		  
		 for(Product p : ProductList)
		 {   
			    byte[] bytes = toBArray(p.getImage());
			    byte[] encodeBase64 = Base64.encodeBase64(bytes);
	            String base64Encoded = new String(encodeBase64, "UTF-8");
	            p.setFile1(base64Encoded);
		 }
		 
		 
		  modelAndView = new ModelAndView("add");
		  //  modelAndView.addObject("ProductList", ProductList);
			    return modelAndView;
		 
		 
		 
		
		//return new ModelAndView("ProductList");
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
