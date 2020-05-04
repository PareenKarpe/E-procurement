package com.neu.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

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
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.mail.ByteArrayDataSource;
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

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.neu.dao.*;
import com.neu.exception.UserException;
import com.neu.pojo.*;

@Controller
@RequestMapping("/ListProducts")
public class ListController {
//place request
	@Autowired
	ProductDao productDao;
	
	@Autowired
     UserDao userDao;
	
	@Autowired
    RequestDao requestDao;
	
//	@RequestMapping(value = "/ListProducts", method = RequestMethod.GET)
//	protected void SendRequest(@RequestParam(value = "clicked_id") int id)throws Exception 
//	
//	{
//		System.out.print("pareen");
//		Product product =new Product();
//		product.setName("hkhs");
//		
//		
//		 productDao.saveProduct(product);
//		
//		
//		
//	}
    @RequestMapping(value = "/ListProducts", method = RequestMethod.GET)
	protected ModelAndView handleRequestInternal(HttpServletRequest hsr, HttpServletResponse hsr1) throws Exception {
	
    	//place request
    	try {
    	String Pid = hsr.getParameter("select1");
    	
    	Product product = productDao.getProduct(Pid);
    	
    	
    	Account acc = (Account) hsr.getSession().getAttribute("Account");
    	
    	Request  request = new Request();
    	request.setProduct(product);
    	request.setUser1(acc.getUser1());
    	request.setTotalprice(product.getPrice());
    	request.setStatus("Initial");
    	
    	DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    	Date date = new Date();
    	request.setReq_Date(date);
    	try {
    	requestDao.sendReq(request);
    	SendEmail(hsr,hsr1,request);
    	}
    	catch(Exception e)
    	{
    		throw new EmailException("Exception while creating email: " + e.getMessage());	
    	}
    	}
    	
    	catch(Exception e)
    	{
    		 ModelAndView modelAndView = new ModelAndView("ProductList");
        	 modelAndView.addObject("mess","invalid data");
        	
        	 return modelAndView;
    	}
    	 ModelAndView modelAndView = new ModelAndView("ActionList2");
    	 modelAndView.addObject("val","Request is sent");
    	
    	 return modelAndView;
    	
    	
		/*
		 * String Valk = hsr.getParameter("select1");
		 * 
		 * User u = new User(); u.setEmail_Id(Valk); // // userDao.register(u); return
		 * new ModelAndView("add");
		 */
    // @RequestParam(value = "id") int id
		  // RequestDispatcher requestDispatcher = null;
		
		//ModelAndView modelAndView = null;
		//User u = new User();
		//u.setEmail_Id("newpareen");
//		
//		
	// userDao.register(u);
		
	 
		 
		
//	 ModelAndView modelAndView = new ModelAndView("ProductList");
//	    modelAndView.addObject("ProductList", ProductList);
//		    return modelAndView;

	}
    public void SendEmail(HttpServletRequest hsr, HttpServletResponse hsr1,Request request) throws EmailException {
//		Email email = new SimpleEmail();
//		email.setHostName("smtp.google.com");
//		email.setSmtpPort(587);
//		//User your gmail username and password
//		email.setAuthenticator(new DefaultAuthenticator("karpe.p@husky.neu.edu", "Pari7777!"));
//		email.setSSLOnConnect(true);
//		email.setFrom("eproc@noreply.com");
//		email.setSubject("TestMail");
//		email.setMsg("Your application has been received");
//		email.addTo("pareen977@gmail.com");
//		email.send();
    	
    	String smtpHost = "smtp.googlemail.com";
    	int smtpPort = 587;
    	
    	String sender = "no-reply@eproc.google.com";
    	String recipent = request.getUser1().getEmail_Id();
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
    	InternetAddress Recipient = new InternetAddress(request.getUser1().getEmail_Id());
    	
    	
    	
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
    		
    		paragraph.add(new Chunk("Request raised by you------- "));
    		paragraph.add("\n\n");
    		paragraph.add(new Chunk("For the product------"+request.getProduct().getName()));
    		paragraph.add("\n\n");
    		paragraph.add(new Chunk("For the vendor------"+request.getProduct().getUser1().getFirst_name()));
    		paragraph.add("\n\n");
    		paragraph.add(new Chunk("Price------ "+request.getTotalprice()));
    		paragraph.add("\n\n");
    		paragraph.add(new Chunk("Date of request------ "+request.getReq_Date()));
    		paragraph.add("\n\n");
    		d.add(paragraph);
    		d.close();
    	}
	
	
    
    
	
	/*
	 * private byte[] toByteArray(Blob fromImageBlob) { ByteArrayOutputStream baos =
	 * new ByteArrayOutputStream(); try { return toByteArrayImpl(fromImageBlob,
	 * baos); } catch (Exception e) { } return null; }
	 * 
	 * private byte[] toByteArrayImpl(Blob fromImageBlob, ByteArrayOutputStream
	 * baos) throws SQLException, IOException { byte buf[] = new byte[4000]; int
	 * dataSize; InputStream is = fromImageBlob.getBinaryStream();
	 * 
	 * try { while((dataSize = is.read(buf)) != -1) { baos.write(buf, 0, dataSize);
	 * } } finally { if(is != null) { is.close(); } } return baos.toByteArray(); }
	 */
	
}
