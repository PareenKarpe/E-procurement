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

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.neu.dao.ProductDao;
import com.neu.pojo.*;

@Controller
@RequestMapping("/EditPrd")
public class EditPrd {
	
	@Autowired
	ProductDao productDao;
	
	@RequestMapping(value = "/EditPrd", method = RequestMethod.GET)
	protected ModelAndView handleRequestInternal(HttpServletRequest hsr, HttpServletResponse hsr1) throws Exception {
		 ModelAndView modelAndView;
		 String prdId ;
		try {
	 prdId =	hsr.getParameter("select1");
	
	Product p = productDao.getProduct(prdId);
	hsr.getSession().setAttribute("PrdObject", p);
	
	  byte[] bytes = toByteArray(p.getImage());
	    byte[] encodeBase64 = Base64.encodeBase64(bytes);
      String base64Encoded = new String(encodeBase64, "UTF-8");
      p.setFile1(base64Encoded);
	  modelAndView = new ModelAndView("Viewprd");
	 modelAndView.addObject("price",p.getPrice());
	 modelAndView.addObject("name",p.getName());
	 modelAndView.addObject("desc",p.getDescription());
	 modelAndView.addObject("image",p.getFile1());
	 hsr.getSession().setAttribute("pid", prdId);
		}
		catch(Exception e)
		{
			 modelAndView = new ModelAndView("ProductListVendor");
		    modelAndView.addObject("message", "Invalid details");
		    return modelAndView;
		}
	 modelAndView.addObject("pid",prdId);
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
