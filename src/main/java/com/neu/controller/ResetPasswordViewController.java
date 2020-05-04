package com.neu.controller;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.util.Properties;
import java.util.UUID;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.mail.ByteArrayDataSource;
import org.apache.commons.mail.EmailException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.neu.dao.AccountDao;
import com.neu.dao.ProductDao;
import com.neu.pojo.*;

@Controller
@RequestMapping("/resetPassword")
public class ResetPasswordViewController {
	
	@Autowired
	AccountDao accountDao;
	
	@Autowired
	ProductDao productDao;
	 @RequestMapping(value = "/resetPassword", method = RequestMethod.GET)
		protected ModelAndView handleRequestInternal(HttpServletRequest hsr, HttpServletResponse hsr1) throws Exception {
	
		String user_name = hsr.getParameter("User_Name1");
		 System.out.print("userNAme"+user_name);
		 Account acc = accountDao.get(user_name);
		 
		 try {
				if(acc.getUser_Name().isEmpty() == true)
				{
					ModelAndView modelAndView = new ModelAndView("home2");
				    modelAndView.addObject("message", "Invalid username");
				    return modelAndView;
				}
			}
			catch(Exception e) {
				
				ModelAndView modelAndView = new ModelAndView("home2");
			    modelAndView.addObject("message", "Invalid username");
			    return modelAndView;
				
			}
		 String rec = acc.getUser1().getEmail_Id();
		
		 SendEmailReset(rec,acc,hsr,hsr1);
		 
		 
	 ModelAndView m = new ModelAndView("ResetPassword");
	 return m;
	 
	 
	 }
	 
	 
	 
	 public void SendEmailReset(String rec,Account acc,HttpServletRequest hsr, HttpServletResponse hsr1)throws EmailException
	 {
			String smtpHost = "smtp.googlemail.com";
	    	int smtpPort = 587;
	    	
	    	String sender = "reset-pass@eproc.google.com";
	    	//String recipent = "pareen977@gmail.com";
	    	String content="dummmy content";
	    	String subject = "dummy subject";
	    	
	    	hsr1.setContentType("application/pdf");
	    	
	    	Properties p = new Properties();
	    	p.put("mail.smtp.host", smtpHost);
	    	p.put("mail.smtp.port", smtpPort);
	    	p.put("mail.smtp.port", smtpPort);
	    	p.put("mail.smtp.starttls.enable","true");
	    	p.put("mail.smtp.socketFactory.port","587");
	    	p.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
	    	p.put("mail.smtp.auth","true");
	    	
	    	Session session = Session.getDefaultInstance(p,new javax.mail.Authenticator() {
	    		
	    		protected PasswordAuthentication getPasswordAuthentication()
	    		{
	    			return new PasswordAuthentication("webtools667@gmail.com", "Webtools!");
	    		}
	    	}
	    	);
	    	
	    	ByteArrayOutputStream outputStream = null;
	    
	    	
	    	try {
	    	MimeBodyPart textBodyPart = new MimeBodyPart();
	    	textBodyPart.setText("Password Reset Token");
	    	
	    	outputStream = new ByteArrayOutputStream();
	    	writePdf(outputStream,acc);
	    	byte[] bytes = outputStream.toByteArray();
	    	
	    	DataSource dataSource = new ByteArrayDataSource(bytes, "application/pdf");
	    	MimeBodyPart pdfBodyPart = new MimeBodyPart();
	    	pdfBodyPart.setDataHandler(new DataHandler(dataSource));
	    	pdfBodyPart.setFileName("ResetPassword.pdf");
	    	
	    	
	    	MimeMultipart mimeMultipart = new MimeMultipart();
	    	mimeMultipart.addBodyPart(textBodyPart);
	    	mimeMultipart.addBodyPart(pdfBodyPart);
	    	
	    	InternetAddress iaSender = new InternetAddress("reset-pass@eproc.google.com");
	    	InternetAddress iaRecipient = new InternetAddress(rec);
	    	
	    	
	    	
	    	MimeMessage mimeMessage = new MimeMessage(session);
	    	mimeMessage.setSender(iaSender);
	    	mimeMessage.setSubject(subject);
	    	mimeMessage.setRecipient(Message.RecipientType.TO,iaRecipient);
	    	mimeMessage.setContent(mimeMultipart);
	    	
	    	
	    	
	    	
	    	Transport.send(mimeMessage);
	    	System.out.print("sent from"+sender+"to"+rec);
	    	
	    	}
	    	catch(Exception ex)
	    	{
	    		ex.printStackTrace();
	    	}
	    	finally {
	    		
	    		if(null!=outputStream)
	    		{
	    			try {
	    				outputStream.close();
	    				outputStream=null;
	    			}
	    			catch(Exception ex)
	    			{
	    				
	    			}
	    			
	    			
	    		}
	    		
	    	}
	    	
	    
		 
		 
		 
	 }
	 
	 public void writePdf(OutputStream outputstream,Account acc)throws Exception
 	{
		String token =  acc.getReset();
 		Document d = new Document();
 	PdfWriter.getInstance(d,outputstream);
 		
 		d.open();
 		Paragraph paragraph = new Paragraph();
 		
 		paragraph.add(new Chunk("Reset your password by entering token in the reset password window and update your password"));
 		paragraph.add(new Chunk("Valid for only one attempt"));
 		paragraph.add(new Chunk("Token is : "+token));
 		d.add(paragraph);
 		d.close();
 	}
	
	
}
