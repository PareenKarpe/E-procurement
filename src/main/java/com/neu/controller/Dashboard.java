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

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.neu.dao.ProductDao;
import com.neu.pojo.Product;
//display of shopping list for emp and man
@Controller
@RequestMapping("/dashboard")
public class Dashboard {
	@Autowired
	ProductDao productDao;
	@RequestMapping(value = "/dashboard", method = RequestMethod.GET)
	protected ModelAndView handleRequestInternal(HttpServletRequest hsr, HttpServletResponse hsr1) throws Exception {
		
	//hsr.getParameter("hidden").
		
		String user = (String) hsr.getSession().getAttribute("user_name");
		String type = (String) hsr.getSession().getAttribute("type");
		
		
		//display shopping list on login
		 List<Product> ProductList = productDao.getProduct() ;
		 for(Product p : ProductList)
		 {
			    byte[] bytes = toByteArray(p.getImage());
			    byte[] encodeBase64 = Base64.encodeBase64(bytes);
	            String base64Encoded = new String(encodeBase64, "UTF-8");
	            p.setFile1(base64Encoded);
		 }
	 
		 
		 
		 ModelAndView modelAndView = new ModelAndView("ProductList");
		    modelAndView.addObject("ProductList", ProductList);
			    return modelAndView;
		 
		
		
		
	}
	
	public static byte[] getImage() {
	      File file =new File("C:\\Users\\paree\\Desktop\\icon.PNG");
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
	
	

