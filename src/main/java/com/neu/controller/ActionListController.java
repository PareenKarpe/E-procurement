package com.neu.controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.imageio.ImageIO;
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

import org.apache.commons.codec.binary.Base64;
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
import com.neu.dao.RequestDao;
import com.neu.pojo.Account;
import com.neu.pojo.Request;

@Controller
@RequestMapping("/ActionList1")
public class ActionListController {
	@Autowired
	RequestDao reqDao;
	  @RequestMapping(value = "/ActionList1", method = RequestMethod.GET)
		protected ModelAndView handleRequestInternal(HttpServletRequest hsr, HttpServletResponse hsr1) throws Exception {
		  String req;
		  ModelAndView modelAndView;
		  String flag = "1";
		  try {
		 
		  
		  req = hsr.getParameter("select1");
			Account acc = (Account) hsr.getSession().getAttribute("Account");
			

			String oldStatus= reqDao.getRequestStatus(Long.parseLong(req)).getStatus();
		
			
			
			System.out.print(hsr.getParameter("action"));
			
			
			if(hsr.getParameter("action").equals("Approve"))
			{
				System.out.print("approve");
				System.out.print("type "+acc.getType());
				System.out.print("old status "+oldStatus);
				
			if(acc.getType().equals("Manager") && oldStatus.equals("Initial"))
			{
				flag="0";
				reqDao.updateReq(Long.parseLong(req), "Inspect");
				  //pdf view button for quality response
			}
		 
			if(acc.getType().equals("QAS")&&oldStatus.equals("Inspect"))
			{
				flag="0";
				reqDao.updateReq(Long.parseLong(req), "Order");
				Request request = reqDao.getreq(Long.parseLong(req));
				SendEmail(hsr, hsr1, request);
			//	modelAndView = new ModelAndView("Qas");
		    	// return modelAndView;
				  //navigate to quality values (approve + reject)views(form controller)
			}
			
			if(acc.getType().equals("Vendor")&& oldStatus.equals("Order"))
			{
				flag="0";
				
				System.out.print("shipp");
				reqDao.updateReq(Long.parseLong(req), "Shipped");
				 
				  // add delivered buttons
				
				  
			}
		 
		 
			}
			else if((hsr.getParameter("action").equals("Reject")))
			{
				flag="0";
				System.out.print(hsr.getParameter("action"));
					reqDao.updateReq(Long.parseLong(req), "Cancel");
					  //pdf view button for quality response
			
			 //add for qas reject to qas
				
			}
		 
			else if((hsr.getParameter("action").equals("Delivered")) && acc.getType().equals("Vendor")&& oldStatus.equals("Shipped"))
			{
				flag="0";
				System.out.print("deliver");
				reqDao.updateReq(Long.parseLong(req), "Delivered");
			}
			else
			{
				
				if((hsr.getParameter("action").equals("Shipped"))&&acc.getType().equals("Vendor") && oldStatus.equals("Ordered"))
				{
					flag="0";
					
					reqDao.updateReq(Long.parseLong(req), "Shipped");
				}
				
			}
		 
		  
		  }	 
		  catch(Exception e)
		  {
			  modelAndView = new ModelAndView("ActionList");
			  modelAndView.addObject("message","Invalid data");
			  return modelAndView;
			  
		  }
		  
		  String s;
		// select1
		  modelAndView = new ModelAndView("ActionList2");
		  if(flag.equals("0")) {
			 s = "Request  "+req+ "  is now "+hsr.getParameter("action");
		  }
		  else
		  {
			   s = "Request  "+req+" cannot be changed.";
		  }
		  
		 
		 modelAndView.addObject("val",s);
	    	 return modelAndView;
	  }
	  public void SendEmail(HttpServletRequest hsr, HttpServletResponse hsr1,Request request) throws EmailException {
//			Email email = new SimpleEmail();
//			email.setHostName("smtp.google.com");
//			email.setSmtpPort(587);
//			//User your gmail username and password
//			email.setAuthenticator(new DefaultAuthenticator("karpe.p@husky.neu.edu", "Pari7777!"));
//			email.setSSLOnConnect(true);
//			email.setFrom("eproc@noreply.com");
//			email.setSubject("TestMail");
//			email.setMsg("Your application has been received");
//			email.addTo("pareen977@gmail.com");
//			email.send();
	    	
	    	String smtpHost = "smtp.googlemail.com";
	    	int smtpPort = 587;
	    	
	    	String sender = "no-reply@eproc.google.com";
	    	String recipent = request.getProduct().getUser1().getEmail_Id();
	    	String content="Request is raised";
	    	String subject = "Request is raised";
	    	
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
	    	textBodyPart.setText("Request is raised");
	    	
	    	outputStream = new ByteArrayOutputStream();
	    	writeDocumentPdf(outputStream,request);
	    	byte[] bytes = outputStream.toByteArray();
	    	
	    	DataSource dataSource = new ByteArrayDataSource(bytes, "application/pdf");
	    	MimeBodyPart pdfbody = new MimeBodyPart();
	    	pdfbody.setDataHandler(new DataHandler(dataSource));
	    	pdfbody.setFileName("RequestDetails.pdf");
	    	
	    	
	    	MimeMultipart mimeMultipart = new MimeMultipart();
	    	mimeMultipart.addBodyPart(textBodyPart);
	    	mimeMultipart.addBodyPart(pdfbody);
	    	
	    	InternetAddress Sender = new InternetAddress("no-reply@eproc.google.com");
	    	InternetAddress Recipient = new InternetAddress(request.getProduct().getUser1().getEmail_Id());
	    	
	    	
	    	
	    	MimeMessage mimeMes = new MimeMessage(session);
	    	mimeMes.setSender(Sender);
	    	mimeMes.setSubject(subject);
	    	mimeMes.setRecipient(Message.RecipientType.TO,Recipient);
	    	mimeMes.setContent(mimeMultipart);
	    	
	    	
	    	
	    	
	    	Transport.send(mimeMes);
	    	System.out.print("email sent from"+sender+"to"+recipent);
	    	
	    	}
	    	catch(Exception e)
	    	{
	    		e.printStackTrace();
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
	    			
	    	
	    	public void writeDocumentPdf(OutputStream outputstream,Request request)throws Exception
	    	{
	    		Document d = new Document();
	    	PdfWriter.getInstance(d,outputstream);
	    		
	    		d.open();
	    		Paragraph paragraph = new Paragraph();
	    		
	    		paragraph.add(new Chunk("Request raised by user------- "+request.getUser1().getFirst_name()));
	    		paragraph.add("\n\n");
	    		paragraph.add(new Chunk("For the product------"+request.getProduct().getName()));
	    		paragraph.add("\n\n");
	    		paragraph.add(new Chunk("Delivery address------"+request.getUser1().getStreet_Name()+","+request.getUser1().getState()+","+request.getUser1().getPostal()));
	    		paragraph.add("\n\n");
	    		paragraph.add(new Chunk("Price------ "+request.getTotalprice()));
	    		paragraph.add("\n\n");
	    		paragraph.add(new Chunk("Date of request------ "+request.getReq_Date()));
	    		paragraph.add("\n\n");
	    		d.add(paragraph);
	    		d.close();
	    	}
}
