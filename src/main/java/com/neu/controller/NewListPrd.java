package com.neu.controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.text.StyledEditorKit.BoldAction;

import org.apache.commons.codec.binary.Base64;
import org.hibernate.engine.jdbc.BlobProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.neu.dao.ProductDao;
import com.neu.pojo.Account;
import com.neu.pojo.Product;
import com.neu.pojo.Request;

@Controller
@RequestMapping("/Listnewprd")
public class NewListPrd {
	
	@Autowired
	ProductDao productDao;
	
	@RequestMapping(value = "/Listnewprd", method = RequestMethod.GET)
	protected ModelAndView handleRequestInternal(HttpServletRequest hsr, HttpServletResponse hsr1) throws Exception {
	
		
			//display shopping list on login
		//	 List<Product> ProductList = productDao.getProduct() ;
		Account acc = (Account) hsr.getSession().getAttribute("Account");
		
		
		List<Product> ProductList = productDao.getProductUser(acc.getUser1().getUser_Id());
		for(Product p : ProductList)
		 {   
			    byte[] bytes = toBArray(p.getImage());
			    byte[] encodeBase64 = Base64.encodeBase64(bytes);
	            String base64Encoded = new String(encodeBase64, "UTF-8");
	            p.setFile1(base64Encoded);
		 }
		 
			 
			 
			 ModelAndView modelAndView = new ModelAndView("ProductListVendor");
			    modelAndView.addObject("ProductList", ProductList);
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
