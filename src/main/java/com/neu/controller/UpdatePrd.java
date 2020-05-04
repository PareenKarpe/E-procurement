package com.neu.controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.engine.jdbc.BlobProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.neu.dao.ProductDao;
import com.neu.pojo.Account;
import com.neu.pojo.Product;
@Controller
@RequestMapping("/UpdatePrd")
public class UpdatePrd {
	@Autowired
	ProductDao productDao;
	@RequestMapping(value = "/UpdatePrd", method = RequestMethod.GET)
	protected ModelAndView handleRequestInternal(HttpServletRequest hsr, HttpServletResponse hsr1) throws Exception {
		 ModelAndView modelAndView ;
		
		
		 
		 try {
			
//			if(hsr.getParameter("price").length()<1||hsr.getParameter("description").length()<1||hsr.getParameter("name").length()<1)
//			{
//				modelAndView = new ModelAndView("Viewprd");
//			    modelAndView.addObject("message","Invalid details");
//				    return modelAndView;	
//			}
		int price = Integer.parseInt(hsr.getParameter("price"));
	String des = hsr.getParameter("description");
String name = hsr.getParameter("name")	;		
	Product product = new Product();
	 Long pid = Long.parseLong(hsr.getParameter("id"));

	product.setPost_ID(pid);
		product.setName(name);
		product.setDescription(des);
		product.setPrice(price);
		if(hsr.getParameter("pic").length()<1)
		{
			// product.setImage(BlobProxy.generateProxy(getImage(hsr.getParameter("pic"))));	
			Product p= (Product)hsr.getSession().getAttribute("PrdObject");
		//	p.getImage()
			product.setImage(p.getImage());
			 System.out.print("you are here");
		}
		
		else
		{
			 //product.setImage(product.getImage()); byte to bolb
			
			product.setImage(BlobProxy.generateProxy(getImage(hsr.getParameter("pic"))));	
			 product.setFile1(hsr.getParameter("pic"));
		}
		
		
		product.setFile1(hsr.getParameter("pic"));
		Account acc = (Account) hsr.getSession().getAttribute("Account");
		product.setUser1(acc.getUser1());
		
	
	//	 String pid = hsr.getParameter("id");
		 
		//Blob im =  BlobProxy.generateProxy(getImage(hsr.getParameter("pic")));
		
		 
		//product.setName("hkhs");
		
	//	product.setFile1(hsr.getParameter("pic"));
		
		 productDao.Update1(product);
		}
		catch(Exception e)
		{
			modelAndView = new ModelAndView("Viewprd");
		    modelAndView.addObject("message","Invalid details");
			    return modelAndView;
		}
		
		
		  modelAndView = new ModelAndView("add");
		    //modelAndView.addObject("ProductList", product);
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

